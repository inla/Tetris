package tetris.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import tetris.logic.Game;

/**
 *
 * @author inka
 */
public class UI implements Runnable {

    private JFrame frame;
    private Game game;

    public UI(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        frame.setPreferredSize(new Dimension(300, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void createComponents(Container c) {
        c.add(new GamePanel());

    }

}
