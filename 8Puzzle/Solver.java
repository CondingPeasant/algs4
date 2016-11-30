import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

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
    private boolean mIsSolvable = true;
    private int mMoves = 0;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        SearchNode initialNode = new SearchNode(initial, null);
        mSolveQueue.insert(initialNode);
        while (mSolveQueue.isEmpty()
                || !mSolveQueue.min().currentBoard.isGoal()) {
            SearchNode tmp = mSolveQueue.delMin();
//            StdOut.println("hamming = " + tmp.currentBoard.hamming() + ", manhattan = " + tmp.currentBoard.manhattan());
//            StdOut.println(tmp.currentBoard);
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//            }
            for (Board b : tmp.currentBoard.neighbors()) {
                if (!b.equals(tmp.currentBoard))
                    mSolveQueue.insert(new SearchNode(b, tmp));
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return mIsSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return mMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return new BoardSolution();
    }

    private class BoardSolution implements Iterable<Board> {
        public Iterator<Board> iterator() {
            return new SolutionIterator();
        }
    }

    private class SolutionIterator implements Iterator<Board> {
        private ArrayList<Board> mItem = new ArrayList<Board>();
        private int index = 0;

        public SolutionIterator() {
            if (mIsSolvable) {
                mItem.add(mSolveQueue.min().currentBoard);
                for (SearchNode s = mSolveQueue.min().preNode; s != null; s = s.preNode) {
                    mItem.add(s.currentBoard);
                }
                Collections.reverse(mItem);
                // - 1 due to Item inluding initial board
                mMoves = mItem.size() - 1;
            }
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
}
