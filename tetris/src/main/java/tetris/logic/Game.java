package tetris.logic;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import tetris.ui.AbstractPanel;

/**
 * Handles the game logic.
 *
 * @author inka
 *
 */
public class Game extends Timer implements ActionListener {

    private final Board board;
    private Tetrimino fallingTetrimino;
    private Tetrimino nextTetrimino;
    private boolean gameOver;
    private boolean paused;
    private int score;
    private int level;
    private int removedRowsTotal;
    private AbstractPanel gamePanel;
    private AbstractPanel sidePanel;

    /**
     * Creates a new game.
     */
    public Game() {
        super(1000, null);
        this.board = new Board();
        addActionListener(this);

        initialize();
    }

    /**
     * Initializes the game by setting the values of the attributes to their
     * starting values.
     */
    public void initialize() {
        this.board.emptyBoard();
        this.fallingTetrimino = new Tetrimino(this.board);
        this.nextTetrimino = new Tetrimino(this.board);
        this.gameOver = false;
        this.paused = false;
        this.score = 0;
        this.level = 1;
        this.removedRowsTotal = 0;
        setDelay(1050 - level * 50);
    }

    /**
     * Calls the game and side panels' repaint-method.
     */
    public void repaint() {

        this.gamePanel.repaint();
        this.sidePanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    /**
     * Updates the game if it's not over or paused. Moves the falling tetrimino
     * down by one or, if that's not possible, calls the respawn-method.
     */
    public void update() {
        if (!isGameOver() && !isPaused()) {
            if (canContinue()) {
                moveTetrimino(Direction.DOWN);
            } else {
                respawn();
            }
        }
    }

    private void respawn() {
        this.board.addTetrimino(this.fallingTetrimino);
        this.fallingTetrimino = getNextTetrimino();
        this.nextTetrimino = new Tetrimino(board);
        int removed = this.board.removeFullRows();
        updateStats(removed);
        if (!canContinue()) {
            gameOver();
        }
    }

    private void updateStats(int removedRows) {
        if (removedRows > 0) {
            this.score += 100 * (removedRows * removedRows + (level - 1));
            this.removedRowsTotal += removedRows;
        }
        this.level = this.removedRowsTotal / 2 + 1;       // keksi parempi kaava?
        setDelay(Math.max(1050 - level * 150, 10));
    }

    private void gameOver() {
        this.gameOver = true;
        stop();
    }

    private boolean canContinue() {
        return this.fallingTetrimino.canMove(Direction.DOWN);
    }

    /**
     * Directs the moving command to the right method of tetrimino.
     *
     * @param direction direction to be moved
     */
    public void moveTetrimino(Direction direction) {
        if (!isGameOver() && !isPaused()) {
            if (direction == Direction.RIGHT) {
                this.fallingTetrimino.moveRight();
            } else if (direction == Direction.LEFT) {
                this.fallingTetrimino.moveLeft();
            } else if (direction == Direction.DOWN) {
                this.fallingTetrimino.moveDown();
            } else if (direction == Direction.UP) {
                this.fallingTetrimino.rotate();
            } else if (direction == null) {
                this.fallingTetrimino.dropDown();
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Tetrimino getFallingTetrimino() {
        return fallingTetrimino;
    }

    public Tetrimino getNextTetrimino() {
        return nextTetrimino;
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

    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets the game paused and stops timer or unpaused and starts timer.
     */
    public void pauseGame() {
        if (!isGameOver()) {
            if (isPaused()) {
                this.paused = false;
                start();
            } else {
                this.paused = true;
                stop();
            }
        }
    }

    public void setGamePanel(AbstractPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setSidePanel(AbstractPanel sidePanel) {
        this.sidePanel = sidePanel;
    }

    public Timer getTimer() {
        return this;
    }
}
