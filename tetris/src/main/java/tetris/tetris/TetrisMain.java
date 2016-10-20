package tetris.tetris;

import javax.swing.SwingUtilities;
import tetris.ui.UI;

public class TetrisMain {

    public static void main(String[] args) {
//        for (int level = 1; level < 11; level++) {
//            System.out.print("Level " + level + ":");
//            for (int removed = 1; removed < 5; removed++) {
//                System.out.print(" " + (100 * (removed * removed + (level - 1))));
//            }
//            System.out.println("");
//        }

        Game g = new Game();
        UI ui = new UI(g);
        SwingUtilities.invokeLater(ui);
        while (ui.getGamePanel() == null || ui.getSidePanel() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        g.setGamePanel(ui.getGamePanel());
        g.setSidePanel(ui.getSidePanel());
        g.start();
    }

}
