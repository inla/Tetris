/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetris.logic.Game;
import tetris.logic.Tetrimino;

/**
 * Panel that shows score and next tetrimino.
 *
 * @author inka
 */
public class SidePanel extends JPanel {

    private final int squareSize;
    private Game game;
    private int score;
    private int removedRows;
    private int level;

    /**
     * Creates the side panel.
     *
     * @param game current game
     * @param squareSize size of the squares
     */
    public SidePanel(Game game, int squareSize) {
        super.setBackground(Color.BLACK);
        this.squareSize = squareSize;
        this.game = game;
        this.score = this.game.getScore();
        this.removedRows = this.game.getRemovedRows();
        this.level = this.game.getLevel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        paintPreview(g);
        g.setColor(Color.LIGHT_GRAY);
        paintScore(g);
        paintLevel(g);
        paintInfo(g);
    }

    private void paintPreview(Graphics g) {
        g.drawString("Next: ", squareSize, 2 * squareSize);
        paintTetrimino(g);
    }

    private void paintTetrimino(Graphics g) {
        int[][] tetr = this.game.getNextTetrimino().getCurrentRotation();
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] != 0) {
                    int x = this.game.getNextTetrimino().getX() + i;
                    int y = this.game.getNextTetrimino().getY() + j + 1;
                    Color c = this.game.getNextTetrimino().getColor();
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintPoint(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fill3DRect(x * squareSize, y * squareSize, squareSize, squareSize, true);
    }

    private void paintScore(Graphics g) {
        g.drawString("Score: " + this.game.getScore(), squareSize, 7 * squareSize);
    }

    private void paintLevel(Graphics g) {
        g.drawString("Level: " + this.game.getLevel(), squareSize, 9 * squareSize);
    }

    private void paintInfo(Graphics g) {
        g.drawString("Controls:", squareSize, 11 * squareSize);
        g.setFont(new Font("Tahoma", Font.PLAIN, 12));
        g.drawString("F1    new game", squareSize, 12 * squareSize);
        g.drawString("P     pause", squareSize, 13 * squareSize);
        g.drawString("↓     down", squareSize, 14 * squareSize);
        g.drawString("→     right", squareSize, 15 * squareSize);
        g.drawString("←     left", squareSize, 16 * squareSize);
        g.drawString("↑     rotate", squareSize, 17 * squareSize);
        g.drawString("spacebar drop", squareSize, 18 * squareSize);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

}
