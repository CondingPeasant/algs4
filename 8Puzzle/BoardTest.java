import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class BoardTest {
    private int[][] blockGoal_2D = {{0, 0, 0},
                                    {0, 1, 2},
                                    {0, 3, 0}};
    private int[][] blockGoal_3D = {{0, 0, 0, 0},
                                    {0, 1, 2, 3},
                                    {0, 4, 5, 6},
                                    {0, 7, 8, 0}};

    private int[][] block1 = {{0, 0, 0, 0},
                              {0, 8, 1, 3},
                              {0, 4, 0, 2},
                              {0, 7, 6, 5}};
    private int[][] block1_twin = {{0, 0, 0, 0},
                                   {0, 8, 1, 3},
                                   {0, 4, 0, 6},
                                   {0, 7, 2, 5}};
    private int[][] block1_neighbor1 = {{0, 0, 0, 0},
                                        {0, 8, 0, 3},
                                        {0, 4, 1, 2},
                                        {0, 7, 6, 5}};
    private int[][] block1_neighbor2 = {{0, 0, 0, 0},
                                        {0, 8, 1, 3},
                                        {0, 0, 4, 2},
                                        {0, 7, 6, 5}};
    private int[][] block1_neighbor3 = {{0, 0, 0, 0},
                                        {0, 8, 1, 3},
                                        {0, 4, 6, 2},
                                        {0, 7, 0, 5}};
    private int[][] block1_neighbor4 = {{0, 0, 0, 0},
                                        {0, 8, 1, 3},
                                        {0, 4, 2, 0},
                                        {0, 7, 6, 5}};

    private int[][] block2 = {{0, 0, 0, 0},
                              {0, 8, 1, 3},
                              {0, 4, 5, 2},
                              {0, 7, 6, 0}};
    private int[][] block2_twin = {{0, 0, 0, 0},
                                   {0, 8, 1, 7},
                                   {0, 4, 5, 2},
                                   {0, 3, 6, 0}};
    private int[][] block2_neighbor1 = {{0, 0, 0, 0},
                                        {0, 8, 1, 3},
                                        {0, 4, 5, 0},
                                        {0, 7, 6, 2}};
    private int[][] block2_neighbor2 = {{0, 0, 0, 0},
                                        {0, 8, 1, 3},
                                        {0, 4, 5, 2},
                                        {0, 7, 0, 6}};

    private int[][] block3 = {{0, 0, 0, 0},
                              {0, 8, 0, 3},
                              {0, 4, 5, 2},
                              {0, 7, 6, 1}};
    private int[][] block3_neighbor1 = {{0, 0, 0, 0},
                                        {0, 0, 8, 3},
                                        {0, 4, 5, 2},
                                        {0, 7, 6, 1}};
    private int[][] block3_neighbor2 = {{0, 0, 0, 0},
                                        {0, 8, 5, 3},
                                        {0, 4, 0, 2},
                                        {0, 7, 6, 1}};
    private int[][] block3_neighbor3 = {{0, 0, 0, 0},
                                        {0, 8, 3, 0},
                                        {0, 4, 5, 2},
                                        {0, 7, 6, 1}};
    @Test
    public void testBoard() {
        try {
            Board b = new Board(null);
            fail("A NullPointerException should be thrown.");
        } catch (NullPointerException aNullPointerException) {
        }
    }

    @Test
    public void testDimension() {
        Board b1 = new Board(blockGoal_2D);
        assertEquals(b1.dimension(), 2);
        Board b2 = new Board(blockGoal_3D);
        assertEquals(b2.dimension(), 3);
    }

    @Test
    public void testIsGoal() {
        Board b1 = new Board(blockGoal_2D);
        assertTrue(b1.isGoal());
        Board b2 = new Board(blockGoal_3D);
        assertTrue(b2.isGoal());
        Board b3 = new Board(block1);
        assertFalse(b3.isGoal());
    }

    @Test
    public void testManhattan() {
        Board b1 = new Board(block1);
        assertEquals(b1.manhattan(), 10);
        Board b2 = new Board(block2);
        assertEquals(b2.manhattan(), 8);
        Board b3 = new Board(block3);
        assertEquals(b3.manhattan(), 11);
        Board b4 = new Board(blockGoal_3D);
        assertEquals(b4.manhattan(), 0);
    }

    @Test
    public void testHamming() {
        Board b1 = new Board(block1);
        assertEquals(b1.hamming(), 5);
        Board b2 = new Board(block2);
        assertEquals(b2.hamming(), 4);
        Board b3 = new Board(block3);
        assertEquals(b3.hamming(), 4);
        Board b4 = new Board(blockGoal_3D);
        assertEquals(b4.hamming(), 0);
    }

    @Test
    public void testEquals() {
        Board b1 = new Board(block1);
        assertTrue(b1.equals(b1));
        Board b2 = new Board(block1_twin);
        assertFalse(b1.equals(b2));
        Board b3 = new Board(blockGoal_2D);
        Board b4 = new Board(blockGoal_3D);
        assertTrue(b3.equals(b3));
        assertFalse(b3.equals(b4));
        assertFalse(b4.equals(b3));
    }

    @Test
    public void testTwin() {
        Board b1 = new Board(block1);
        Board b2 = new Board(block1_twin);
        Board b3 = new Board(block2);
        Board b4 = new Board(block2_twin);
        assertTrue(b1.twin().equals(b2));
        assertTrue(b3.twin().equals(b4));
    }

    @Test
    public void testNeighbors() {
        Board b1 = new Board(block1);
        Board b2 = new Board(block2);
        Board b3 = new Board(block3);
        Iterator<Board> it1 = b1.neighbors().iterator();
        Iterator<Board> it2 = b2.neighbors().iterator();
        Iterator<Board> it3 = b3.neighbors().iterator();

        assertTrue(it1.next().equals(new Board(block1_neighbor1)));
        assertTrue(it1.next().equals(new Board(block1_neighbor2)));
        assertTrue(it1.next().equals(new Board(block1_neighbor3)));
        assertTrue(it1.next().equals(new Board(block1_neighbor4)));
        assertFalse(it1.hasNext());

        assertTrue(it2.next().equals(new Board(block2_neighbor1)));
        assertTrue(it2.next().equals(new Board(block2_neighbor2)));
        assertFalse(it2.hasNext());

        assertTrue(it3.next().equals(new Board(block3_neighbor1)));
        assertTrue(it3.next().equals(new Board(block3_neighbor2)));
        assertTrue(it3.next().equals(new Board(block3_neighbor3)));
        assertFalse(it3.hasNext());
    }
}
