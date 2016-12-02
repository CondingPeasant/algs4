import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeTest {
    private RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);

    @Test
    public void testIsEmpty() {
        PointSET ps = new PointSET();
        assertTrue(ps.isEmpty());
        ps.insert(new Point2D(0.5, 0.5));
        assertFalse(ps.isEmpty());
    }

    @Test
    public void testSize() {
        PointSET ps = new PointSET();
        assertEquals(ps.size(), 0);
        ps.insert(new Point2D(0.5, 0.5));
        assertEquals(ps.size(), 1);
        ps.insert(new Point2D(0.5, 0.5));
        assertEquals(ps.size(), 1);
        ps.insert(new Point2D(0.6, 0.5));
        assertEquals(ps.size(), 2);
    }

    @Test
    public void testInsert() {
        PointSET ps = new PointSET();
        try {
            ps.insert(null);
            fail("Not yet implemented");
        } catch (NullPointerException aNullPointerException) {
        }
    }

    @Test
    public void testContains() {
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.6, 0.5);
        PointSET ps = new PointSET();
        assertFalse(ps.contains(p1));
        assertFalse(ps.contains(p2));

        ps.insert(p1);
        assertTrue(ps.contains(p1));
        assertFalse(ps.contains(p2));

        ps.insert(p2);
        assertTrue(ps.contains(p1));
        assertTrue(ps.contains(p2));
    }

    public void testDraw() {
        fail("Not yet implemented");
    }

    public void testRange() {
        fail("Not yet implemented");
    }

    @Test
    public void testNearest() {
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.6, 0.5);
        Point2D p3 = new Point2D(0.9, 0.9);
        Point2D p4 = new Point2D(0.8, 0.9);
        Point2D p5 = new Point2D(0.9, 0.8);
        Point2D p6 = new Point2D(0.7, 0.8);
        Point2D p7 = new Point2D(0.9, 0.7);
        Point2D p8 = new Point2D(0.7, 0.9);
        Point2D p9 = new Point2D(0.8, 0.8);
        PointSET ps = new PointSET();
        ps.insert(p1);
        ps.insert(p2);
        ps.insert(p3);
        ps.insert(p4);
        ps.insert(p5);
        ps.insert(p6);
        ps.insert(p7);
        ps.insert(p7);
        ps.insert(p8);
        ps.insert(p9);
        assertEquals(ps.nearest(p2), p1);
    }


}
