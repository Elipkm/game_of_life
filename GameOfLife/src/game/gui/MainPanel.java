package game.gui;

import game.pojo.ViewPort;
import game.pojo.World;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel {

    private final World world;
    private final WorldDraw worldDraw;

    public MainPanel(World world, WorldDraw worldDraw) {
        this.world = world;
        this.worldDraw = worldDraw;

        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.TRAILING);

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int adjustPerScroll = 1;
                if(e.getWheelRotation() > 0){
                    worldDraw.setZoomFactor(worldDraw.getZoomFactor() + adjustPerScroll);
                    repaint();
                }
                if(e.getWheelRotation() < 0){
                    worldDraw.setZoomFactor(worldDraw.getZoomFactor() - adjustPerScroll);
                    repaint();
                }
            }
        });


        Point prevMouse = new Point(-1,-1);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){

                    int xDiff = prevMouse.x - e.getX();
                    int yDiff = prevMouse.y - e.getY();
                    prevMouse.x = e.getX();
                    prevMouse.y = e.getY();

                    xDiff /= worldDraw.getZoomFactor();
                    yDiff /= worldDraw.getZoomFactor();

                    // update viewport
                    ViewPort viewPort = worldDraw.getViewPort();
                    int newStartX = viewPort.getStartX() + xDiff;
                    int newEndX = viewPort.getEndX() + xDiff;

                    // add world bounds
                    if(newStartX >= 0 && newEndX <= world.getWidth()){
                        viewPort.setStartX(newStartX);
                        viewPort.setEndX(newEndX);
                    }

                    int newStartY = viewPort.getStartY() + yDiff;
                    int newEndY = viewPort.getEndY() + yDiff;
                    if(newStartY >= 0 && newEndY <= world.getHeight()){
                        viewPort.setStartY(newStartY);
                        viewPort.setEndY(newEndY);
                    }

                    System.out.println("ViewPort change");
                    System.out.println(viewPort);
                    System.out.println();
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                prevMouse.x = e.getX();
                prevMouse.y = e.getY();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        worldDraw.paintComponent(g);

    }



}
