import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private int size;
    private int trials;
    private Percolation mPercolation;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        checkParam(n, trials);
        size = n;
        this.trials = trials;
        result = new double[n];
        doTrials();
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(result);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    // test client (described below)
    public static void main(String[] args)
    {
    }

    private void checkParam(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException(
                    "Row or trials number cannot be negtive.");
        }
    }

    private void doTrials() {
        for (int i = 0; i < trials; i++) {
            mPercolation = new Percolation(size);
            int j;
            for (j = 1; j <= size * size; j++) {
                int row, col;
                do {
                    row = StdRandom.uniform(1, size + 1);
                    col = StdRandom.uniform(1, size + 1);
                } while (!mPercolation.isOpen(row, col));
                mPercolation.open(row, col);
                if (mPercolation.percolates()) {
                    break;
                }
            }
            result[i] = (double) j / (double) size * size;
        }
    }
}
