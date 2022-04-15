package game.gui;

import game.pojo.Cell;
import game.pojo.World;

import javax.swing.*;
import java.awt.*;


public class WorldDraw extends JComponent {

    private final World world;

    // GUI related data for drawing
    private final int worldStartX = 80;
    private final int worldStartY = 100;
    private final int width = 500;
    private final int height = 400;

    private int zoomFactor = 2;


    public WorldDraw(World world) {
        this.world = world;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color prev = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(worldStartX, worldStartY, width, height);
        g.setColor(prev);

        int viewPortX = 0;
        int viewPortY = 0;

        Cell[][] cells = world.getCells();
        for(int x = viewPortX; x < width / zoomFactor; x++){
            for(int y = viewPortY; y < height / zoomFactor; y++){
                if(cells[x][y].isAlive()){
                    g.fillRect(x*zoomFactor + worldStartX,y*zoomFactor + worldStartY,1*zoomFactor,1*zoomFactor);
                }
            }
        }
    }

    public void setZoomFactor(int zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    public int getZoomFactor() {
        return zoomFactor;
    }
}
