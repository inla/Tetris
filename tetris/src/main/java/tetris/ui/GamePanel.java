package tetris.ui;

import java.awt.Color;
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
    private Board board;
    private Tetrimino tetrimino;
    private final int SQUARE_SIZE;

    public GamePanel(Game game, int squaresize) {
        super.setBackground(Color.BLACK);
        this.game = game;
        this.board = this.game.getBoard();
        this.tetrimino = this.game.getTetrimino();
        this.SQUARE_SIZE = squaresize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintBackground(g);
        paintBoard(g);
        paintTetrimino(g);
        repaint();
    }

    private void paintBackground(Graphics g) {
        g.setColor(new Color(10, 10, 10));
        for (int i = 0; i < this.board.getHeight(); i++) {
            for (int j = 0; j < this.board.getWidth(); j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                } else if (i % 2 != 0 && j % 2 != 0) {
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    private void paintBoard(Graphics g) {
        for (int y = 0; y < this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
                if (!this.board.isPointEmpty(x, y)) {
                    Color c = this.board.getPointColor(x, y);
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintTetrimino(Graphics g) {
        int[][] tetr = this.game.getTetrimino().getCurrentRotation();
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] != 0) {
                    int x = this.tetrimino.getX() + i;
                    int y = this.tetrimino.getY() + j;
                    Color c = this.tetrimino.getColor();
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintPoint(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fill3DRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, true);
    }

//    @Override
//    public void repaint() {
//        super.repaint();
//    }
}