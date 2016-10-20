package tetris.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.tetris.Game;

/**
 * This abstract class contains methods that both of it's subclasses, GamePanel
 * and SidePanel, need.
 *
 * @author inka
 */
public abstract class AbstractPanel extends JPanel {

    private final Game game;
    private final int squareSize;

    /**
     * Constructor.
     *
     * @param game current game
     * @param squareSize size of the squares to draw
     */
    public AbstractPanel(Game game, int squareSize) {
        this.game = game;
        this.squareSize = squareSize;
    }

    /**
     * Abstract method for painting tetrimino. Subclasses implementations of
     * this method are slightly different from each other.
     *
     * @param g object that contains methods for drawing
     */
    protected abstract void paintTetrimino(Graphics g);

    /**
     * Paints the given coordinate point with given color.
     *
     * @param g object that contains methods for drawing
     * @param x coordinate x
     * @param y coordinate y
     * @param c color which should be used
     */
    protected void paintPoint(Graphics g, int x, int y, Color c) {
        if (this.game.isPaused() || this.game.isGameOver()) {
            c = c.darker().darker();
        }
        g.setColor(c);
        g.fill3DRect(x * squareSize, y * squareSize, squareSize, squareSize, true);
    }

    public Game getGame() {
        return game;
    }

    public int getSquareSize() {
        return squareSize;
    }

}
