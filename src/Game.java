//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame {
    private final Field field;
    private final JButton startButton;
    private final JButton restartButton;
    private Timer timer;

    public Game() {
        this.setTitle("THINGTOPIA");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.field = new Field(500, 400);
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Game.this.field.draw(g);
            }
        };
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(480, 380));
        this.add(panel, "Center");
        JPanel buttonPanel = new JPanel(new BorderLayout());
        this.startButton = new JButton("Start");
        this.startButton.addActionListener((e) -> {
            this.startGame();
        });
        Font customFont = new Font("Helvetica", 0, 18);
        this.startButton.setFont(customFont);
        this.startButton.setBackground(Color.WHITE);
        this.startButton.setForeground(Color.BLACK);
        buttonPanel.add(this.startButton, "West");
        this.restartButton = new JButton("Restart");
        this.restartButton.addActionListener((e) -> {
            this.restartGame();
        });
        this.restartButton.setFont(customFont);
        this.restartButton.setBackground(Color.WHITE);
        this.restartButton.setForeground(Color.BLACK);
        this.restartButton.setEnabled(false);
        buttonPanel.add(this.restartButton, "East");
        this.add(buttonPanel, "North");
        this.pack();
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
    }

    private void startGame() {
        this.field.generateObjects();
        this.startButton.setEnabled(false);
        this.restartButton.setEnabled(true);
        this.timer = new Timer(20, (e) -> {
            this.field.moveObjects();
            this.repaint();
            if (this.field.isGameOver()) {
                this.endGame();
            }

        });
        this.timer.start();
    }

    private void restartGame() {
        this.timer.stop();
        this.field.generateObjects();
        this.startButton.setEnabled(false);
        this.restartButton.setEnabled(true);
        new Game();
    }

    private void endGame() {
        this.timer.stop();
        this.field.endGame();
        this.startButton.setEnabled(true);
        this.restartButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new Game();
    }
}
