package tetris.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    private Board b;

    public BoardTest() {
    }

    @Before
    public void setUp() {
        this.b = new Board();
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
        assertFalse(b.isPointEmpty(10, 2));
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
    
    @Test
    public void testGetPointPalauttaaNollaLaudanUlkoOikealta() {
        assertEquals(0, b.getPoint(10, 1));
    }
    
    @Test
    public void testGetPointPalauttaaNollaLaudanUlkoVasemmalta() {
        assertEquals(0, b.getPoint(-1, 1));
    }
    
    @Test
    public void testGetPointPalauttaaNollaLaudanUlkoAlhaalta() {
        assertEquals(0, b.getPoint(1, 21));
    }
    
    @Test
    public void testGetPointPalauttaaNollaLaudanUlkoYlhaalta() {
        assertEquals(0, b.getPoint(1, -1));
    }
    
    @Test
    public void testGetPointPalauttaaNollaKunPisteOnTyhjaJaLaudalla() {
        assertEquals(0, b.getPoint(2,2));
    }
    
    @Test
    public void testGetPointPalauttaaYksiKunPisteOnYksiJaLaudalla() {
        this.b.getBoard()[2][2] = 1;
        assertEquals(1, b.getPoint(2,2));
    }
    
    @Test
    public void testRemoveRowPoistaaRivinJaAsettaaTilalleYlemmanRivin() {
        this.b.getBoard()[2][2] = 2;
        this.b.getBoard()[1][2] = 1;
        this.b.removeRow(2);
        assertEquals(1, this.b.getPoint(2, 2));
        assertTrue(b.isRowEmpty(1));
    }
    
    @Test
    public void testRemoveRowAsettaaYlimmanRivinNollille() {
        this.b.getBoard()[0][2] = 1;
        this.b.removeRow(0);
        assertTrue(b.isRowEmpty(0));
    }
    
    @Test
    public void testRemoveFullRowsToimii() {
        for (int i = 0; i < b.getWidth(); i++) {
            b.getBoard()[2][i] = 1;
        }
        b.getBoard()[0][2] = 1;
        b.removeFullRows();
        assertTrue(b.isRowEmpty(2));
        assertEquals(0, b.getPoint(2, 0));
        assertEquals(1, b.getPoint(2, 1));
    }
}
