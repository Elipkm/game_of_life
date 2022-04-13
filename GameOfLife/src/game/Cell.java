package game;

import javax.swing.*;
import java.awt.*;

public class Cell extends JComponent {

    private final int x, y;

    private boolean alive = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.alive){
            g.drawRect(x,y, 1, 1);
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
}
