//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Field {
    private final ArrayList<Object> objects;
    private final int width;
    private final int height;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.objects = new ArrayList<>();
    }

    public void generateObjects() {
        Random rand = new Random();
        int numObjects = rand.nextInt(21) + 30;

        for(int i = 0; i < numObjects; ++i) {
            int x = rand.nextInt(this.width - 30);
            int y = rand.nextInt(this.height - 30);
            int vx = rand.nextInt(21) - 10;
            int vy = rand.nextInt(21) - 10;
            if (vx >= 0 && vx < 3) {
                vx = 3;
            }

            if (vx > -3 && vx <= 0) {
                vx = -3;
            }

            if (vy >= 0 && vy < 3) {
                vy = 3;
            }

            if (vy > -3 && vy <= 0) {
                vy = -3;
            }

            Color color = this.getRandomColor();
            Object object = new Object(x, y, color, vx, vy);
            this.objects.add(object);
        }

    }

    private Color getRandomColor() {
        Random rand = new Random();
        int colorNum = rand.nextInt(5);

        return switch (colorNum) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 3 -> new Color(128, 0, 128);
            default -> Color.YELLOW;
        };
    }

    public void moveObjects() {
        for(int i = 0; i < this.objects.size(); ++i) {
            Object object = this.objects.get(i);
            object.move();
            if (object.getX() < 0 || object.getX() > this.width - 30) {
                object.setVX(-object.getVX());
            }

            if (object.getY() < 0 || object.getY() > this.height - 30) {
                object.setVY(-object.getVY());
            }

            for(int j = i + 1; j < this.objects.size(); ++j) {
                Object other = this.objects.get(j);
                if (object.getX() < other.getX() + 30 && object.getX() + 30 > other.getX() && object.getY() < other.getY() + 30 && object.getY() + 30 > other.getY()) {
                    this.interact(object, other);
                }
            }
        }

    }

    private void interact(Object object, Object other) {
        Color color1 = object.getColor();
        Color color2 = other.getColor();
        if (color1.equals(color2)) {
            this.reproduce(object, other);
        } else if ((!color1.equals(Color.RED) || !color2.equals(Color.BLUE)) && (!color1.equals(Color.BLUE) || !color2.equals(Color.GREEN)) && (!color1.equals(Color.GREEN) || !color2.equals(new Color(128, 0, 128))) && (!color1.equals(new Color(128, 0, 128)) || !color2.equals(Color.YELLOW)) && (!color1.equals(Color.YELLOW) || !color2.equals(Color.RED))) {
            this.kill(object);
        } else {
            this.kill(other);
        }

    }

    private void reproduce(Object object, Object other) {
        Random rand = new Random();
        int x = (object.getX() + other.getX()) / 2;
        int y = (object.getY() + other.getY()) / 2;
        int vx = rand.nextInt(21) - 10;
        int vy = rand.nextInt(21) - 10;
        if (vx >= 0 && vx < 3) {
            vx = 3;
        }

        if (vx > -3 && vx <= 0) {
            vx = -3;
        }

        if (vy >= 0 && vy < 3) {
            vy = 3;
        }

        if (vy > -3 && vy <= 0) {
            vy = -3;
        }

        Color color = object.getColor();
        this.objects.remove(object);
        this.objects.remove(other);
        Object newObject = new Object(x, y, color, vx, vy);
        this.objects.add(newObject);
    }

    private void kill(Object other) {
        this.objects.remove(other);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.width, this.height);

        for (Object object : this.objects) {
            object.draw(g);
        }

    }

    public boolean isGameOver() {
        return this.objects.size() <= 1;
    }

    void endGame() {
        if (this.objects.size() == 1) {
            Color color = this.objects.get(0).getColor();
            new ImageIcon();
            String colors;
            ImageIcon colorImage;
            if (color == Color.RED) {
                colors = "RED";
                colorImage = new ImageIcon("/Users/ian/Downloads/red (1).png");
                JOptionPane.showMessageDialog(null, colors + " is the Winner!", "WINNER", JOptionPane.INFORMATION_MESSAGE, colorImage);
            } else if (color == Color.BLUE) {
                colors = "BLUE";
                colorImage = new ImageIcon("/Users/ian/Downloads/blue (1).png");
                JOptionPane.showMessageDialog(null, colors + " is the Winner!", "WINNER", JOptionPane.INFORMATION_MESSAGE, colorImage);
            } else if (color == Color.GREEN) {
                colors = "GREEN";
                colorImage = new ImageIcon("/Users/ian/Downloads/green (1).png");
                JOptionPane.showMessageDialog(null, colors + " is the Winner!", "WINNER", JOptionPane.INFORMATION_MESSAGE, colorImage);
            } else if (color == Color.YELLOW) {
                colors = "YELLOW";
                colorImage = new ImageIcon("/Users/ian/Downloads/yellow (1).png");
                JOptionPane.showMessageDialog(null, colors + " is the Winner!", "WINNER", JOptionPane.INFORMATION_MESSAGE, colorImage);
            } else {
                colors = "PURPLE";
                colorImage = new ImageIcon("/Users/ian/Downloads/purple (1).png");
                JOptionPane.showMessageDialog(null, colors + " is the Winner!", "WINNER", JOptionPane.INFORMATION_MESSAGE, colorImage);
            }

            System.exit(0);
        }

    }
}
