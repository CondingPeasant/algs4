import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class SolverTest {
    private int[][] block1 = {{0, 0, 0, 0},
                              {0, 1, 2, 3},
                              {0, 4, 0, 6},
                              {0, 7, 5, 8}};
    private int[][] block2 = {{0, 0, 0, 0},
                              {0, 4, 1, 2},
                              {0, 7, 5, 3},
                              {0, 8, 0, 6}};
    private int[][] block3 = {{0, 0, 0, 0},
                              {0, 8, 1, 3},
                              {0, 4, 0, 2},
                              {0, 7, 6, 5}};
    private int[][] block4 = {{0, 0, 0, 0},
                              {0, 1, 2, 3},
                              {0, 4, 5, 6},
                              {0, 8, 7, 0}};
    @Test
    public void testSovler() {
//        Board b1 = new Board(block1);
//        Solver s1 = new Solver(b1);
//        for (Board b : s1.solution()) {
//            StdOut.println(b);
//        }
//        assertEquals(s1.moves(), 2);
//
//        Board b2 = new Board(block2);
//        Solver s2 = new Solver(b2);
//        for (Board b : s2.solution()) {
//            StdOut.println(b);
//        }
//        assertEquals(s2.moves(), 7);

        Board b3 = new Board(block3);
        Solver s3 = new Solver(b3);
        for (Board b : s3.solution()) {
            StdOut.println(b);
        }
        assertTrue(s3.isSolvable());

        Board b4 = new Board(block4);
        Solver s4 = new Solver(b4);
        assertFalse(s4.isSolvable());
    }

}
