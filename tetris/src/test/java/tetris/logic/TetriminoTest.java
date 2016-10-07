/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author inka
 */
public class TetriminoTest {

    private Board b;
    private Tetrimino tetr;
    private Tetrimino i;
    private Tetrimino j;
    private Tetrimino l;
    private Tetrimino o;
    private Tetrimino s;
    private Tetrimino t;
    private Tetrimino z;

    public TetriminoTest() {
    }

    @Before
    public void setUp() {
        this.b = new Board();
        this.tetr = new Tetrimino(b);
        this.i = new Tetrimino(b, TetriminoType.I);
        this.j = new Tetrimino(b, TetriminoType.J);
        this.l = new Tetrimino(b, TetriminoType.L);
        this.o = new Tetrimino(b, TetriminoType.O);
        this.s = new Tetrimino(b, TetriminoType.S);
        this.t = new Tetrimino(b, TetriminoType.T);
        this.z = new Tetrimino(b, TetriminoType.Z);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testKonstruktoriAsettaaOikeatRotaatiotI() {
        assertTrue(this.i.getTetriminoRotations() == TetriminoType.I.getRotations());
    }

    @Test
    public void testKonstruktoriAsettaaOikeatRotaatiotJ() {
        assertTrue(this.j.getTetriminoRotations() == TetriminoType.J.getRotations());
    }

    @Test
    public void testKonstruktoriAsettaaOikeatRotaatiotL() {
        assertTrue(this.l.getTetriminoRotations() == TetriminoType.L.getRotations());
    }

    @Test
    public void testKonstruktoriAsettaaOikeatRotaatiotO() {
        assertTrue(this.o.getTetriminoRotations() == TetriminoType.O.getRotations());
    }

    @Test
    public void testKonstruktoriAsettaaOikeatRotaatiotS() {
        assertTrue(this.s.getTetriminoRotations() == TetriminoType.S.getRotations());
    }

    @Test
    public void testKonstruktoriAsettaaOikeatRotaatiotT() {
        assertTrue(this.t.getTetriminoRotations() == TetriminoType.T.getRotations());
    }

    @Test
    public void testKonstruktoriAsettaaOikeatRotaatiotZ() {
        assertTrue(this.z.getTetriminoRotations() == TetriminoType.Z.getRotations());
    }

    @Test
    public void testTetriminoLiikkuuOikeinOikealle() {
        this.tetr.moveRight();
        assertEquals(b.getWidth() / 2, tetr.getX());
    }

    @Test
    public void testTetriminoLiikkuuOikeinVasemmalle() {
        tetr.moveLeft();
        assertEquals(b.getWidth() / 2 - 2, tetr.getX());
    }

    @Test
    public void testTetriminoLiikkuuOikeinAlas() {
        tetr.moveDown();
        assertEquals(1, tetr.getY());

    }

    @Test
    public void testTetriminoPutoaaOikein() {
        tetr.dropDown();
        assertEquals(b.getHeight() - tetr.getCurrentRotation().length, this.tetr.getY());
    }

    @Test
    public void testTetriminoPyoriiOikein() {
        int[][] next = tetr.getNextRotation();
        tetr.rotate();
        assertArrayEquals(next, tetr.getCurrentRotation());
    }

    @Test
    public void testIsInsideBordersPalauttaaFalseKunPisteEiRajojenSisalla() {
        assertFalse(t.isInsideBorders(-1, 1));
        assertFalse(t.isInsideBorders(-1, -1));
        assertFalse(t.isInsideBorders(11, 1));
        assertFalse(t.isInsideBorders(11, 21));
    }

    @Test
    public void testIsInsideBordersPalauttaaTrueKunPisteOnRajojenSisalla() {
        assertTrue(t.isInsideBorders(1, 1));
    }

    @Test
    public void testIsInsideBordersPalauttaaTrueKunPisteOnRajalla() {
        assertTrue(t.isInsideBorders(0, 0));
        assertTrue(t.isInsideBorders(9, 19));
        assertTrue(t.isInsideBorders(9, 0));
        assertTrue(t.isInsideBorders(0, 19));
    }
}
