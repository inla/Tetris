package tetris.ui;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.tetris.Game;

/**
 * Handles the presses of the keyboard made by the player. Directs them to the
 * right methods of game class.
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.game.moveTetrimino(Direction.UP);
                break;
            case KeyEvent.VK_RIGHT:
                this.game.moveTetrimino(Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                this.game.moveTetrimino(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                this.game.moveTetrimino(Direction.LEFT);
                break;
            case KeyEvent.VK_SPACE:
                this.game.moveTetrimino(null);
                break;
            case KeyEvent.VK_P:
                this.game.pauseGame();
                break;
            case KeyEvent.VK_F1:
                this.game.initialize();
                this.game.restart();
                break;
            default:
                break;
        }
        this.game.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
