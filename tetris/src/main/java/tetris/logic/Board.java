package tetris.logic;

import java.awt.Color;

/**
 * Represents the game board of Tetris. It is defined as a 2-dimenional integer
 * table, where 0 means an empty point.
 *
 * @author inka
 */
public class Board {

    private final static int HEIGHT = 20;
    private final static int WIDTH = 10;
    private final int[][] board;

    /**
     * Creates a new board matrix.
     */
    public Board() {
        this.board = new int[HEIGHT][WIDTH];
        //emptyBoard();
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int[][] getBoard() {
        return board;
    }

    /**
     * Clears the board. Sets all points to 0.
     */
    public void emptyBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    /**
     * Adds a tetrimino into the board. Sets the coordinate values where the
     * tetrimino is to the tetrimino's type (which is the coloCode).
     *
     * @param tetrimino the tetrimino to be added
     */
    public void addTetrimino(Tetrimino tetrimino) {
        for (int i = 0; i < tetrimino.getCurrentRotation().length; i++) {
            for (int j = 0; j < tetrimino.getCurrentRotation()[i].length; j++) {
                int colorCode = tetrimino.getCurrentRotation()[i][j];
                if (colorCode == 0) {
                    continue;
                }
                int x = j + tetrimino.getX();
                int y = i + tetrimino.getY();
                setPoint(x, y, colorCode);
            }
        }
    }

    /**
     * Tells if a point in given coordinates is empty. Point is empty when its
     * value is 0.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return true if the point is empty, false otherwise
     */
    public boolean isPointEmpty(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return x >= 0 && x < WIDTH && y < 0;    //true jos laudan yläpuolella
        } else {
            return this.board[y][x] == 0;
        }
    }

    /**
     * Removes all full rows from the board. Iterates through all the rows from
     * below upwards, checks if a row is full and if it is, removes it.
     */
    public void removeFullRows() {
        for (int y = HEIGHT - 1; y >= 0; y--) {
            if (isRowFull(y)) {
                removeRow(y);
                y++;
            }
        }
    }

    /**
     * Tells if a certain row is full. A row is full when all of its points are
     * not empty.
     *
     * @param y row's y coordinate
     * @return true if the row is full, false otherwise
     */
    public boolean isRowFull(int y) {
//        if (y < 0 || y >= HEIGHT) {   //laudan ulkopuolella true/false?
//            return true;
//        }
        for (int x = 0; x < WIDTH; x++) {
            if (this.board[y][x] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tells if a certain row is empty. A row is empty when all of its points
     * are empty.
     *
     * @param y row's y coordinate
     * @return true if the row is empty, false otherwise
     */
    public boolean isRowEmpty(int y) {
//        if (y < 0 || y >= HEIGHT) {   //laudan ulkopuolella true/false?
//            return false;
//        }
        for (int x = 0; x < WIDTH; x++) {
            if (this.board[y][x] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes the given row. Drops the upper rows one row further down and sets
     * the top row empty.
     *
     * @param y the y coordinate of the row to be removed
     */
    public void removeRow(int y) {
        if (y < 0 || y >= HEIGHT) {
            return;
        }
        for (int z = y; z > 0; z--) {       //ylemmät rivit yksi alemmas
            for (int x = 0; x < WIDTH; x++) {
                this.board[z][x] = this.board[z - 1][x];
            }
        }
        for (int x = 0; x < WIDTH; x++) {     //uusi rivi ylös
            this.board[0][x] = 0;
        }
    }

    /**
     * Sets the value of a given coordinate point to the given type.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param type type to be setted (=colorcode)
     */
    public void setPoint(int x, int y, int type) {
        this.board[y][x] = type;
    }

    /**
     * Tells the type of a point in given coordinates.
     *
     * @param x point's x coordinate
     * @param y point's y coordinate
     * @return the type(=colocode)
     */
    public int getPoint(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return 0;
        } else {
            return this.board[y][x];
        }
    }
    
    public Color getPointColor(int x, int y) {
        int point = getPoint(x, y);
        if (point == 1) {
            return Color.CYAN;
        } else if (point == 2) {
            return Color.BLUE;
        } else if (point == 3) {
            return Color.ORANGE;
        } else if (point == 4) {
            return Color.YELLOW;
        } else if (point == 5) {
            return Color.GREEN;
        } else if (point == 6) {
            return Color.MAGENTA;
        } else if (point == 7) {
            return Color.RED;
        }
        return null;
    }
}
