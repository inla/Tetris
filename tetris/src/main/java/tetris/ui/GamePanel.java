package tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.logic.Board;
import tetris.logic.Game;
import tetris.logic.Tetrimino;

/**
 * Draws the current situation of the game.
 *
 * @author inka
 */
public class GamePanel extends JPanel {

    private Game game;
    private final int SQUARE_SIZE;

    public GamePanel(Game game, int squaresize) {
        super.setBackground(Color.BLACK);
        this.game = game;
        this.SQUARE_SIZE = squaresize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintBackground(g);
        paintBoard(g);
        paintTetrimino(g);
        if (this.game.isGameOver()) {
            paintGameOver(g);
        }
    }

    private void paintBackground(Graphics g) {
        g.setColor(new Color(10, 10, 10));
        for (int i = 0; i < this.game.getBoard().getHeight(); i++) {
            for (int j = 0; j < this.game.getBoard().getWidth(); j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                } else if (i % 2 != 0 && j % 2 != 0) {
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    private void paintBoard(Graphics g) {
        for (int y = 0; y < this.game.getBoard().getHeight(); y++) {
            for (int x = 0; x < this.game.getBoard().getWidth(); x++) {
                if (!this.game.getBoard().isPointEmpty(x, y)) {
                    Color c = this.game.getBoard().getPointColor(x, y);
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintTetrimino(Graphics g) {
        int[][] tetr = this.game.getFallingTetrimino().getCurrentRotation();
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] != 0) {
                    int x = this.game.getFallingTetrimino().getX() + i;
                    int y = this.game.getFallingTetrimino().getY() + j;
                    Color c = this.game.getFallingTetrimino().getColor();
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintPoint(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fill3DRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, true);
    }

    private void paintGameOver(Graphics g) {
        int x = (this.game.getBoard().getWidth() * SQUARE_SIZE) / 4 - SQUARE_SIZE;
        int y = (this.game.getBoard().getHeight() * SQUARE_SIZE) / 2 - SQUARE_SIZE;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 60));
        g.drawString("GAME", x, y);
        g.drawString("OVER", x, y + SQUARE_SIZE * 3);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

}
