import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int sizeLowBound = 1;
    private int size;
    private Item[] mRQ;
    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        mRQ = (Item[]) new Object[sizeLowBound];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return true;
    }

    // return the number of items on the queue
    public int size() {
        return 0;
    }

    // add the item
    public void enqueue(Item item) {
        if (null == item)
            throw new NullPointerException("Cannot add a null object.");
    }

    // remove and return a random item
    public Item dequeue() {
        return null;
    }

    // return (but do not remove) a random item
    public Item sample() {
        return null;
    }

    private void resize() {}

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        public boolean hasNext() {
            return false;
        }
        public void remove() {
            throw new UnsupportedOperationException("DequeIterator does not support remove operation.");
        }
        public Item next() {
            throw new NoSuchElementException("There's no next element.");
        }
    }

    // unit testing
    public static void main(String[] args) {
    }
}
