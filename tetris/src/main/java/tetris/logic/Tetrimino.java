package tetris.logic;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a tetrimino and contains methods for it.
 *
 * @author inka
 */
public class Tetrimino {

    private TetriminoType type;
    private ArrayList<int[][]> tetriminoRotations;
    private int rotation;
    private Color color;
    private int x;
    private int y;
    private Board board;

    /**
     * Selects a random tetrimino type and calls the other constructor.
     *
     * @param board the board where this tetrimino appears
     */
    public Tetrimino(Board board) {
        this(board, TetriminoType.values()[new Random().nextInt(7)]);
    }

    /**
     * Creates a new Tetrimino. Defines its possible rotations, the current
     * rotation's number and its starting point on the board.
     *
     * @param board the board where this tetrimino appears
     * @param type the type of this tetrimino
     */
    public Tetrimino(Board board, TetriminoType type) {
        this.board = board;
        this.type = type;
        this.tetriminoRotations = this.type.getRotations();
        this.rotation = 0;
        this.color = this.type.getColor();
        this.x = board.getWidth() / 2 - 1;
        this.y = 0;

    }
    public void moveToStartingPoint() {
        this.x = board.getWidth() / 2 - 1;
        this.y = 0;
    }
    
    /**
     * Tells if a tetrimino can move to a certain direction.
     *
     * @param direction the direction to be checked
     * @return true if the tetrimino can move, false otherwise
     */
    public boolean canMove(Direction direction) {
        return !collides(direction);
    }

    /**
     * Increases the tetrimino's x coordinate by one if it's possible.
     */
    public void moveRight() {
        if (canMove(Direction.RIGHT)) {
            this.x++;
        }
    }

    /**
     * Decreases the tetrimino's x coordinate by one if it's possible.
     */
    public void moveLeft() {
        if (this.x == 0) {
            return;
        }
        if (canMove(Direction.LEFT)) {
            this.x--;
        }
    }

    /**
     * Increases the tetrimino's y coordinate by one if it's possible.
     */
    public void moveDown() {
        if (canMove(Direction.DOWN)) {
            this.y++;
        }
    }

    /**
     * Moves the tetrimino as down as possible.
     */
    public void dropDown() {
        while (canMove(Direction.DOWN)) {
            this.y++;
        }
    }

    /**
     * Sets the next rotation number from the tetrimino's list of rotations as
     * the current rotation number, if it doesn't then collide to anything.
     */
    public void rotate() {
        if (this.type.getMaxRotation() == 1) {
            return;
        }
        if (!collides(null)) {
            if (this.rotation + 1 < this.type.getMaxRotation()) {
                this.rotation++;
            } else {
                this.rotation = 0;
            }
        }
    }

    /**
     * Checks if the given tetrimino shape collides with anything in the given
     * direction.
     *
     * @param direction the direction to be checked
     * @return true if collides, false otherwise
     */
    public boolean collides(Direction direction) {
        int dx = getX();
        int dy = getY();

        if (direction == Direction.LEFT) {
            dx--;
        } else if (direction == Direction.RIGHT) {
            dx++;
        } else if (direction == Direction.DOWN) {
            dy++;
        }
        int[][] tetr = getCurrentRotation();
        if (direction == null) {
            tetr = getNextRotation();
        }
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] == 0) {
                    continue;
                }
                int testX = j + dx;
                int testY = i + dy;
                if ((!isInsideBorders(testX, testY) || board.getBoard()[testY][testX] != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds this tetrimino shape to the board.
     */
    public void addToBoard() {
        this.board.addTetrimino(this);
    }

    public int[][] getCurrentRotation() {
        return this.tetriminoRotations.get(this.rotation);
    }

    public int[][] getNextRotation() {
        if (this.rotation + 1 < this.type.getMaxRotation()) {
            return this.tetriminoRotations.get(this.rotation + 1);
        }
        return this.tetriminoRotations.get(0);
    }

    /**
     * Checks if given coordinate point is inside the board.
     *
     * @param testX x coordinate
     * @param testY y coordinate
     * @return true if point is inside borders, false otherwise
     */
    public boolean isInsideBorders(int testX, int testY) {
        return testX < board.getWidth() && testX >= 0 && testY < board.getHeight() && testY >= 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<int[][]> getTetriminoRotations() {
        return tetriminoRotations;
    }

    public Color getColor() {
        return color;
    }

    public TetriminoType getType() {
        return type;
    }
}
