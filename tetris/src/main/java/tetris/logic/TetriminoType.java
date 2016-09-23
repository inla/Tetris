package tetris.logic;

import java.util.ArrayList;

public enum TetriminoType {

    I(0),
    J(1),
    L(2),
    O(3),
    S(4),
    T(5),
    Z(6);

    private int maxRotation;
    private ArrayList<int[][]> rotations;

    private TetriminoType(int type) {
        makeTetrimino(type);
    }

    private void makeTetrimino(int type) {
        switch (type) {
            case 0:     //I
                this.maxRotation = 2;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{{1, 1, 1, 1}});
                this.rotations.add(new int[][]{ {1},
                                                {1},
                                                {1},
                                                {1}});
                break;

            case 1:     //J
                this.maxRotation = 4;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {0, 1},
                                                {0, 1},
                                                {1, 1}});
                this.rotations.add(new int[][]{ {1, 0, 0},
                                                {1, 1, 1}});
                this.rotations.add(new int[][]{ {1, 1},
                                                {1, 0},
                                                {1, 0}});
                this.rotations.add(new int[][]{ {1, 1, 1},
                                                {0, 0, 1}});
                break;

            case 2:     //L
                this.maxRotation = 4;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {1, 0},
                                                {1, 0},
                                                {1, 1}});
                this.rotations.add(new int[][]{ {1, 1, 1},
                                                {1, 0, 0}});
                this.rotations.add(new int[][]{ {1, 1},
                                                {0, 1},
                                                {0, 1}});
                this.rotations.add(new int[][]{ {0, 0, 1},
                                                {1, 1, 1}});
                break;

            case 3:     //O
                this.maxRotation = 1;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {1, 1},
                                                {1, 1}});
                break;

            case 4:     //S
                this.maxRotation = 2;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {0, 1, 1},
                                                {1, 1, 0}});
                this.rotations.add(new int[][]{ {1, 0},
                                                {1, 1},
                                                {0, 1}});
                break;

            case 5:     //T
                this.maxRotation = 4;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {0, 1, 0},
                                                {1, 1, 1}});
                this.rotations.add(new int[][]{ {1, 0},
                                                {1, 1},
                                                {1, 0}});
                this.rotations.add(new int[][]{ {1, 1, 1},
                                                {0, 1, 0}});
                this.rotations.add(new int[][]{ {0, 1},
                                                {1, 1},
                                                {0, 1}});
                break;

            case 6:     //Z
                this.maxRotation = 2;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {1, 1, 0},
                                                {0, 1, 1}});
                this.rotations.add(new int[][]{ {0, 1},
                                                {1, 1},
                                                {1, 0}});
                break;
        }
    }

    public ArrayList<int[][]> getRotations() {
        return rotations;
    }

    public int getMaxRotation() {
        return maxRotation;
    }

}
