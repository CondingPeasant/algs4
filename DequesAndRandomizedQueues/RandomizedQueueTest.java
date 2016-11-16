import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RandomizedQueueTest {

    @Test
    public void testIsEmpty() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        assertTrue(rq.isEmpty());
    }

    @Test
    public void testSize() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        assertEquals(rq.size(), 0);
    }

    @Test
    public void testEnqueue() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String s = null;
        try {
            rq.enqueue(s);
        } catch (NullPointerException aNullPointerException) {
        }
    }

    @Test
    public void testDequeue() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        try {
            rq.dequeue();
        } catch (NoSuchElementException aNoSuchElementException) {
        }
    }

    @Test
    public void testSample() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        try {
            rq.sample();
        } catch (NoSuchElementException aNoSuchElementException) {
        }
    }

    @Test
    public void testIterator() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        Iterator<String> it = rq.iterator();

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
    public void testCase1() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("one");
        assertEquals(rq.size(), 1);
        rq.enqueue("two");
        assertEquals(rq.size(), 2);
        rq.enqueue("three");
        assertEquals(rq.size(), 3);
        rq.enqueue("four");
        assertEquals(rq.size(), 4);

        rq.dequeue();
        assertEquals(rq.size(), 3);
        rq.dequeue();
        assertEquals(rq.size(), 2);
        rq.dequeue();
        assertEquals(rq.size(), 1);
        rq.dequeue();
        assertEquals(rq.size(), 0);
        assertTrue(rq.isEmpty());
    }
}
