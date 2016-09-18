/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.tetris;

import java.util.ArrayList;

/**
 *
 * @author inka
 */
public enum TetriminoType {
    I(0),
    J(1), 
    L(2),
    O(3),
    S(4),
    T(5),
    Z(6);
    
    private int type;
    private int maxRotation;
    private ArrayList<int[][]> list;

    private TetriminoType(int type) {
        this.list = new ArrayList();
        makeTetrimino(type);
    }
    
    private void makeTetrimino(int type) {
        switch (type) {
            case 0:     //I
                this.maxRotation = 2;
                this.list = new ArrayList();
                this.list.add(new int[][] { {1, 1, 1, 1} });
                this.list.add(new int[][] { {1}, 
                                            {1},
                                            {1},
                                            {1} });
                break;
                
            case 1:     //J
                this.maxRotation = 4;
                this.list = new ArrayList();
                this.list.add(new int[][] { {0, 1},
                                            {0, 1},
                                            {1, 1} });
                this.list.add(new int[][] { {1, 0, 0},
                                            {1, 1, 1} });
                this.list.add(new int[][] { {1, 1},
                                            {1, 0},
                                            {1, 0} });
                this.list.add(new int[][] { {1, 1, 1},
                                            {0, 0, 1} });
                break;
            
            case 2:     //L
                this.maxRotation = 4;
                this.list = new ArrayList();
                this.list.add(new int[][] { {1, 0},
                                            {1, 0},
                                            {1, 1} });
                this.list.add(new int[][] { {1, 1, 1},
                                            {1, 0, 0} });
                this.list.add(new int[][] { {1, 1},
                                            {0, 1},
                                            {0, 1} });
                this.list.add(new int[][] { {0, 0, 1},
                                            {1, 1, 1} });
                break;
            
            case 3:     //O
                this.maxRotation = 1;
                this.list = new ArrayList();
                this.list.add(new int[][] { {1, 1},
                                            {1, 1} });
                break;
                
            case 4:     //S
                this.maxRotation = 2;
                this.list = new ArrayList();
                this.list.add(new int[][] { {0, 1, 1},
                                            {1, 1, 0} });
                this.list.add(new int[][] { {1, 0},
                                            {1, 1},
                                            {0, 1} });
                break;
                
            case 5:     //T
                this.maxRotation = 4;
                this.list = new ArrayList();
                this.list.add(new int[][] { {0, 1, 0},
                                            {1, 1, 1} });
                this.list.add(new int[][] { {1, 0},
                                            {1, 1},
                                            {1, 0} });
                this.list.add(new int[][] { {1, 1, 1},
                                            {0, 1, 0} });
                this.list.add(new int[][] { {0, 1},
                                            {1, 1},
                                            {0, 1} });
                break;
                
            case 6:     //Z
                this.maxRotation = 2;
                this.list = new ArrayList();
                this.list.add(new int[][] { {1, 1, 0},
                                            {0, 1, 1} });
                this.list.add(new int[][] { {0, 1},
                                            {1, 1},
                                            {1, 0} });
                break;
        }
    }
    
}
