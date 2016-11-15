import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node pre;
        private Node next;
    }

    private int size;
    private Node first;
    private Node last;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return 0 == size;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (null == item)
            throw new NullPointerException("Cannot add a null object.");

        Node node = new Node();
        node.item = item;

        if (1 == ++size) {
            last = node;
            first = node;
            node.pre = null;
            node.next = null;
        } else {
            node.pre = null;
            node.next = first;
            first.pre = node;
            first = node;
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        if (null == item)
            throw new NullPointerException("Cannot add a null object.");

        Node node = new Node();
        node.item = item;

        if (1 == ++size) {
            last = node;
            first = node;
            node.pre = null;
            node.next = null;
        } else {
            node.pre = last;
            node.next = null;
            last.next = node;
            last = node;
        }

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (0 == size)
            throw new NoSuchElementException(
                    "Deque is empty, there's nothing to remove!");

        Node node = first;
        if (0 == --size) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.pre = null;
        }
        
        node.next = null;
        node.pre = null;
        return node.item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (0 == size)
            throw new NoSuchElementException(
                    "Deque is empty, there's nothing to remove!");

        Node node = last;
        if (0 == --size) {
            first = null;
            last = null;
        } else {
            last = last.pre;
            last.next = null;
        }

        node.next = null;
        node.pre = null;
        return node.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return null != current;
        }
        public void remove() {
            throw new UnsupportedOperationException(
                    "DequeIterator does not support remove operation.");
        }
        public Item next() {
            if (null == current)
                throw new NoSuchElementException("There's no next element.");

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {
    }
}
