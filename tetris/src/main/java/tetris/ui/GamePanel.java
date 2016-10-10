package tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.logic.Game;

/**
 * Draws the current situation of the game.
 *
 * @author inka
 */
public class GamePanel extends JPanel {

    private Game game;
    private int squareSize;

    /**
     * Creates the game panel.
     *
     * @param game current game
     * @param squareSize size of the squares
     */
    public GamePanel(Game game, int squareSize) {
        this.game = game;
        this.squareSize = squareSize;
        super.setBackground(Color.BLACK);
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
        if (this.game.isPaused()) {
            paintPaused(g);
        }
    }

    private void paintBackground(Graphics g) {
        g.setColor(new Color(7, 7, 7));
        for (int i = 0; i < this.game.getBoard().getHeight(); i++) {
            for (int j = 0; j < this.game.getBoard().getWidth(); j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);
                } else if (i % 2 != 0 && j % 2 != 0) {
                    g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);
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
                    int x = this.game.getFallingTetrimino().getX() + j;
                    int y = this.game.getFallingTetrimino().getY() + i;
                    Color c = this.game.getFallingTetrimino().getColor();
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintPoint(Graphics g, int x, int y, Color c) {
        if (this.game.isGameOver() || this.game.isPaused()) {
            c = c.darker().darker();
        }
        g.setColor(c);
        g.fill3DRect(x * squareSize, y * squareSize, squareSize, squareSize, true);
    }

    private void paintGameOver(Graphics g) {
        int x = squareSize;
        int y = (this.game.getBoard().getHeight() * squareSize) / 2 - squareSize;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 60));
        g.drawString("GAME", x, y);
        g.drawString("OVER", x + 4, y + squareSize * 3);
    }

    private void paintPaused(Graphics g) {
        g.setColor(Color.LIGHT_GRAY.darker());
        g.setFont(new Font("Tahoma", Font.BOLD, 50));
        g.drawString("PAUSED", squareSize / 3, 9 * squareSize);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

}
