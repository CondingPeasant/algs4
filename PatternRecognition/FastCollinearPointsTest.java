import static org.junit.Assert.*;

import org.junit.Test;

public class FastCollinearPointsTest {

    @Test
    public void testExcptionThrown() {
        try {
            FastCollinearPoints fcp = new FastCollinearPoints(null);
            fail("Expected a NullPointerException to be thrown");
        } catch (NullPointerException aNullPointerException) {
        }

        Point[] points1 = {};
        try {
            FastCollinearPoints fcp = new FastCollinearPoints(points1);
            fail("Expected a NullPointerException to be thrown");
        } catch (NullPointerException aNullPointerException) {
        }

        Point[] points2 = {new Point(0, 0), new Point(0, 0), new Point(2, 2), new Point (3, 3)};
        try {
            FastCollinearPoints fcp = new FastCollinearPoints(points2);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
        }

        Point[] points3 = {new Point(0, 0), new Point(1, 1), new Point(1, 1), new Point (3, 3)};
        try {
            FastCollinearPoints fcp = new FastCollinearPoints(points3);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
        }
    }

    @Test
    public void testCase1() {
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point (3, 3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 1);
        LineSegment[] segmentsResult = {new LineSegment(new Point(0, 0), new Point(3,3))};
    }

    @Test
    public void testCase2() {
        Point[] points = {new Point(0, 0), new Point(1, 2), new Point(2, 2), new Point (3, 3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 0);
    }

    @Test
    public void testCase3() {
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point (3, 3),
                          new Point(1, -1), new Point(2, -2), new Point(3, -3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 2);
        LineSegment[] segmentsResult = {new LineSegment(new Point(0, 0), new Point(3,3)),
                                        new LineSegment(new Point(0, 0), new Point(-3, -3))};
    }

    @Test
    public void testCase4() {
        Point[] points = {new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point (0, -1)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 1);
        LineSegment[] segmentsResult = {new LineSegment(new Point(0, -1), new Point(0,2))};
    }

}
