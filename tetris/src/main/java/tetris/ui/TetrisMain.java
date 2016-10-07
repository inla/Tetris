package tetris.ui;

import javax.swing.SwingUtilities;
import tetris.logic.Game;

public class TetrisMain {

    public static void main(String[] args) {
        Game g = new Game();
        UI ui = new UI(g);
        SwingUtilities.invokeLater(ui);
        while (ui.getGamePanel() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("hmm..");
            }
        }
        g.setGamePanel(ui.getGamePanel());
        g.start();
    }

}
