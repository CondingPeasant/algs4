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
        KdTree kt = new KdTree();
        assertTrue(kt.isEmpty());
        kt.insert(new Point2D(0.5, 0.5));
        assertFalse(kt.isEmpty());
    }

    @Test
    public void testSize() {
        KdTree kt = new KdTree();
        assertEquals(kt.size(), 0);
        kt.insert(new Point2D(1, 1));
        assertEquals(kt.size(), 1);
        kt.insert(new Point2D(1, 1));
        assertEquals(kt.size(), 2);
        kt.insert(new Point2D(1, 1));
        assertEquals(kt.size(), 3);
        kt.insert(new Point2D(1, 1));
        assertEquals(kt.size(), 4);
        kt.insert(new Point2D(1, 1));
        assertEquals(kt.size(), 5);
        kt.insert(new Point2D(1, 1));
        assertEquals(kt.size(), 6);
        kt.insert(new Point2D(1, 2));
        assertEquals(kt.size(), 7);
    }

    @Test
    public void testInsert() {
        KdTree kt = new KdTree();
        try {
            kt.insert(null);
            fail("Not yet implemented");
        } catch (NullPointerException aNullPointerException) {
        }
    }

    @Test
    public void testContains() {
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.6, 0.5);
        KdTree kt = new KdTree();
        assertFalse(kt.contains(p1));
        assertFalse(kt.contains(p2));

        kt.insert(p1);
        assertTrue(kt.contains(p1));
        assertFalse(kt.contains(p2));

        kt.insert(p2);
        assertTrue(kt.contains(p1));
        assertTrue(kt.contains(p2));

        kt.insert(p1);
        assertTrue(kt.contains(p1));
        assertTrue(kt.contains(p2));

        kt.insert(p1);
        assertTrue(kt.contains(p1));
        assertTrue(kt.contains(p2));

        kt.insert(p1);
        assertTrue(kt.contains(p1));
        assertTrue(kt.contains(p2));
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
        KdTree kt = new KdTree();
        kt.insert(p1);
        kt.insert(p2);
        kt.insert(p3);
        kt.insert(p4);
        kt.insert(p5);
        kt.insert(p6);
        kt.insert(p7);
        kt.insert(p7);
        kt.insert(p8);
        kt.insert(p9);
        assertEquals(kt.nearest(new Point2D(0.5, 0.6)), p1);
        assertEquals(kt.nearest(p1), p1);
    }


}
