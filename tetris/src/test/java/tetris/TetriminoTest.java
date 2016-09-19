/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.tetris.Tetrimino;
import tetris.tetris.TetriminoType;

/**
 *
 * @author inka
 */
public class TetriminoTest {
    Tetrimino i;
    Tetrimino j;
    Tetrimino l;
    Tetrimino o;
    Tetrimino s;
    Tetrimino t;
    Tetrimino z;
    
    public TetriminoTest() {
    }

    @Before
    public void setUp() {
        this.i = new Tetrimino(TetriminoType.I);
        this.j = new Tetrimino(TetriminoType.J);
        this.l = new Tetrimino(TetriminoType.L);
        this.o = new Tetrimino(TetriminoType.O);
        this.s = new Tetrimino(TetriminoType.S);
        this.t = new Tetrimino(TetriminoType.T);
        this.z = new Tetrimino(TetriminoType.Z);
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
}
