import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    private SET<Point2D> mSet;
    // construct an empty set of points 
    public PointSET() {
        mSet = new SET<Point2D>();
    }

    // is the set empty? 
    public boolean isEmpty() {
        return mSet.isEmpty();
    }

    // number of points in the set 
    public int size() {
        return mSet.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        mSet.add(p);
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null)
            throw new NullPointerException();

        return mSet.contains(p);
    }

    // draw all points to standard draw 
    public void draw() {
        Iterator<Point2D> it = mSet.iterator();
        while (it.hasNext()) {
            it.next().draw();
        }
    }

    // all points that are inside the rectangle 
    public Iterable<Point2D> range(final RectHV rect) {
        if (rect == null)
            throw new NullPointerException();

        return new Iterable<Point2D>() {
            public Iterator<Point2D> iterator() {
                return new RangeIterator(rect);
            }
        };
    }

    private class RangeIterator implements Iterator<Point2D> {
        private Point2D[] mCollection;
        private int index = 0;

        public RangeIterator(RectHV rect) {
            ArrayList<Point2D> item;
            item = new ArrayList<Point2D>();
            Iterator<Point2D> it = mSet.iterator();
            while (it.hasNext()) {
                Point2D p = it.next();
                if (rect.contains(p))
                    item.add(p);
            }
            mCollection = item.toArray(new Point2D[item.size()]);
        }

        public boolean hasNext() {
            return index < mCollection.length;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "DequeIterator does not support remove operation.");
        }

        public Point2D next() {
            if (index >= mCollection.length)
                throw new NoSuchElementException();
            return mCollection[index++];
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        double min = Double.POSITIVE_INFINITY;
        Point2D ret = null;
        Iterator<Point2D> it = mSet.iterator();
        while (it.hasNext()) {
            Point2D that = it.next();
            if (p.distanceTo(that) < min) {
                min = p.distanceTo(that);
                ret = that;
            }
        }
        return ret;
    }


    // unit testing of the methods (optional) 
    public static void main(String[] args) {
    }
}
