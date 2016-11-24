import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private int mNumOfSegments = 0;
    private ArrayList<Point[]> mSegments = new ArrayList<Point[]>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (null == points || 0 == points.length)
            throw new NullPointerException();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }

        if (points.length < 4)
            return;

        for (int i = 0; i < points.length; i++) {
            Point[] tmpPoints = new Point[points.length - i];
            System.arraycopy(points, i, tmpPoints, 0, points.length - i);
            Comparator<Point> c = points[i].slopeOrder();
            Arrays.sort(tmpPoints, c);
            // tmpPoints[0] is points[i] itself
            int j = 1;
            while (j < tmpPoints.length) {
                int k;
                for (k = j + 1; k < tmpPoints.length; k++) {
                    if (c.compare(tmpPoints[j], tmpPoints[k]) != 0)
                        break;
                }
                if (k - j >= 3) {
                    Point[] collinearPoints = new Point[k - j + 1];
                    collinearPoints[0] = points[i];
                    System.arraycopy(tmpPoints, j, collinearPoints, 1, k - j);
                    Arrays.sort(collinearPoints);
                    addToSegments(collinearPoints[0], collinearPoints[k - j]);
                }
                j = k;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return mNumOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[mSegments.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = new LineSegment(mSegments.get(i)[0], mSegments.get(i)[1]);
        }
        return ret;
    }

    // true: add; false: not add
    private boolean addToSegments(Point p, Point q) {
        for (Point[] seg : mSegments) {
            if (isCollinearLineSegment(p, q, seg)) {
                return false;
            }
        }
        mNumOfSegments++;
        Point[] newSeg = {p, q};
        mSegments.add(newSeg);
        return true;
    }

    private boolean isCollinearLineSegment(Point p, Point q, Point[] segment) {
        assert (segment.length == 2);
        return isPointOnLineSegment(p, segment)
                && isPointOnLineSegment(q, segment);
    }

    private boolean isPointOnLineSegment(Point p, Point[] segment) {
        assert (segment.length == 2);
        if (p.compareTo(segment[0]) == 0 || p.compareTo(segment[1]) == 0)
            return true;

        Comparator<Point> c = p.slopeOrder();
        return c.compare(segment[0], segment[1]) == 0;
    }
}
