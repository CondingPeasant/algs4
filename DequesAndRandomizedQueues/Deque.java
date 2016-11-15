import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node pre;
        Node next;
    }

    private int size;
    private Item first;
    private Item last;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return true;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (null == item)
            throw new NullPointerException("Cannot add a null object.");
    }

    // add the item to the end
    public void addLast(Item item) {
        if (null == item)
            throw new NullPointerException("Cannot add a null object.");
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (0 == size)
            throw new NoSuchElementException("Deque is empty, there's nothing to remove!");
        return first;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (0 == size)
            throw new NoSuchElementException("Deque is empty, there's nothing to remove!");
        return last;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
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
