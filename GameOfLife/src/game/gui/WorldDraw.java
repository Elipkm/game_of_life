package game.gui;

import game.pojo.Cell;
import game.pojo.ViewPort;
import game.pojo.World;
import javax.swing.*;
import java.awt.*;


public class WorldDraw extends JComponent {

    private final World world;

    // GUI related data for drawing
    private int worldStartX = 50;
    private int worldStartY = 50;

    private final int width;
    private final int height;

    private final ViewPort viewPort;
    private double zoomFactor = 1;


    public WorldDraw(World world) {
        this.world = world;
        this.width = world.getWidth();
        this.height = world.getHeight();
        this.viewPort = new ViewPort(0, width, 0, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color prev = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(worldStartX, worldStartY, width, height);
        g.setColor(prev);

        Cell[][] cells = world.getCells();
        for(int x = viewPort.getStartX(); x < viewPort.getEndX(); x++){
            for(int y = viewPort.getStartY(); y < viewPort.getEndY(); y++){
                if(cells[x][y].isAlive()){
                    int xOnGUI = (int) (x * zoomFactor) + worldStartX;
                    int yOnGUI = (int) (y * zoomFactor) + worldStartY;
                    g.fillRect(xOnGUI,yOnGUI,(int) zoomFactor,(int) zoomFactor);
                }
            }
        }
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
        viewPort.setEndX(((int) (width/zoomFactor)) - 1);
        viewPort.setEndY((int) (height/zoomFactor) - 1);
    }

    public double getZoomFactor() {
        return zoomFactor;
    }
}
