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

    @Test
    // addFirst & removeFirst
    public void testCase1() {
        Deque<String> d = new Deque<String>();
        d.addFirst("three");
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());

        d.addFirst("two");
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());

        d.addFirst("one");
        assertEquals(d.size(), 3);
        assertFalse(d.isEmpty());

        String s;
        s = d.removeFirst();
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());
        assertEquals(s, "one");

        s = d.removeFirst();
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());
        assertEquals(s, "two");

        s = d.removeFirst();
        assertEquals(d.size(), 0);
        assertTrue(d.isEmpty());
        assertEquals(s, "three");
    }

    @Test
    // addFirst & removeLast
    public void testCase2() {
        Deque<String> d = new Deque<String>();
        d.addFirst("one");
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());

        d.addFirst("two");
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());

        d.addFirst("three");
        assertEquals(d.size(), 3);
        assertFalse(d.isEmpty());

        String s;
        s = d.removeLast();
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());
        assertEquals(s, "one");

        s = d.removeLast();
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());
        assertEquals(s, "two");

        s = d.removeLast();
        assertEquals(d.size(), 0);
        assertTrue(d.isEmpty());
        assertEquals(s, "three");
    }


    @Test
    // addLast & removeFirst
    public void testCase3() {
        Deque<String> d = new Deque<String>();
        d.addLast("one");
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());

        d.addLast("two");
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());

        d.addLast("three");
        assertEquals(d.size(), 3);
        assertFalse(d.isEmpty());

        String s;
        s = d.removeFirst();
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());
        assertEquals(s, "one");

        s = d.removeFirst();
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());
        assertEquals(s, "two");

        s = d.removeFirst();
        assertEquals(d.size(), 0);
        assertTrue(d.isEmpty());
        assertEquals(s, "three");
    }

    @Test
    // addLast & removeLast
    public void testCase4() {
        Deque<String> d = new Deque<String>();
        d.addLast("one");
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());

        d.addLast("two");
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());

        d.addLast("three");
        assertEquals(d.size(), 3);
        assertFalse(d.isEmpty());

        String s;
        s = d.removeLast();
        assertEquals(d.size(), 2);
        assertFalse(d.isEmpty());
        assertEquals(s, "three");

        s = d.removeLast();
        assertEquals(d.size(), 1);
        assertFalse(d.isEmpty());
        assertEquals(s, "two");

        s = d.removeLast();
        assertEquals(d.size(), 0);
        assertTrue(d.isEmpty());
        assertEquals(s, "one");
    }

    @Test
    // iterator
    public void testCase5() {
        Deque<String> d = new Deque<String>();
        d.addLast("one");
        d.addLast("two");
        d.addLast("three");

        Iterator<String> it = d.iterator();
        assertTrue(it.hasNext());
        assertEquals(it.next(), "one");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "two");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "three");
        assertFalse(it.hasNext());
    }
}
