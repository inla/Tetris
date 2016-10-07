/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.ui;

import java.awt.Color;
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
    private Tetrimino tetrimino;
    private int score;
    private int removedRows;
    private int level;

    public SidePanel(Game game, int squareSize) {
        super.setBackground(Color.BLACK);
        this.squareSize = squareSize;
        this.game = game;
        this.tetrimino = this.game.getNextTetrimino();
        this.score = this.game.getScore();
        this.removedRows = this.game.getRemovedRows();
        this.level = this.game.getLevel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintTetrimino(g);
    }

    private void paintTetrimino(Graphics g) {
        int[][] tetr = this.tetrimino.getCurrentRotation();
        for (int i = 0; i < tetr.length; i++) {
            for (int j = 0; j < tetr[i].length; j++) {
                if (tetr[i][j] != 0) {
                    int x = this.tetrimino.getX() + i;
                    int y = this.tetrimino.getY() + j;
                    Color c = this.tetrimino.getColor();
                    paintPoint(g, x, y, c);
                }
            }
        }
    }

    private void paintPoint(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fill3DRect(x * squareSize, y * squareSize, squareSize, squareSize, true);
    }

    @Override
    public void repaint() {
        super.repaint();
    }

}
