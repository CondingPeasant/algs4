import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.MinPQ;
//import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        public Board currentBoard;
        public SearchNode preNode;
        public SearchNode(Board current, SearchNode pre) {
            currentBoard = current;
            preNode = pre;
        }
        public int compareTo(SearchNode that) {
            if (currentBoard.hamming() + currentBoard.manhattan()
                    < that.currentBoard.hamming() + that.currentBoard.manhattan())
                return -1;
            if (currentBoard.hamming() + currentBoard.manhattan()
                    == that.currentBoard.hamming() + that.currentBoard.manhattan())
                return 0;
            return 1;
        }
    }

    private MinPQ<SearchNode> mSolveQueue = new MinPQ<SearchNode>();
    private MinPQ<SearchNode> mTwinQueue = new MinPQ<SearchNode>();
    private boolean mIsSolvable = true;
    private int mMoves = 0;
    private Iterator<Board> mSolutionIterator;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        SearchNode initialNode = new SearchNode(initial, null);
        SearchNode initialTwinNode = new SearchNode(initial.twin(), null);
        mSolveQueue.insert(initialNode);
        mTwinQueue.insert(initialTwinNode);
        while (!mSolveQueue.isEmpty()
                && !mSolveQueue.min().currentBoard.isGoal()) {
            if (!mTwinQueue.min().currentBoard.isGoal()) {
                SearchNode tmp = mSolveQueue.delMin();
                SearchNode tmpTwin = mTwinQueue.delMin();
//                StdOut.println("hamming = " + tmp.currentBoard.hamming() + ", manhattan = " + tmp.currentBoard.manhattan());
//                StdOut.println(tmp.currentBoard);
//                StdOut.println("twin:");
//                StdOut.println(tmpTwin.currentBoard);
//                try {
//                    Thread.sleep(500);
//                } catch (Exception e) {
//                }
                for (Board b : tmp.currentBoard.neighbors()) {
                    if (!isOnGameTree(tmp, b)) {
                        mSolveQueue.insert(new SearchNode(b, tmp));
                    }
                }
                for (Board b : tmpTwin.currentBoard.neighbors()) {
                    if (!isOnGameTree(tmpTwin, b)) {
                        mTwinQueue.insert(new SearchNode(b, tmpTwin));
                    }
                }
            } else {
                mIsSolvable = false;
                break;
            }
        }
        mSolutionIterator = new SolutionIterator();
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return mIsSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (mIsSolvable) {
            return mMoves;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (mIsSolvable) {
            return new BoardSolution();
        } else {
            return null;
        }
    }

    private class BoardSolution implements Iterable<Board> {
        public Iterator<Board> iterator() {
            return mSolutionIterator;
        }
    }

    private class SolutionIterator implements Iterator<Board> {
        private ArrayList<Board> mItem = new ArrayList<Board>();
        private int index = 0;

        public SolutionIterator() {
            mItem.add(mSolveQueue.min().currentBoard);
            for (SearchNode s = mSolveQueue.min().preNode; s != null; s = s.preNode) {
                mItem.add(s.currentBoard);
            }
            Collections.reverse(mItem);
            // - 1 due to Item inluding initial board
            mMoves = mItem.size() - 1;
        }

        public boolean hasNext() {
            return index < mItem.size();
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "DequeIterator does not support remove operation.");
        }

        public Board next() {
            if (index >= mItem.size())
                throw new NoSuchElementException("There's no next element.");
            return mItem.get(index++);
        }
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }

    private boolean isOnGameTree(SearchNode currentNode, Board neighbor) {
        for (SearchNode p = currentNode.preNode; p != null; p = p.preNode) {
            if (neighbor.equals(p.currentBoard))
                return true;
        }
        return false;
    }
}
