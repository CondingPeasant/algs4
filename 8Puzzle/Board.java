import java.util.Iterator;
import java.util.NoSuchElementException;

// import edu.princeton.cs.algs4.StdOut;

public class Board {
    private static final int BLANK = 0;
    private int mDimension;
    private int mBlankRow;
    private int mBlankCol;
    private final int[][] mBlocks;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null)
            throw new NullPointerException();

        mDimension = blocks.length;
        mBlocks = new int[blocks.length][blocks[0].length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                mBlocks[i][j] = blocks[i][j];
                if (mBlocks[i][j] == BLANK) {
                    mBlankRow = i;
                    mBlankCol = j;
                }
            }
        }
    }

    // board dimension n
    public int dimension() {
        return mDimension;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < mDimension; i++) {
            for (int j = 0; j < mDimension; j++) {
                if (!isInRightPosition(i, j) && !isBlank(i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < mDimension; i++) {
            for (int j = 0; j < mDimension; j++) {
                if (isInRightPosition(i, j) || isBlank(i, j))
                    continue;
                int rightRow = (mBlocks[i][j] - 1) / mDimension;
                int rightCol = mBlocks[i][j] - mDimension * rightRow - 1;
                count += Math.abs(rightRow - i) + Math.abs(rightCol - j);
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < mDimension; i++) {
            for (int j = 0; j < mDimension; j++) {
                if (!isInRightPosition(i, j))
                    return false;
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] tmpBlocks = copyBlocks();

        int row1, col1, row2, col2;
        row1 = (mBlankRow + 1) % mDimension;
        col1 = mBlankCol;
        row2 = mBlankRow;
        col2 = (mBlankCol + 1) % mDimension;
        swapBlock(tmpBlocks, row1, col1, row2, col2);
        return new Board(tmpBlocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null)
            return false;

        if (this.getClass() != y.getClass())
            return false;
        Board board = (Board) y;
        
        if (mDimension != board.mDimension)
            return false;

        for (int i = 0; i < mDimension; i++) {
            for (int j = 0; j < mDimension; j++) {
                if (mBlocks[i][j] != board.mBlocks[i][j])
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new BoardNeighbors();
    }

    private class BoardNeighbors implements Iterable<Board> {
        public Iterator<Board> iterator() {
            return new BoardIterator();
        }
    }

    private class BoardIterator implements Iterator<Board> {
        private Board[] mItem = new Board[4];
        private int index = 0;
        private int capacity = 0;

        public BoardIterator() {
            if (mBlankRow != 0) {
                int[][] tmpBlocks = copyBlocks();
                swapBlock(tmpBlocks, mBlankRow, mBlankCol,
                        mBlankRow - 1, mBlankCol);
                mItem[capacity++] = new Board(tmpBlocks);
            }
            if (mBlankCol != 0) {
                int[][] tmpBlocks = copyBlocks();
                swapBlock(tmpBlocks, mBlankRow, mBlankCol,
                        mBlankRow, mBlankCol - 1);
                mItem[capacity++] = new Board(tmpBlocks);
            }
            if (mBlankRow != mDimension - 1) {
                int[][] tmpBlocks = copyBlocks();
                swapBlock(tmpBlocks, mBlankRow, mBlankCol,
                        mBlankRow + 1, mBlankCol);
                mItem[capacity++] = new Board(tmpBlocks);
            }
            if (mBlankCol != mDimension - 1) {
                int[][] tmpBlocks = copyBlocks();
                swapBlock(tmpBlocks, mBlankRow, mBlankCol,
                        mBlankRow, mBlankCol + 1);
                mItem[capacity++] = new Board(tmpBlocks);
            }
        }

        public boolean hasNext() {
            return index < capacity;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "DequeIterator does not support remove operation.");
        }

        public Board next() {
            if (index >= capacity)
                throw new NoSuchElementException("There's no next element.");
            return mItem[index++];
        }
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d\n", mDimension));
        for (int i = 0; i < mDimension; i++) {
            for (int j = 0; j < mDimension; j++)
                sb.append(String.format("%2d ", mBlocks[i][j]));
            sb.append("\n");
        }
        return sb.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }

    private int convert2DTo1D(int row, int col) {
        return row * mDimension + col + 1;
    }

    private boolean isInRightPosition(int row, int col) {
        if (mBlocks[row][col] == 0)
            return row == mDimension - 1 && col == mDimension - 1;
        return mBlocks[row][col] == convert2DTo1D(row, col);
    }

    private boolean isBlank(int row, int col) {
        return mBlankRow == row && mBlankCol == col;
    }

    private void swapBlock(int[][] blocks, int row1, int col1, int row2, int col2) {
        int tmp = blocks[row1][col1];
        blocks[row1][col1] = blocks[row2][col2];
        blocks[row2][col2] = tmp;
    }

    private int[][] copyBlocks() {
        int[][] tmpBlocks = new int[mBlocks.length][mBlocks[0].length];
        for (int i = 0; i < mBlocks.length; i++) {
            for (int j = 0; j < mBlocks[0].length; j++) {
                tmpBlocks[i][j] = mBlocks[i][j];
            }
        }
        return tmpBlocks;
    }
}
