package tetris.tetris;

import java.util.ArrayList;
import java.util.Random;

public class Tetrimino {

    private TetriminoType type;
    private ArrayList<int[][]> tetriminoRotations;
    private int rotation;
    private int x;
    private int y;

    public Tetrimino(TetriminoType type) {
        this.type = type; //getRandomTetrimino() ?
        this.tetriminoRotations = this.type.getRotations();
        this.rotation = 0;
        this.x = Board.getWidth() / 2;
        this.y = 0;
    }

    public Tetrimino() {
        getRandomTetrimino();
        this.tetriminoRotations = this.type.getRotations();
        this.rotation = 0;
        this.x = Board.getWidth() / 2;
        this.y = 0;
    }
    

    private void getRandomTetrimino() {
        this.type = TetriminoType.values()[new Random().nextInt(7)];
    }

    public void moveRight() {
        this.x++;
        int[][] next = this.tetriminoRotations.get(this.rotation);
        if (!isInsideBorders(next) || collides(next)) {
            this.x--;
        } else {
            //?
        }
    }

    public void moveLeft() {
        this.x--;
        int[][] next = this.tetriminoRotations.get(this.rotation);
        if (!isInsideBorders(next) || collides(next)) {
            this.x++;
        } else {
            //?
        }
    }

    public void rotate() {
        if (this.type.getMaxRotation() == 1) {
            return;
        }
        int[][] nextRot = getNextRotation();
        if (isInsideBorders(nextRot) && !collides(nextRot)) {
            if (this.rotation + 1 < this.type.getMaxRotation()) {
                this.rotation++;
            } else {
                this.rotation = 0;
            }
        }
    }

    public boolean collides(int[][] tetr) {

        return false;
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

    public boolean isInsideBorders(int[][] tetr) {
        return x + tetr.length - 1 < Board.getWidth() && x >= 0;  //y-tetr.length < Board.getLength()
    }                                                             // x+tetr.lenght vai x+tetr.length[y?]

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
