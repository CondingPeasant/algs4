import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.Insertion;

public class FastCollinearPoints {
        private int mNumOfSegments = 0;
        private ArrayList<LineSegment> mSegments = new ArrayList<LineSegment>();

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
            Point[] tmp = new Point[points.length - i];
            System.arraycopy(points, i, tmp, 0, points.length - i);
            Comparator<Point> c = points[i].slopeOrder();
            Insertion.sort(tmp, c);
            for (int j = 0; j + 2 < tmp.length; j++) {
                if (c.compare(tmp[j], tmp[j + 1]) == 0
                        && c.compare(tmp[j], tmp[j + 2]) == 0) {
                    mNumOfSegments++;
                    Point min = points[i];
                    Point max = points[i];
                    if (max.compareTo(tmp[j]) < 0)
                        max = tmp[j];
                    if (min.compareTo(tmp[j]) > 0)
                        min = tmp[j];
                    if (max.compareTo(tmp[j + 1]) < 0)
                        max = tmp[j + 1];
                    if (min.compareTo(tmp[j + 1]) > 0)
                        min = tmp[j + 1];
                    if (max.compareTo(tmp[j + 2]) < 0)
                        max = tmp[j + 2];
                    if (min.compareTo(tmp[j + 2]) > 0)
                        min = tmp[j + 2];
                    mSegments.add(new LineSegment(min, max));
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return mNumOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return mSegments.toArray(new LineSegment[mSegments.size()]);
    }

}
