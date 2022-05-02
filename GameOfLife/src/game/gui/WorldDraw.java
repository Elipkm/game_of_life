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
    private int zoomFactor = 1;

    public WorldDraw(World world) {
        this.world = world;
        this.width = world.getWidth();
        this.height = world.getHeight();
        this.viewPort = new ViewPort(0, width, 0, height);
        this.setZoomFactor(1);
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
                    int xOnGUI = (int) ((x-viewPort.getStartX()) * zoomFactor) + worldStartX;
                    int yOnGUI = (int) ((y-viewPort.getStartY()) * zoomFactor) + worldStartY;
                    g.fillRect(xOnGUI,yOnGUI,(int) zoomFactor,(int) zoomFactor);
                }
            }
        }
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public void setZoomFactor(int zoomFactor) {
        // no out zooming of world possible right now!
        System.out.println(zoomFactor);
        if(zoomFactor < 1){
            return;
        }

        if(zoomFactor - 1 < 0.01){
            this.viewPort.setStartX(0);
            this.viewPort.setStartY(0);
            this.viewPort.setEndX(width);
            this.viewPort.setEndY(height);
            this.zoomFactor = 1;
            return;
        }

        double lxNew = width / zoomFactor;
        double lxOld = width / this.zoomFactor;

        double deltaLx = lxNew - lxOld;
        double moveX = deltaLx / 2;

        int newStartX = (int) (viewPort.getStartX() - moveX);
        int newEndX = (int) (viewPort.getEndX() + moveX);

        double xStartBounds = 0;
        if(newStartX < 0){
            xStartBounds = newStartX * -1;
            newStartX = 0;
            newEndX += xStartBounds;
        }

        if(newEndX >= width){
            double xEndBounds = newEndX - width - 1;
            newEndX = width - 1;
            newStartX -= xEndBounds;
        }

        viewPort.setStartX(newStartX);
        viewPort.setEndX(newEndX);

        double lyNew = height / zoomFactor;
        double lyOld = height / this.zoomFactor;

        double deltaLy = lyNew - lyOld;
        double moveY = deltaLy / 2;

        int newStartY = (int) (viewPort.getStartY() - moveY);
        int newEndY = (int) (viewPort.getEndY() + moveY);

        if(newStartY < 0){
            int yStartBounds = newStartY * -1;
            newStartY = 0;
            newEndY += yStartBounds;
        }

        if(newEndY >= height){
            double yEndBounds = newEndY - height - 1;
            newEndY = height - 1;
            newStartY -= yEndBounds;
        }

        if(newStartY >= 0) viewPort.setStartY(newStartY);
        if(newEndY < height) viewPort.setEndY(newEndY);


        this.zoomFactor = zoomFactor;
        onChange();
        repaint();
    }


    public int getZoomFactor() {
        return zoomFactor;
    }

    private Runnable onChange;
    public void setOnChange(Runnable runnable){
        onChange = runnable;
    }
    private void onChange(){
        if(this.onChange != null){
            this.onChange.run();
        }
    }
}
