package game;

import javax.swing.*;
import java.awt.*;

public class Cell extends JComponent {

    private final int x, y;

    private int size = 1;

    private boolean alive = false;

    public Cell(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.alive){
            g.fillRect(x,y, size, size);
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return size;
    }

    @Override
    public int getWidth() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
