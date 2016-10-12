package tetris.logic;

import com.sun.javafx.scene.traversal.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author inka
 */
public class GameTest {

    private Game g;
    private Tetrimino t;

    public GameTest() {
    }

    @Before
    public void setUp() {
        this.g = new Game();
        this.t = g.getFallingTetrimino();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testKonstruktoriAlustuksetToimii() {
        assertTrue(g.getBoard() != null);
        assertTrue(g.getFallingTetrimino() != null);
        assertTrue(g.getNextTetrimino() != null);
        assertEquals(0, g.getScore());
        assertEquals(1, g.getLevel());
        assertFalse(g.isGameOver());
        assertFalse(g.isPaused());
    }

    @Test
    public void testTetriminoLiikkuuOikeinOikealle() {
        g.moveTetrimino(Direction.RIGHT);
        assertEquals(g.getBoard().getWidth() / 2, g.getFallingTetrimino().getX());
    }

    @Test
    public void testTetriminoLiikkuuOikeinVasemmalle() {
        g.moveTetrimino(Direction.LEFT);
        assertEquals(g.getBoard().getWidth() / 2 - 2, g.getFallingTetrimino().getX());
    }

    @Test
    public void testTetriminoLiikkuuOikeinAlas() {
        g.moveTetrimino(Direction.DOWN);
        assertEquals(1, g.getFallingTetrimino().getY());

    }

    @Test
    public void testTetriminoPyoriiOikein() {
        int[][] next = t.getNextRotation();
        g.moveTetrimino(Direction.UP);
        assertArrayEquals(next, g.getFallingTetrimino().getCurrentRotation());
    }

    @Test
    public void testUpdateLiikuttaaTetriminoaYhdenAlasJosMahdollista() {
        int y = g.getFallingTetrimino().getY();
        g.update();
        assertEquals(y + 1, g.getFallingTetrimino().getY());
    }

    @Test
    public void testUpdateEiTeeMitaanJosPause() {
        int y = g.getFallingTetrimino().getY();
        g.pauseGame();
        g.update();
        assertEquals(y, g.getFallingTetrimino().getY());
    }

    @Test
    public void testRespawnAsettaaSeuraavanTetriminonNykyiseksi() {
        Tetrimino next = g.getNextTetrimino();
        g.moveTetrimino(null);
        g.update();
        assertEquals(next, g.getFallingTetrimino());
    }

    @Test
    public void testPauseGameToimiiKunPausetetaan() {
        g.pauseGame();
        assertTrue(g.isPaused());
        assertFalse(g.getTimer().isRunning());
    }

    @Test
    public void testPauseGameToimiiKunPausetetaanPois() {
        g.pauseGame();
        g.pauseGame();
        assertFalse(g.isPaused());
        assertTrue(g.getTimer().isRunning());
    }

    @Test
    public void testTimerKaynnistyyOikein() {
        g.start();
        assertTrue(g.getTimer().isRunning());
    }

    @Test
    public void testGameOverStoppaaTimerin() {
        while (!g.isGameOver()) {
            g.moveTetrimino(null);
            g.update();
        }
        assertTrue(g.isGameOver());
        assertFalse(g.getTimer().isRunning());
    }
}
