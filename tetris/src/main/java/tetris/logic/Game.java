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

    public void start() {
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        this.gamePanel.repaint();
    }

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
        this.tetrimino.moveToStartingPoint();
        this.nextTetrimino = new Tetrimino(board);
        int removed = this.board.removeFullRows();
        this.score += 100 * removed * level;            //keksi parempi kaava?
        this.removedRows += removed;
        this.level = this.score / 10000 + 1;            // ^

        if (!this.tetrimino.canMove(Direction.DOWN)) {
            this.gameOver = true;
        }
    }

    public void moveRight() {
        if (running) {
            this.tetrimino.moveRight();
            this.gamePanel.repaint();
        }
    }

    public void moveLeft() {
        if (running) {
            this.tetrimino.moveLeft();
            this.gamePanel.repaint();
        }
    }

    public void moveDown() {
        if (running) {
            this.tetrimino.moveDown();
            this.gamePanel.repaint();
        }
    }

    public void dropDown() {
        if (running) {
            this.tetrimino.dropDown();
            this.gamePanel.repaint();
        }
    }

    public void rotate() {
        if (running) {
            this.tetrimino.rotate();
            this.gamePanel.repaint();
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
