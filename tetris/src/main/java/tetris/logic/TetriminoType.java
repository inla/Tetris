package tetris.logic;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Defines all seven different tetrimino types and all their possible rotations.
 *
 * @author inka
 *
 */
public enum TetriminoType {

    I(1),
    J(2),
    L(3),
    O(4),
    S(5),
    T(6),
    Z(7);

    private Color color;
    private int maxRotation;
    private ArrayList<int[][]> rotations;

    private TetriminoType(int type) {
        makeTetrimino(type);
    }

    private void makeTetrimino(int type) {
        switch (type) {
            case 1:     //I
                this.color = Color.CYAN;
                this.maxRotation = 2;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{{1, 1, 1, 1}});
                this.rotations.add(new int[][]{ {1},
                                                {1},
                                                {1},
                                                {1}});
                break;

            case 2:     //J
                this.color = Color.BLUE;
                this.maxRotation = 4;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {2, 2, 2},
                                                {0, 0, 2}});
                this.rotations.add(new int[][]{ {0, 2},
                                                {0, 2},
                                                {2, 2}});
                this.rotations.add(new int[][]{ {2, 0, 0},
                                                {2, 2, 2}});
                this.rotations.add(new int[][]{ {2, 2},
                                                {2, 0},
                                                {2, 0}});
                
                break;

            case 3:     //L
                this.color = new Color(255, 137, 0);    //orange
                this.maxRotation = 4;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {0, 0, 3},
                                                {3, 3, 3}});
                this.rotations.add(new int[][]{ {3, 0},
                                                {3, 0},
                                                {3, 3}});
                this.rotations.add(new int[][]{ {3, 3, 3},
                                                {3, 0, 0}});
                this.rotations.add(new int[][]{ {3, 3},
                                                {0, 3},
                                                {0, 3}});
                
                break;

            case 4:     //O
                this.color = Color.YELLOW;
                this.maxRotation = 1;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {4, 4},
                                                {4, 4}});
                break;

            case 5:     //S
                this.color = Color.GREEN;
                this.maxRotation = 2;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {0, 5, 5},
                                                {5, 5, 0}});
                this.rotations.add(new int[][]{ {5, 0},
                                                {5, 5},
                                                {0, 5}});
                break;

            case 6:     //T
                this.color = Color.MAGENTA;
                this.maxRotation = 4;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {0, 6, 0},
                                                {6, 6, 6}});
                this.rotations.add(new int[][]{ {6, 0},
                                                {6, 6},
                                                {6, 0}});
                this.rotations.add(new int[][]{ {6, 6, 6},
                                                {0, 6, 0}});
                this.rotations.add(new int[][]{ {0, 6},
                                                {6, 6},
                                                {0, 6}});
                break;

            case 7:     //Z
                this.color = Color.RED;
                this.maxRotation = 2;
                this.rotations = new ArrayList();
                this.rotations.add(new int[][]{ {7, 7, 0},
                                                {0, 7, 7}});
                this.rotations.add(new int[][]{ {0, 7},
                                                {7, 7},
                                                {7, 0}});
                break;
        }
    }

    public ArrayList<int[][]> getRotations() {
        return rotations;
    }

    public int getMaxRotation() {
        return maxRotation;
    }

    public Color getColor() {
        return color;
    }
}
