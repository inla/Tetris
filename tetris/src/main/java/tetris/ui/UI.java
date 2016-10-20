package tetris.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetris.tetris.Game;

/**
 * The user interface of tetris.
 *
 * @author inka
 */
public class UI implements Runnable {

    private static final int SQUARE_SIZE = 24;
    private JFrame frame;
    private final Game game;
    private AbstractPanel gamePanel;
    private AbstractPanel sidePanel;

    /**
     * Creates the user interface.
     *
     * @param game the logic that needs user interface
     */
    public UI(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, SQUARE_SIZE));

        createComponents(frame.getContentPane());
        setPanelSizes();
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void createComponents(Container c) {
        this.gamePanel = new GamePanel(this.game, SQUARE_SIZE);
        this.sidePanel = new SidePanel(this.game, SQUARE_SIZE);
        c.add(this.gamePanel, BorderLayout.CENTER);
        c.add(this.sidePanel, BorderLayout.EAST);
        frame.addKeyListener(new KeyboardListener(this.game));
    }

    private void setPanelSizes() {
        int width = SQUARE_SIZE * this.game.getBoard().getWidth();
        int height = SQUARE_SIZE * this.game.getBoard().getHeight();
        this.gamePanel.setPreferredSize(new Dimension(width, height));
        this.sidePanel.setPreferredSize(new Dimension(width - SQUARE_SIZE, height));
    }

    public AbstractPanel getGamePanel() {
        return gamePanel;
    }

    public AbstractPanel getSidePanel() {
        return sidePanel;
    }

    public JFrame getFrame() {
        return frame;
    }

}
