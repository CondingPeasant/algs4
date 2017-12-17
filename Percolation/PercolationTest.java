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
        p.open(3, 3);
        p.open(2, 3);
        assertTrue(p.percolates());
        p.open(3, 1);
        assertFalse(p.isFull(3, 1));
        assertTrue(p.percolates());
    }
}
