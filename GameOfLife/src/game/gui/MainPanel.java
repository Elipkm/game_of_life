package game.gui;

import game.pojo.World;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
                System.out.println(e.getWheelRotation());
                double adjustPerScroll = 0.1;
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        worldDraw.paintComponent(g);
    }



}
