import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class PointTest {

    @Test
    public void testSlopeTo() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(-1, 0);
        Point p4 = new Point(0, -1);
        Point p5 = new Point(1, 1);
        Point p6 = new Point(-1, 1);
        Point p7 = new Point(-1, -1);
        Point p8 = new Point(1, -1);
        Point p9 = new Point(2, 1);

        assertEquals(p0.slopeTo(p0), Double.NEGATIVE_INFINITY, 0.0001F);
        assertEquals(p0.slopeTo(p1), 0.0, 0.0001F);
        assertEquals(p0.slopeTo(p2), Double.POSITIVE_INFINITY, 0.0001F);
        assertEquals(p0.slopeTo(p3), 0.0, 0.0001F);
        assertEquals(p0.slopeTo(p4), Double.POSITIVE_INFINITY, 0.0001F);
        assertEquals(p3.slopeTo(p5), 0.5, 0.0001F);
        assertEquals(p3.slopeTo(p9), 0.33333, 0.0001F);
        assertEquals(p5.slopeTo(p7), 1.0, 0.0001F);
        assertEquals(p6.slopeTo(p8), -1.0, 0.0001F);
        assertEquals(p4.slopeTo(p5), 2.0, 0.0001F);
        assertEquals(p4.slopeTo(p6), -2.0, 0.0001F);
    }

    @Test
    public void testCompareTo() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(-1, 0);
        Point p4 = new Point(0, -1);
        Point p5 = new Point(1, 1);
        Point p6 = new Point(-1, 1);
        Point p7 = new Point(-1, -1);
        Point p8 = new Point(1, -1);

        assertEquals(p0.compareTo(p1), -1);
        assertEquals(p0.compareTo(p2), -1);
        assertEquals(p0.compareTo(p3), 1);
        assertEquals(p0.compareTo(p4), 1);
        assertEquals(p0.compareTo(p0), 0);
        assertEquals(p1.compareTo(p1), 0);
        assertEquals(p5.compareTo(p7), 1);
        assertEquals(p6.compareTo(p8), 1);
    }

    @Test
    public void testSlopeOrder() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(-1, 0);
        Point p4 = new Point(0, -1);
        Point p5 = new Point(1, 1);
        Point p6 = new Point(-1, 1);
        Point p7 = new Point(-1, -1);
        Point p8 = new Point(1, -1);
        Comparator<Point> comparator = p0.slopeOrder();
        assertTrue(comparator.compare(p1, p2) < 0);
        assertTrue(comparator.compare(p1, p3) == 0);
        assertTrue(comparator.compare(p2, p4) == 0);
        assertTrue(comparator.compare(p5, p6) > 0);
    }

}
