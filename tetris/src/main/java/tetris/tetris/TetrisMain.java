
package tetris.tetris;

import java.util.Random;


public class TetrisMain {

    public static void main(String[] args) {
//        Board b = new Board();
//        TetriminoType tt = TetriminoType.values()[new Random().nextInt(7)];
        Tetrimino t = new Tetrimino();
        
        
        for (int i = 0; i < t.getCurrentRotation().length; i++) {
            for (int j = 0; j < t.getCurrentRotation()[i].length; j++) {
                System.out.print(t.getCurrentRotation()[i][j]);
            }
            System.out.println("");
        }
        System.out.println(t.isInsideBorders(t.getNextRotation()));
    }
    
}
