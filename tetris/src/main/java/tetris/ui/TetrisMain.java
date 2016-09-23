
package tetris.ui;

import javax.swing.SwingUtilities;
import tetris.logic.Game;

public class TetrisMain {

    public static void main(String[] args) {
        Game g = new Game();
        SwingUtilities.invokeLater(new UI(g));
    }
    
}
