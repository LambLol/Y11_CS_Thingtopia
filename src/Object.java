//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

public class Object {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private final Color color;

    public Object(int x, int y, Color color, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.vx = vx;
        this.vy = vy;
    }

    public void move() {
        this.x += this.vx;
        this.y += this.vy;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, 15, 15);
    }

    public Color getColor() {
        return this.color;
    }

    public void setVX(int vx) {
        this.vx = vx;
    }

    public void setVY(int vy) {
        this.vy = vy;
    }

    public int getVX() {
        return this.vx;
    }

    public int getVY() {
        return this.vy;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
