import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

public class PercolationTest {

    @Test
    public void testPercolation() {
        try {
            Percolation p0 = new Percolation(0);
        } catch (IllegalArgumentException anIllegalArgumentException) {
            // do nothing
        }
        Percolation p1 = new Percolation(1);
    }

    @Test
    public void testConver2DTo1D() {
        Percolation p = new Percolation(3);
        Class c = Percolation.class;
        try {
            Method method = c.getDeclaredMethod("conver2DTo1D", new Class[]{int.class, int.class});
            method.setAccessible(true);
            Object result = method.invoke(p, new Object[]{1,1});
            assertEquals(1, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method method = c.getDeclaredMethod("conver2DTo1D", new Class[]{int.class, int.class});
            method.setAccessible(true);
            Object result = method.invoke(p, new Object[]{1,3});
            assertEquals(3, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method method = c.getDeclaredMethod("conver2DTo1D", new Class[]{int.class, int.class});
            method.setAccessible(true);
            Object result = method.invoke(p, new Object[]{3,1});
            assertEquals(7, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Method method = c.getDeclaredMethod("conver2DTo1D", new Class[]{int.class, int.class});
            method.setAccessible(true);
            Object result = method.invoke(p, new Object[]{3,3});
            assertEquals(9, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOutOfBound() {
        Percolation p = new Percolation(1);
        try {
            p.isOpen(0, 0);
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            // do nothing
        }
        try {
            p.isOpen(0, 1);
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            // do nothing
        }
        try {
            p.isOpen(1, 0);
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            // do nothing
        }
        try {
            p.isOpen(1, 2);
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            // do nothing
        }
        try {
            p.isOpen(2, 1);
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            // do nothing
        }
        try {
            p.isOpen(2, 2);
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            // do nothing
        }
        assertFalse(p.isOpen(1, 1));
    }

    @Test
    public void testOthers_1() {
        Percolation p = new Percolation(1);
        assertFalse(p.isOpen(1, 1));
        assertFalse(p.isFull(1, 1));
        assertFalse(p.percolates());

        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
        assertTrue(p.isFull(1, 1));
        assertTrue(p.percolates());
    }

    @Test
    public void testOthers_2() {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        assertTrue(p.isOpen(1, 1));
        assertTrue(p.isFull(1, 1));
        p.open(2, 2);
        assertTrue(p.isOpen(2, 2));
        assertFalse(p.isFull(2, 2));
        assertFalse(p.percolates());

        p.open(1, 2);
        assertTrue(p.isOpen(1, 2));
        assertTrue(p.isFull(1, 2));
        assertTrue(p.isFull(2, 2));
        assertTrue(p.percolates());
    }

    @Test
    public void testOthers_3() {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(1, 2);
        assertTrue(p.isFull(1, 2));
        assertFalse(p.percolates());

        p.open(2, 1);
        assertTrue(p.isFull(1, 1));
        assertTrue(p.isFull(1, 2));
        assertTrue(p.isFull(2, 1));
        assertFalse(p.isFull(2, 2));
        assertTrue(p.percolates());
    }

    @Test
    public void testOthers_4() {
        Percolation p = new Percolation(3);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 1);
        assertFalse(p.isFull(3, 1));
        assertTrue(p.percolates());
    }
}
