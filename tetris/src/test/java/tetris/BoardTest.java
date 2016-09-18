package tetris;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.tetris.Board;

/**
 *
 * @author inka
 */
public class BoardTest {
    private Board b;

    public BoardTest() {
        this.b = new Board(20, 10);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testKonstruktoriAsettaaKorkeudenOikein() {
        assertEquals(20, b.getHeight());
        assertEquals(20, b.getBoard().length);
    }

    @Test
    public void testKonstruktoriAsettaaLeveydenOikein() {
        assertEquals(10, b.getWidth());
        assertEquals(10, b.getBoard()[0].length);
    }
    
    @Test
    public void testAlussaLautaTyhja(){
        for (int i = 0; i < b.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                assertEquals(0, this.b.getBoard()[i][j]);
            }
        }
    }
    
    @Test
    public void testOnkoPisteTyhjaLaudanUlkopuolellaOikealla(){
        assertFalse(b.isPointEmpty(-1, 2));
    }
    
    @Test
    public void testOnkoPisteTyhjaLaudanUlkopuolellaVasemmalla(){
        assertFalse(b.isPointEmpty(11, 2));
    }
    
    @Test
    public void testOnkoPisteTyhjaLaudanUlkopuolellaAlhaalla(){
        assertFalse(b.isPointEmpty(2, 21));
    }
    
    @Test
    public void testOnkoPisteTyhjaLaudanSisapuolellaKunEiPalikkaa(){
        assertTrue(b.isPointEmpty(2, 2));
    }
    
    @Test
    public void testOnkoPisteTyhjaLaudanSisapuolellaKunOnPalikka(){
        b.getBoard()[1][1] = 1;
        assertFalse(b.isPointEmpty(1, 1));
    }
    
    @Test
    public void testOnkoRiviTaysiPalauttaaTrueKunTaysi() {
        for (int i = 0; i < b.getBoard()[1].length; i++) {
            b.getBoard()[0][i] = 1;
        }
        assertTrue(b.isRowFull(0));
    }

    @Test
    public void testOnkoRiviTaysiPalauttaaFalseKunEiTaysi() {
        for (int i = 0; i < b.getBoard()[1].length; i++) {
            b.getBoard()[0][i] = 1;
        }
        b.getBoard()[0][1] = 0;
        assertFalse(b.isRowFull(0));
    }
    
    @Test
    public void testOnkoRiviTyhjaPalauttaaTrueKunTyhja() {
        assertTrue(b.isRowEmpty(0));
    }
    
    @Test
    public void testOnkoRiviTyhjaPalauttaaFalseKunEiTyhja() {
        b.getBoard()[0][1] = 1;
        assertFalse(b.isRowEmpty(0));
    }
}
