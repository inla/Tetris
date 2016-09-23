/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author inka
 */
public class GamePanel extends JPanel {

    private final static int SQUARE_SIZE = 30;

    public GamePanel() {
        super.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintBoard(g);
    }

    private void paintBoard(Graphics g) {
        g.setColor(new Color(10, 10, 10));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                } else if (i % 2 != 0 && j % 2 != 0) {
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }
    
}
