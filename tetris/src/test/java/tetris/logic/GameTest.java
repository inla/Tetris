/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logic;

import javax.swing.SwingUtilities;
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
        this.ui = new UI(g);
        SwingUtilities.invokeLater(ui);
        while (ui.getGamePanel() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("hmm..");
            }
        }
        this.g.setGamePanel(ui.getGamePanel());
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
     public void testRespawnToimii() {
         
     }
}
