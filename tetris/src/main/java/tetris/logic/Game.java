package tetris.logic;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import tetris.ui.GamePanel;

/**
 * Handels the game logic.
 *
 * @author inka
 *
 */
public class Game implements ActionListener {

    private Board board;
    private Tetrimino tetrimino;
    private Tetrimino nextTetrimino;
    private boolean gameOver;
    private boolean paused;
    private boolean running;
    private Timer timer;
    private int removedRows;
    private int score;
    private int level;
    private GamePanel gamePanel;

    /**
     * Creates a new game.
     */
    public Game() {
        this.board = new Board();
        this.tetrimino = new Tetrimino(this.board);
        this.nextTetrimino = new Tetrimino(this.board);
        this.gameOver = false;
        this.paused = false;
        this.running = true;
        this.timer = new Timer(1000, this);
        this.removedRows = 0;
        this.score = 0;
        this.level = 1;

    }

    /**
     * Starts the timer.
     */
    public void start() {
        this.timer.start();
    }

    /**
     * Calls the game panels repaint-method.
     */
    public void repaint() {
        this.gamePanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        this.gamePanel.repaint();
    }

    /**
     * Updates game.
     */
    public void update() {
        if (this.gameOver) {
            return;
        }
        if (running) {
            if (this.tetrimino.canMove(Direction.DOWN)) {
                moveDown();
            } else {
                respawn();
            }
        }

    }

    private void respawn() {
        this.tetrimino.addToBoard();
        this.tetrimino = getNextTetrimino();
        this.nextTetrimino = new Tetrimino(board);
        int removed = this.board.removeFullRows();
        this.score += 100 * removed * level;            //keksi parempi kaava?
        this.removedRows += removed;
        this.level = this.score / 10000 + 1;            // ^

        if (!this.tetrimino.canMove(Direction.DOWN)) {
            this.gameOver = true;
        }
    }

    /**
     * Calls tetriminos moveRight()-method and repaints the game.
     */
    public void moveRight() {
        if (running) {
            this.tetrimino.moveRight();
        }
    }

    /**
     * Calls tetriminos moveLeft()-method and repaints the game.
     */
    public void moveLeft() {
        if (running) {
            this.tetrimino.moveLeft();
        }
    }

    /**
     * Calls tetriminos moveDown()-method and repaints the game.
     */
    public void moveDown() {
        if (running) {
            this.tetrimino.moveDown();
        }
    }

    /**
     * Calls tetriminos dropDown()-method and repaints the game.
     */
    public void dropDown() {
        if (running) {
            this.tetrimino.dropDown();
        }
    }

    /**
     * Calls tetriminos rotate()-method and repaints the game.
     */
    public void rotate() {
        if (running) {
            this.tetrimino.rotate();
        }
    }

    public Board getBoard() {
        return board;
    }

    public Tetrimino getTetrimino() {
        return tetrimino;
    }

    public Tetrimino getNextTetrimino() {
        return nextTetrimino;
    }

    public int getRemovedRows() {
        return removedRows;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets the game paused or running.
     */
    public void pauseGame() {
        if (running) {
            this.paused = true;
            this.running = false;

        } else {
            this.running = true;
            this.paused = false;
        }

    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
