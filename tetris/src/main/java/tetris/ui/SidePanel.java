package tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.logic.Game;

/**
 * Panel that shows information of the game.
 *
 * @author inka
 */
public class SidePanel extends JPanel {

    private int squareSize;
    private Game game;

    /**
     * Creates the side panel.
     *
     * @param game current game
     * @param squareSize size of the squares
     */
    public SidePanel(Game game, int squareSize) {
        this.game = game;
        this.squareSize = squareSize;
        super.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        if (!this.game.isGameOver()) {
            paintPreview(g);
        }

        g.setColor(Color.LIGHT_GRAY);
        paintInfo(g);
    }

    private void paintPreview(Graphics g) {
        g.drawString("Next: ", squareSize, 2 * squareSize);
        paintTetrimino(g);
    }

    private void paintTetrimino(Graphics g) {
        int[][] tetr = this.game.getNextTetrimino().getCurrentRotation();
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] != 0) {
                    int x = this.game.getNextTetrimino().getX() + j;
                    int y = this.game.getNextTetrimino().getY() + i + 1;
                    Color c = this.game.getNextTetrimino().getColor();
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintPoint(Graphics g, int x, int y, Color c) {
        if (this.game.isPaused()) {
            c = c.darker().darker();
        }
        g.setColor(c);
        g.fill3DRect(x * squareSize, y * squareSize, squareSize, squareSize, true);
    }

    private void paintInfo(Graphics g) {
        g.drawString("Score: " + this.game.getScore(), squareSize, 6 * squareSize);

        g.drawString("Level: " + this.game.getLevel(), squareSize, 8 * squareSize);

        g.drawString("Controls:", squareSize, 11 * squareSize);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        g.drawString("F1            new game", squareSize, 12 * squareSize);
        g.drawString("P              pause/continue", squareSize, 13 * squareSize);
        g.drawString("↓              down", squareSize, 14 * squareSize);
        g.drawString("→              right", squareSize, 15 * squareSize);
        g.drawString("←              left", squareSize, 16 * squareSize);
        g.drawString("↑              rotate", squareSize, 17 * squareSize);
        g.drawString("spacebar  drop", squareSize, 18 * squareSize);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

}
