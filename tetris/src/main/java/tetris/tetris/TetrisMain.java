package tetris.tetris;

import javax.swing.SwingUtilities;
import tetris.logic.Game;
import tetris.ui.UI;

public class TetrisMain {

    public static void main(String[] args) {
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
