package tetris.logic;

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
     * Selects a random tetrimino type and calls the other constructor. Selects
     * also a random rotation number.
     *
     * @param board the board where this tetrimino appears
     */
    public Tetrimino(Board board) {
        this(board, TetriminoType.values()[new Random().nextInt(7)]);
        this.rotation = new Random().nextInt(this.type.getMaxRotation());
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

    /**
     * Tells if a tetrimino can move down by one point.
     *
     * @return true if the tetimino can move, false otherwise
     */
    public boolean canMoveDown() {
        int[][] nextPlace = this.tetriminoRotations.get(this.rotation);
        return !collides(nextPlace, 2);
    }

    /**
     * Increases the tetrimino's x coordinate by one if it doesn't then collide
     * to anything.
     */
    public void moveRight() {
        if (!collides(this.tetriminoRotations.get(this.rotation), 6)) {
            this.x++;
        }
    }

    /**
     * Decreases the tetrimino's x coordinate by one if it doesn't then collide
     * to anything.
     */
    public void moveLeft() {
        if (this.x == 0) {
            return;
        }
        if (!collides(this.tetriminoRotations.get(this.rotation), 4)) {
            this.x--;
        }
    }

    /**
     * Increases the tetrimino's y coordinate by one if it doesn't then collide
     * to anything.
     */
    public void moveDown() {
        if (canMoveDown()) {
            this.y++;
        }
    }

    /**
     * Gets the next rotation from the tetrimino's list of rotations and sets
     * its number as the current rotation number, if it doesn't then collide to
     * anything.
     */
    public void rotate() {
        if (this.type.getMaxRotation() == 1) {
            return;
        }
        if (!collides(getNextRotation(), 0)) {
            if (this.rotation + 1 < this.type.getMaxRotation()) {
                this.rotation++;
            } else {
                this.rotation = 0;
            }
        }
    }

    /**
     * Checks if the given tetrimino shape collides with anything
     *
     * @param tetr the rotation of the tetrimino to be tested
     * @param direction the direction to be checked
     * @return true if collides, false otherwise
     */
    public boolean collides(int[][] tetr, int direction) {
        int tetrX = getX();
        int tetrY = getY();
        if (direction == 4) {
            tetrX--;
        } else if (direction == 6) {
            tetrX++;
        } else if (direction == 2) {
            tetrY++;
        }
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] == 0) {
                    continue;
                }
                int testX = j + tetrX;
                int testY = i + tetrY;
                if ((board.getBoard()[testY][testX] != 0) || !isInsideBorders(testX, testY)) {
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

    /**
     *
     * @return the current rotation shape
     */
    public int[][] getCurrentRotation() {
        return this.tetriminoRotations.get(this.rotation);
    }

    /**
     *
     * @return the next rotation shape
     */
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
        return testX < board.getWidth() - 1 && testX >= 0 && testY < board.getHeight() - 1 && testY >= 0;
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
