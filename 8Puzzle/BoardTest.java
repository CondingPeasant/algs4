import static org.junit.Assert.*;

import org.junit.Test;

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
        Board b2 = new Board(blockGoal_3D);
        assertEquals(b2.manhattan(), 0);
    }

    @Test
    public void testHamming() {
        Board b1 = new Board(block1);
        assertEquals(b1.hamming(), 5);
        Board b2 = new Board(blockGoal_3D);
        assertEquals(b2.hamming(), 0);
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
        assertEquals(b1.twin(), b2);
    }
}
