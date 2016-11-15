import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DequeTest {

    @Test
    public void testIsEmpty() {
        Deque<String> d = new Deque<String>();
        assertTrue(d.isEmpty());
    }

    @Test
    public void testSize() {
        Deque<String> d = new Deque<String>();
        assertEquals(d.size(), 0);
    }

    @Test
    public void testAddFirst() {
        Deque<String> d = new Deque<String>();
        String s = null;
        try {
            d.addFirst(s);
        } catch (NullPointerException aNullPointerException) {
        }
    }

    @Test
    public void testAddLast() {
        Deque<String> d = new Deque<String>();
        String s = null;
        try {
            d.addLast(s);
        } catch (NullPointerException aNullPointerException) {
        }
    }

    @Test
    public void testRemoveFirst() {
        Deque<String> d = new Deque<String>();
        try {
            d.removeFirst();
        } catch (NoSuchElementException aNoSuchElementException) {
        }
    }

    @Test
    public void testRemoveLast() {
        Deque<String> d = new Deque<String>();
        try {
            d.removeLast();
        } catch (NoSuchElementException aNoSuchElementException) {
        }
    }

    @Test
    public void testIterator() {
        Deque<String> d = new Deque<String>();
        Iterator<String> it = d.iterator();

        assertFalse(it.hasNext());
        try {
            it.remove();
        } catch (UnsupportedOperationException aUnsupportedOperationException) {
        }
        try {
            it.next();
        } catch (NoSuchElementException aNoSuchElementException) {
        }
    }

}
