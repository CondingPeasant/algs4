import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private int mNumOfSegments = 0;
    private ArrayList<LineSegment> mSegments = new ArrayList<LineSegment>();
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (null == points || 0 == points.length)
            throw new NullPointerException();

        if (points.length < 4)
            return;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < points.length; i++) {
            Comparator<Point> c = points[i].slopeOrder();
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (0 != c.compare(points[j], points[k]))
                        continue;
                    for (int l = k + 1; l < points.length; l++) {
                        if (0 == c.compare(points[j], points[l])) {
//                            StdOut.println("i = " + points[i] +
//                                    ", j = " + points[j] +
//                                    ", k = " + points[k] +
//                                    ", l = " + points[l]);
//                            StdOut.println("slop(i, j) = " + points[i].slopeTo(points[j])
//                                    + ", slop(i, k) = " + points[i].slopeTo(points[k])
//                                    + ", slop(i, l) = " + points[i].slopeTo(points[l]));
                            mNumOfSegments++;
                            Point min = points[i];
                            Point max = points[i];
                            if (max.compareTo(points[j]) < 0)
                                max = points[j];
                            if (min.compareTo(points[j]) > 0)
                                min = points[j];
                            if (max.compareTo(points[k]) < 0)
                                max = points[k];
                            if (min.compareTo(points[k]) > 0)
                                min = points[k];
                            if (max.compareTo(points[l]) < 0)
                                max = points[l];
                            if (min.compareTo(points[l]) > 0)
                                min = points[l];
                            mSegments.add(new LineSegment(min, max));
                        }
                    }
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
