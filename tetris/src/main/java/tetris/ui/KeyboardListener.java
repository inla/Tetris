package tetris.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.logic.Game;

/**
 *
 * @author inka
 */
public class KeyboardListener implements KeyListener {

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.game.rotate();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.game.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.game.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.game.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.game.dropDown();
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            this.game.pauseGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
