import java.util.ArrayList;

import edu.princeton.cs.algs4.Insertion;

public class FastCollinearPoints {
        private int mNumOfSegments = 0;
        private ArrayList<LineSegment> mSegments = new ArrayList<LineSegment>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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
            Double[] slope = new Double[points.length - i];
            Double[] slopeOrigin = new Double[points.length - i];
            for (int j = i + 1; j < points.length; j++) {
                slope[j] = points[i].slopeTo(points[j]);
                slopeOrigin[j] = points[i].slopeTo(points[j]);
                Insertion.sort(slope, points[i].slopeOrder());
                for (int k = 0; k + 2 < slope.length; k++) {
                    if (slope[k] == slope[k + 1]
                            && slope[k] == slope[k + 2]) {
                        mNumOfSegments++;
                        Point min = points[i];
                        Point max = points[i];
                        for (int l = 0; l < slopeOrigin.length; l++) {
                            if(slopeOrigin[l] == slope[k]
                                    && min.compareTo(points[l + i]) < 0)
                                min = points[l + i];
                            if(slopeOrigin[l] == slope[k]
                                    && max.compareTo(points[l + i]) > 0)
                                max = points[l + i];
                        }
                        mSegments.add(new LineSegment(min, max));
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
