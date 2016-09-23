package tetris.logic;

import java.util.ArrayList;
import java.util.Random;

public class Tetrimino {

    private TetriminoType type;
    private ArrayList<int[][]> tetriminoRotations;
    private int rotation;
    private int x;
    private int y;
    private Board board;

    public Tetrimino(Board board) {
        this(board, TetriminoType.values()[new Random().nextInt(7)]);
        this.rotation = new Random().nextInt(this.type.getMaxRotation());
    }

    public Tetrimino(Board board, TetriminoType type) {
        this.board = board;
        this.type = type;
        this.tetriminoRotations = this.type.getRotations();
        this.rotation = 0;
        this.x = board.getWidth() / 2 - 1;
        this.y = 0;

    }

    public boolean canMoveDown() {
        this.y++;
        int[][] nextPlace = this.tetriminoRotations.get(this.rotation);
        if (collides(nextPlace)) {
            this.y--;
            return false;
        }
        this.y--;
        return true;
    }

    public void moveRight() {
        this.x++;
        int[][] next = this.tetriminoRotations.get(this.rotation);
        if (collides(next)) {
            this.x--;
        } //else {
//            //?
//        }
    }

    public void moveLeft() {
        this.x--;
        int[][] next = this.tetriminoRotations.get(this.rotation);
        if (collides(next)) {
            this.x++;
        } //else {
//            //?
//        }
    }

    public void moveDown() {
        this.y++;
        int[][] next = this.tetriminoRotations.get(this.rotation);
        if (collides(next)) {
            this.y--;
        } // else {
//            //?
//        }
    }

    public void rotate() {
        if (this.type.getMaxRotation() == 1) {
            return;
        }
        int[][] nextRot = getNextRotation();
        if (!collides(nextRot)) {
            if (this.rotation + 1 < this.type.getMaxRotation()) {
                this.rotation++;
            } else {
                this.rotation = 0;
            }
        }
    }

    public boolean collides(int[][] tetr) {
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] == 0) {
                    continue;
                }
                int testX = j + getX();
                int testY = i + getY();
                if ((board.getBoard()[testY][testX] != 0) || !isInsideBorders(testX, testY)) {
                    return true;
                }
            }
        }
        return false;
    }

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

    public TetriminoType getType() {
        return type;
    }
}
