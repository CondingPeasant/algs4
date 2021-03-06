import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private int size;
    private int trials;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        checkParam(n, trials);
        size = n;
        this.trials = trials;
        result = new double[trials];
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
        int n = StdIn.readInt();
        int t = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo());
        StdOut.println(ps.confidenceHi());
    }

    private void checkParam(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException(
                    "Row or trials number cannot be negtive.");
        }
    }

    private void doTrials() {
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(size);
            int j;
            for (j = 1; j <= size * size; j++) {
                int row, col;
                do {
                    row = StdRandom.uniform(1, size + 1);
                    col = StdRandom.uniform(1, size + 1);
                } while (p.isOpen(row, col));
                p.open(row, col);
                if (p.percolates()) {
                    break;
                }
            }
            result[i] = ((double) j / ((double) (size * size)));
        }
    }
}
