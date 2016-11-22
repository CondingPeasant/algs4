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
            double[] slope = new double[points.length - 1];
            for (j = 0; j < points.length; j++) {
                if (j == i) {
                    j--;
                    continue;
                }
                slope[j] = points[i].slopeTo(points[j]);
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
