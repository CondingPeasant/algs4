import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Draw;
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

        public boolean insert(Point2D p) {
            if (mPoint.equals(p))
                return false;

            boolean ret;
            if (mSplitDirection == VERTICAL) {
                // equals p.x() >= mPoint.x()
                if (p.x() - mPoint.x() >= -DELTA) {
                    if (mRChild == null) {
                        mRChild = new KdTreeNode(p, HORIZONAL);
                        ret = true;
                    } else {
                        ret = mRChild.insert(p);
                    }
                } else {
                    if (mLChild == null) {
                        mLChild = new KdTreeNode(p, HORIZONAL);
                        ret = true;
                    } else {
                        ret = mLChild.insert(p);
                    }
                }
            } else {
                if (p.y() - mPoint.y() >= -DELTA) {
                    if (mRChild == null) {
                        mRChild = new KdTreeNode(p, VERTICAL);
                        ret = true;
                    } else {
                        ret = mRChild.insert(p);
                    }
                } else {
                    if (mLChild == null) {
                        mLChild = new KdTreeNode(p, VERTICAL);
                        ret = true;
                    } else {
                        ret = mLChild.insert(p);
                    }
                }
            }
            return ret;
        }

        public boolean contains(Point2D p) {
            if (mPoint.equals(p))
                return true;

            KdTreeNode next;
            if (mSplitDirection == VERTICAL) {
                if (p.x() - mPoint.x() >= -DELTA) {
                    next = mRChild;
                } else {
                    next = mLChild;
                }
            } else {
                if (p.y() - mPoint.y() >= -DELTA) {
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
        
        public Point2D nearest(Point2D p, double nearestDistance) {
            double distanceToLeftChild = Double.POSITIVE_INFINITY;
            double distanceToRightChild = Double.POSITIVE_INFINITY;
            double distanceToCurrent = getPoint().distanceTo(p);

            KdTreeNode lChild;
            KdTreeNode rChild;
            Point2D lRet = null;
            Point2D rRet = null;
            
            lChild = getLChild();
            rChild = getRChild();

            if (lChild == null && rChild == null) {
                if (distanceToCurrent < nearestDistance - DELTA)
                    return getPoint();
                return null;
            }

            if (lChild != null) {
                lRet = lChild.nearest(p, nearestDistance);
                distanceToLeftChild = lRet.distanceTo(p);
            }

            if (rChild != null) {
                rRet = rChild.nearest(p, nearestDistance);
                distanceToRightChild = rRet.distanceTo(p);
            }

            if (distanceToRightChild < distanceToLeftChild - DELTA
                    && distanceToRightChild < distanceToCurrent - DELTA) {
                return rRet;
            }

            if (distanceToLeftChild < distanceToRightChild - DELTA
                    && distanceToLeftChild < distanceToCurrent - DELTA) {
                return lRet;
            }

            return getPoint();
        }

        public void draw() {
            mDraw.setPenColor(Draw.BLACK);
            mPoint.draw();
            if (mSplitDirection == VERTICAL) {
                mDraw.setPenColor(Draw.RED);
                mDraw.line(mPoint.x(), 0, mPoint.x(), 10);
            } else {
                mDraw.setPenColor(Draw.BLUE);
                mDraw.line(0, mPoint.y(), 10, mPoint.y());
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

    private static final double DELTA = 0.0000001D;
    private KdTreeNode mRoot;
    private int mSize;
    private Draw mDraw;
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
            mSize++;
        } else {
            if (mRoot.insert(p))
                mSize++;
        }

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
        if (mDraw == null) {
            mDraw = new Draw();
        } else {
            mDraw.clear();
        }
        draw(mRoot);
        mDraw.show();
    }

    private void draw(KdTreeNode node) {
        if (node == null)
            return;
        draw(node.getLChild());
        node.draw();
        draw(node.getRChild());
    }

    // all points that are inside the rectangle 
    public Iterable<Point2D> range(final RectHV rect) {
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

        if (mRoot == null)
            return null;

        return mRoot.nearest(p, Double.POSITIVE_INFINITY);
    }

    // unit testing of the methods (optional) 
    public static void main(String[] args) {
    }
}
