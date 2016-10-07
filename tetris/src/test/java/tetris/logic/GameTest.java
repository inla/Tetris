/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.ui.UI;

/**
 *
 * @author inka
 */
public class GameTest {
    private Game g;
    private Tetrimino t;
    private UI ui;
    
    public GameTest() {
    }
    
    @Before
    public void setUp() {
        this.g = new Game();
        this.t = g.getTetrimino();
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void testKonstruktoriToimii() {
         assertTrue(g.getBoard() != null);
         assertTrue(g.getTetrimino() != null);
         assertTrue(g.getNextTetrimino() != null);
         assertTrue(g.getScore()== 0);
         assertTrue(g.getRemovedRows() == 0);
         assertTrue(g.isRunning());
         assertFalse(g.isGameOver());
         assertFalse(g.isPaused());
     }
     
     @Test
     public void testTetriminoLiikkuuOikeinOikealle() {
         g.moveRight();
         assertEquals(g.getBoard().getWidth()/2, g.getTetrimino().getX());
     }
     
     @Test
     public void testTetriminoLiikkuuOikeinVasemmalle() {
         g.moveLeft();
         assertEquals(g.getBoard().getWidth()/2-2, g.getTetrimino().getX());
     }
     
     @Test
     public void testTetriminoLiikkuuOikeinAlas() {
         g.moveDown();
         assertEquals(1, g.getTetrimino().getY());
         
     }
     
     @Test
     public void testTetriminoPyoriiOikein() {
         int[][] next = t.getNextRotation();
         g.rotate();
         assertArrayEquals(next, g.getTetrimino().getCurrentRotation());
     }
     
     @Test
     public void testUpdateLiikuttaaTetriminoaYhdenAlasJosMahdollista() {
         int y = g.getTetrimino().getY();
         g.update();
         assertEquals(y+1, g.getTetrimino().getY());
     }
     
     @Test
     public void testUpdateEiTeeMitaanJosPause() {
         int y = g.getTetrimino().getY();
         g.pauseGame();
         g.update();
         assertEquals(y, g.getTetrimino().getY());
     }
     
     @Test
     public void testRespawnAsettaaSeuraavanTetriminonNykyiseksi() {
         Tetrimino next = g.getNextTetrimino();
         g.dropDown();
         g.update();
         assertEquals(next, g.getTetrimino());
     }
     
     @Test
     public void testPauseGameToimiiKunPausetetaan() {
         g.pauseGame();
         assertFalse(g.isRunning());
         assertTrue(g.isPaused());
     }
     
     @Test
     public void testPauseGameToimiiKunPausetetaanPois() {
         g.pauseGame();
         g.pauseGame();
         assertTrue(g.isRunning());
         assertFalse(g.isPaused());
     }
}
