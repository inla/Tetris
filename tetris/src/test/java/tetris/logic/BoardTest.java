package tetris.logic;

import java.awt.Color;
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
    public void testAlussaLautaTyhja() {
        for (int i = 0; i < b.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                assertEquals(0, this.b.getBoard()[i][j]);
            }
        }
    }

    @Test
    public void testOnkoPisteTyhjaLaudanUlkopuolellaOikealla() {
        assertFalse(b.isPointEmpty(-1, 2));
    }

    @Test
    public void testOnkoPisteTyhjaLaudanUlkopuolellaVasemmalla() {
        assertFalse(b.isPointEmpty(10, 2));
    }

    @Test
    public void testOnkoPisteTyhjaLaudanUlkopuolellaAlhaalla() {
        assertFalse(b.isPointEmpty(2, 21));
    }

    @Test
    public void testOnkoPisteTyhjaLaudanUlkopuolellaYlhaalla() {
        assertTrue(b.isPointEmpty(2, -1));
    }

    @Test
    public void testOnkoPisteTyhjaLaudanRajoilla() {
        assertTrue(b.isPointEmpty(0, 0));
        assertTrue(b.isPointEmpty(0, 19));
        assertTrue(b.isPointEmpty(9, 0));
        assertTrue(b.isPointEmpty(9, 19));
    }

    @Test
    public void testOnkoPisteTyhjaLaudanSisapuolellaKunEiPalikkaa() {
        assertTrue(b.isPointEmpty(2, 2));
    }

    @Test
    public void testOnkoPisteTyhjaLaudanSisapuolellaKunOnPalikka() {
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
        assertEquals(0, b.getPoint(2, 2));
    }

    @Test
    public void testGetPointPalauttaaYksiKunPisteOnYksiJaLaudalla() {
        this.b.getBoard()[2][2] = 1;
        assertEquals(1, b.getPoint(2, 2));
    }

    @Test
    public void testRemoveRowPoistaaRivinJaAsettaaTilalleYlemmanRivin() {
        this.b.getBoard()[2][2] = 2;
        this.b.getBoard()[1][2] = 1;
        this.b.removeRow(2);
        assertEquals(1, this.b.getPoint(2, 2));
        for (int i = 0; i < b.getWidth(); i++) {
            assertEquals(0, b.getBoard()[1][i]);
        }
    }

    @Test
    public void testRemoveRowAsettaaYlimmanRivinNollille() {
        this.b.getBoard()[0][2] = 1;
        this.b.removeRow(0);
        for (int i = 0; i < b.getWidth(); i++) {
            assertEquals(0, b.getBoard()[0][i]);
        }
    }

    @Test
    public void testRemoveRowEiTeeMitaanJosYLaudanUlkopuolella() {
        this.b.setPoint(1, 1, 1);
        this.b.removeRow(-1);
        this.b.removeRow(20);
        assertEquals(1, this.b.getPoint(1, 1));
    }

    @Test
    public void testRemoveFullRowsToimii() {
        for (int i = 0; i < b.getWidth(); i++) {
            b.getBoard()[2][i] = 1;
        }
        b.getBoard()[0][2] = 1;
        int montakoPoistettua = b.removeFullRows();
        for (int i = 0; i < b.getWidth(); i++) {
            assertEquals(0, b.getBoard()[2][i]);
        }
        assertEquals(0, b.getPoint(2, 0));
        assertEquals(1, b.getPoint(2, 1));
        assertEquals(1, montakoPoistettua);
    }

    @Test
    public void testEmptyBoardToimii() {
        b.setPoint(1, 1, 1);
        b.setPoint(2, 2, 2);
        b.emptyBoard();
        for (int i = 0; i < b.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                assertEquals(0, b.getPoint(i, j));
            }
        }
    }

    @Test
    public void testAddTetriminoToimii() {
        Tetrimino t = new Tetrimino(b);
        Color c = t.getColor();
        b.addTetrimino(t);
        for (int i = 0; i < t.getCurrentRotation().length; i++) {
            for (int j = 0; j < t.getCurrentRotation()[i].length; j++) {
                if (t.getCurrentRotation()[i][j] == 0) {
                    continue;
                }
                int x = j + t.getX();
                int y = i + t.getY();
                assertEquals(c, b.getPointColor(x, y));
            }
        }
    }

    @Test
    public void testGetPointColorPalauttaaOikeinCyan() {
        b.setPoint(1, 1, 1);
        assertEquals(Color.CYAN, b.getPointColor(1, 1));
    }

    @Test
    public void testGetPointColorPalauttaaOikeinBlue() {
        b.setPoint(2, 2, 2);
        assertEquals(Color.BLUE, b.getPointColor(2, 2));
    }

    @Test
    public void testGetPointColorPalauttaaOikeinOmaOranssi() {
        b.setPoint(3, 3, 3);
        assertEquals(new Color(255, 137, 0), b.getPointColor(3, 3));
    }

    @Test
    public void testGetPointColorPalauttaaOikeinYellow() {
        b.setPoint(4, 4, 4);
        assertEquals(Color.YELLOW, b.getPointColor(4, 4));
    }

    @Test
    public void testGetPointColorPalauttaaOikeinGreen() {
        b.setPoint(5, 5, 5);
        assertEquals(Color.GREEN, b.getPointColor(5, 5));
    }

    @Test
    public void testGetPointColorPalauttaaOikeinMagenta() {
        b.setPoint(6, 6, 6);
        assertEquals(Color.MAGENTA, b.getPointColor(6, 6));
    }

    @Test
    public void testGetPointColorPalauttaaOikeinPunainen() {
        b.setPoint(7, 7, 7);
        assertEquals(Color.RED, b.getPointColor(7, 7));
    }

    @Test
    public void testGetPointColorPalauttaaOikeinNull() {
        assertEquals(null, b.getPointColor(0, 0));
    }
}
