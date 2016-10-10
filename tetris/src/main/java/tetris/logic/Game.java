package tetris.logic;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import tetris.ui.GamePanel;
import tetris.ui.SidePanel;

/**
 * Handels the game logic.
 *
 * @author inka
 *
 */
public class Game implements ActionListener {

    private Board board;
    private Tetrimino fallingTetrimino;
    private Tetrimino nextTetrimino;
    private boolean gameOver;
    private boolean paused;
    private boolean running;
    private Timer timer;
    private int score;
    private int level;
    private GamePanel gamePanel;
    private SidePanel sidePanel;

    /**
     * Creates a new game.
     */
    public Game() {
        initialize();
    }

    /**
     * Starts the timer.
     */
    public void start() {
        this.timer.start();
    }

    /**
     * Initializes the game by setting the values of the attributes to their
     * starting values.
     */
    public void initialize() {
        this.board = new Board();
        this.fallingTetrimino = new Tetrimino(this.board);
        this.nextTetrimino = new Tetrimino(this.board);
        this.gameOver = false;
        this.paused = false;
        this.running = true;
        this.timer = new Timer(1000, this);
        this.score = 0;
        this.level = 1;
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
     * Updates the game.
     */
    public void update() {
        if (this.gameOver) {
            return;
        }
        if (running) {
            if (this.fallingTetrimino.canMove(Direction.DOWN)) {
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
        this.score += 100 * removed * level;            //keksi parempi kaava?
        this.level = this.score / 10000 + 1;            // ^

        if (!this.fallingTetrimino.canMove(Direction.DOWN)) {
            this.gameOver = true;
            this.running = false;
        }
    }

    /**
     * Directs the command to the right tetrimino's method.
     *
     * @param direction direction to be moved
     */
    public void moveTetrimino(Direction direction) {
        if (isRunning()) {
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

    public void setSidePanel(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
    }
}
