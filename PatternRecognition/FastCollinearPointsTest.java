import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

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

        StdOut.println("case1");
        for (int i = 0; i < fcp.numberOfSegments(); i++) {
            StdOut.println(fcp.segments()[i]);
        }
    }

    @Test
    public void testCase2() {
        Point[] points = {new Point(0, 0), new Point(1, 2), new Point(2, 2), new Point (3, 3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 0);

        StdOut.println("case2");
        for (int i = 0; i < fcp.numberOfSegments(); i++) {
            StdOut.println(fcp.segments()[i]);
        }
    }

    @Test
    public void testCase3() {
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point (3, 3),
                          new Point(1, -1), new Point(2, -2), new Point(3, -3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 2);

        StdOut.println("case3");
        for (int i = 0; i < fcp.numberOfSegments(); i++) {
            StdOut.println(fcp.segments()[i]);
        }
    }

    @Test
    public void testCase4() {
        Point[] points = {new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point (0, -1)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 1);

        StdOut.println("case4");
        for (int i = 0; i < fcp.numberOfSegments(); i++) {
            StdOut.println(fcp.segments()[i]);
        }
    }

    @Test
    public void testCase5() {
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point (3, 3),
                          new Point(-1, -1), new Point(-2, -2), new Point(-3, -3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 1);

        StdOut.println("case5");
        for (int i = 0; i < fcp.numberOfSegments(); i++) {
            StdOut.println(fcp.segments()[i]);
        }
    }

    @Test
    public void testCase6() {
        Point[] points = {new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point (0, 3),
                          new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point (1, 3),
                          new Point(2, 0), new Point(2, 1), new Point(2, 2), new Point (2, 3),
                          new Point(3, 0), new Point(3, 1), new Point(3, 2), new Point (3, 3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assertEquals(fcp.numberOfSegments(), 10);

        StdOut.println("case6");
        for (int i = 0; i < fcp.numberOfSegments(); i++) {
            StdOut.println(fcp.segments()[i]);
        }
    }

    @Test
    public void testIsCollinearLineSegment() throws Exception {
        Point[] points = {new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point (0, 3)};
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        Method testIsCollinearLineSegment = fcp.getClass().getDeclaredMethod("isCollinearLineSegment",
                new Class[]{Point.class, Point.class, Point[].class});   
        testIsCollinearLineSegment.setAccessible(true);   

        StdOut.println("testIsCollinearLineSegment");
        Point p = new Point(0, 0);
        Point q = new Point(1, 1);
        Point[] seg1 = {p, new Point(0, 1)};
        Object result1 = testIsCollinearLineSegment.invoke(fcp, new Object[]{p, q, seg1});
        assertEquals(false, result1);

        Point[] seg2 = {p, q};
        Object result2 = testIsCollinearLineSegment.invoke(fcp, new Object[]{p, q, seg2});
        assertEquals(true, result2);
        StdOut.println("testIsCollinearLineSegment done");
    }
}
