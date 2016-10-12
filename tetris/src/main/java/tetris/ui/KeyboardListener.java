package tetris.ui;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.logic.Game;

/**
 * Handels the presses of the keyboard made by the player.
 *
 * @author inka
 */
public class KeyboardListener implements KeyListener {

    private final Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.game.moveTetrimino(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.game.moveTetrimino(Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.game.moveTetrimino(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.game.moveTetrimino(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.game.moveTetrimino(null);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            this.game.pauseGame();
        } else if (e.getKeyCode() == KeyEvent.VK_F1) {
            this.game.initialize();
            this.game.start();
        }
        this.game.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
