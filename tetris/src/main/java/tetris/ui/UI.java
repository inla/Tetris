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
    private GamePanel gamePanel;
    private final static int SQUARE_SIZE = 24;

    public UI(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        
        createComponents(frame.getContentPane());
setPanelSize();
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void createComponents(Container c) {
        this.gamePanel = new GamePanel(this.game, SQUARE_SIZE);
        c.add(this.gamePanel);
        frame.addKeyListener(new KeyboardListener(this.game));
    }

    private void setPanelSize() {
        int width = SQUARE_SIZE * this.game.getBoard().getWidth();
        int height = SQUARE_SIZE * this.game.getBoard().getHeight();
        this.gamePanel.setPreferredSize(new Dimension(width, height));
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public JFrame getFrame() {
        return frame;
    }

}
