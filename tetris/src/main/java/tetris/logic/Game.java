package tetris.logic;

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

    }

    public void start() {
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    public void update() {
//        if (this.gameOver) {
//
//        }
        if (running) {
            if (this.tetrimino.canMoveDown()) {
                moveDown();
            } else {
                this.tetrimino.addToBoard();
                this.board.removeFullRows();
                this.tetrimino = getNextTetrimino();
                this.nextTetrimino = new Tetrimino(board);
            }
            this.gamePanel.repaint();
        }

    }

    public void moveRight() {
        this.tetrimino.moveRight();
    }

    public void moveLeft() {
        this.tetrimino.moveLeft();
    }

    public void moveDown() {
        this.tetrimino.moveDown();
    }

    public void rotate() {
        this.tetrimino.rotate();
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
