import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int top;
    private final int bottom;
    private boolean[][] mGrid;
    private WeightedQuickUnionUF mUF;
    private int size;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n)
    {
        if (n <= 0) {
            throw new IllegalArgumentException("Row number cannot be negtive.");
        }
        size = n;
        top = 0;
        bottom = n * n + 1;
        // include top(0) and bottom(n * n + 1)
        mUF = new WeightedQuickUnionUF(n * n + 2);

        mGrid = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                mGrid[i][j] = false;
            }
        }
    }

    // open site (row, col) if it is not open already:w
    public void open(int row, int col)
    {
        checkParam(row, col);
        mGrid[row][col] = true;
        if (isInGrid(row - 1, col) && isOpen(row - 1, col)) {
            mUF.union(conver2DTo1D(row, col), conver2DTo1D(row - 1, col));
        }
        if (isInGrid(row + 1, col) && isOpen(row + 1, col)) {
            mUF.union(conver2DTo1D(row, col), conver2DTo1D(row + 1, col));
        }
        if (isInGrid(row, col - 1) && isOpen(row, col - 1)) {
            mUF.union(conver2DTo1D(row, col), conver2DTo1D(row, col - 1));
        }
        if (isInGrid(row, col + 1) && isOpen(row, col + 1)) {
            mUF.union(conver2DTo1D(row, col), conver2DTo1D(row, col + 1));
        }
        if (row == 1) {
            mUF.union(conver2DTo1D(row, col), top);
        }
        if (row == size) {
            mUF.union(conver2DTo1D(row, col), bottom);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        checkParam(row, col);
        return mGrid[row][col];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col)
    {
        checkParam(row, col);
        if (isOpen(row, col)) {
            return mUF.connected(top, conver2DTo1D(row, col));
        } else {
            return false;
        }
    }

    // does the system percolate?
    public boolean percolates()
    {
        return mUF.connected(top, bottom);
    }
 
    // test client (optional)
    public static void main(String[] args) {
    }

    private boolean isInGrid(int row, int col) {
        boolean ret;
        boolean isRowInRange = row > 0 && row <= size;
        boolean isColInRange = col > 0 && col <= size;
        if (isRowInRange && isColInRange) {
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    private void checkParam(int row, int col) {
        if (!isInGrid(row, col)) {
            throw new IndexOutOfBoundsException("row or col is out of bound!");
        }
    }
    private int conver2DTo1D(int row, int col) {
        return size * (row - 1) + col;
    }
}
