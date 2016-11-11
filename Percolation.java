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
        bottom = size * size + 1;
        mUF = new WeightedQuickUnionUF(n * n + 2);

        mGrid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mGrid[i][j] = false;
            }
        }
    }

    // open site (row, col) if it is not open already:w
    public void open(int row, int col)
    {
        if (mGrid[row - 1][col - 1] == true)
            return;

        mGrid[row - 1][col - 1] = true;

        int selfIndex = size * (row - 1) + col;
        int upIndex = size * (row - 2) + col;
        int downIndex = size * row + col;
        int leftIndex = size * (row - 1) + col - 1;
        int rightIndex = size * (row - 1) + col + 1;
        if (row != 1 && isOpen(row - 1, col)) {
            mUF.union(selfIndex, upIndex);
        }
        if (row != size && isOpen(row + 1, col)) {
            mUF.union(selfIndex, downIndex);
        }
        if (col != 1 && isOpen(row, col - 1)) {
            mUF.union(selfIndex, leftIndex);
        }
        if (col != size && isOpen(row, col + 1)) {
            mUF.union(selfIndex, rightIndex);
        }
        if (row == 1) {
            mUF.union(selfIndex, top);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        return mGrid[row - 1][col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (isOpen(row, col)) {
            return mUF.connected(top, size * (row - 1) + col);
        } else {
            return false;
        }
    }

    // does the system percolate?
    public boolean percolates()
    {
        for (int i = 1; i <= size; i++) {
            if (isFull(size, i)) {
                return true;
            }
        }
        return false;
    }
 
    // test client (optional)
    public static void main(String[] args) {
    }
}
