import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private class KdTreeNode {
        public static final boolean VERTICAL = false;
        public static final boolean HORIZONAL = true;
        private Point2D mPoint;
        private KdTreeNode mLChild;
        private KdTreeNode mRChild;
        private boolean mSplitDirection;

        public KdTreeNode(Point2D point, boolean splitDirection) {
            mPoint = point;
            mSplitDirection = splitDirection;
            mLChild = null;
            mRChild = null;
        }

        public void insert(Point2D p) {
            if (mSplitDirection == VERTICAL) {
                if (p.x() - mPoint.x() >= DELTA) {
                    if (mRChild == null) {
                        mRChild = new KdTreeNode(p, HORIZONAL);
                    } else {
                        mRChild.insert(p);
                    }
                } else {
                    if (mLChild == null) {
                        mLChild = new KdTreeNode(p, HORIZONAL);
                    } else {
                        mLChild.insert(p);
                    }
                }
            } else {
                if (p.y() - mPoint.y() >= DELTA) {
                    if (mRChild == null) {
                        mRChild = new KdTreeNode(p, VERTICAL);
                    } else {
                        mRChild.insert(p);
                    }
                } else {
                    if (mLChild == null) {
                        mLChild = new KdTreeNode(p, VERTICAL);
                    } else {
                        mLChild.insert(p);
                    }
                }
            }
        }

        public boolean contains(Point2D p) {
            if (mPoint.equals(p))
                return true;

            KdTreeNode next;
            if (mSplitDirection == VERTICAL) {
                if (p.x() - mPoint.x() >= DELTA) {
                    next = mRChild;
                } else {
                    next = mLChild;
                }
            } else {
                if (p.y() - mPoint.x() >= DELTA) {
                    next = mRChild;
                } else {
                    next = mLChild;
                }
            }

            if (next == null) {
                return false;
            } else {
                return next.contains(p);
            }
        }

        public KdTreeNode getLChild() {
            return mLChild;
        }

        public KdTreeNode getRChild() {
            return mRChild;
        }

        public Point2D getPoint() {
            return mPoint;
        }
    }

    private static final double DELTA = 0.000001D;
    private KdTreeNode mRoot;
    private int mSize;
    // construct an empty set of points 
    public KdTree() {
    }

    // is the set empty? 
    public boolean isEmpty() {
        return mSize == 0;
    }

    // number of points in the set 
    public int size() {
        return mSize;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new NullPointerException();

        if (mRoot == null) {
            mRoot = new KdTreeNode(p, KdTreeNode.VERTICAL);
        } else {
            mRoot.insert(p);
        }

        mSize++;
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        if (mRoot == null) {
            return false;
        }

        return mRoot.contains(p);
    }

    // draw all points to standard draw 
    public void draw() {
        // TODO
    }

    // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new NullPointerException();
        if (mRoot == null)
            return null;

        return new Iterable<Point2D>() {
            public Iterator<Point2D> iterator() {
                return new RangeIterator(rect);
            }
        };
    }

    private class RangeIterator implements Iterator<Point2D> {
        private Point2D[] mCollection;
        private int index;
        public RangeIterator(RectHV rect) {
            ArrayList<Point2D> arr = new ArrayList<Point2D>();
            tranverse(mRoot, rect, arr);
            mCollection = arr.toArray(new Point2D[arr.size()]);
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

        public void tranverse(KdTreeNode node, RectHV rect, ArrayList<Point2D> arr) {
            if (node == null) {
                return;
            }

            tranverse(node.getLChild(), rect, arr);
            if (rect.contains(node.getPoint())) {
                arr.add(node.getPoint());
            }
            tranverse(node.getRChild(), rect, arr);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException();

        return findNearest(p, Double.POSITIVE_INFINITY, mRoot);
    }

    private Point2D findNearest(Point2D p, double nearestDistance, KdTreeNode node) {
        Point2D ret = null;
        Point2D leftRet = null;
        Point2D rightRet = null;
        if (node == null)
            return null;

        double distanceToCurrent = node.getPoint().distanceTo(p);
        double distanceToLChild = Double.POSITIVE_INFINITY;
        double distanceToRChild = Double.POSITIVE_INFINITY;
        if (distanceToCurrent - nearestDistance <= -DELTA) {
            ret = node.getPoint();
            nearestDistance = distanceToCurrent;
        }
        if (node.getLChild() != null)
            distanceToLChild = node.getLChild().getPoint().distanceTo(p);
        if (node.getRChild() != null)
            distanceToRChild = node.getRChild().getPoint().distanceTo(p);

        if (distanceToLChild - distanceToCurrent >= DELTA
                && distanceToLChild - distanceToRChild >= DELTA) {
            rightRet = findNearest(p, distanceToCurrent, node.getRChild());
        } else if (distanceToRChild - distanceToCurrent >= DELTA
                && distanceToRChild - distanceToLChild >= DELTA) {
            leftRet = findNearest(p, distanceToCurrent, node.getLChild());
        } else {
            leftRet = findNearest(p, distanceToCurrent, node.getLChild());
            rightRet = findNearest(p, distanceToCurrent, node.getRChild());
        }

        if (rightRet != null
                && rightRet.distanceTo(p) - nearestDistance <= -DELTA)
            ret = rightRet;

        if (leftRet != null
                && leftRet.distanceTo(p) - nearestDistance <= -DELTA)
            ret = leftRet;

        return ret;
    }

    // unit testing of the methods (optional) 
    public static void main(String[] args) {
    }
}
