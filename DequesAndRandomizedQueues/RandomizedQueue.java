import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node pre;
        private Node next;
    }

    private int size = 0;
    private Node first = null;
    private Node last = null;
    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the queue empty?
    public boolean isEmpty() {
        return 0 == size;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (null == item)
            throw new NullPointerException("Cannot add a null object.");

        Node node = new Node();
        node.item = item;
        node.next = null;
        node.pre = last;
        last = node;

        if (1 == ++size) {
            first = last;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (0 == size)
            throw new NoSuchElementException("RandomizedQueue is empty");
        int index = StdRandom.uniform(size);
        Node node = find(index);
        if (null != node.pre) {
            node.pre.next = node.next;
            node.pre = null;
        }

        if (null != node.next) {
            node.next.pre = node.pre;
            node.next = null;
        }

        if (0 == --size) {
            first = null;
            last = null;
        }
        return node.item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (0 == size)
            throw new NoSuchElementException("RandomizedQueue is empty");

        int index = StdRandom.uniform(size);
        Node node = find(index);
        return node.item;
    }

    private Node find(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node node = first;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] mItem = (Item[]) new Object[size];
        private int index = 0;
        public RandomizedQueueIterator() {
            for (Node p = first; null != p; p = p.next) {
                int i;
                do {
                    i = StdRandom.uniform(size);
                } while (null == mItem[i]);
                mItem[i] = p.item;
            }
        }

        public boolean hasNext() {
            return index >= 0 && index < size;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "DequeIterator does not support remove operation.");
        }

        public Item next() {
            if (index >= size)
                throw new NoSuchElementException("There's no next element.");
            return mItem[index++];
        }
    }

    // unit testing
    public static void main(String[] args) {
    }
}
