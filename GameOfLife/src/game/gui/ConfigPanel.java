package game.gui;

import game.pojo.ViewPort;
import game.pojo.World;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {

    private final World world;
    private final WorldDraw worldDrawer;

    public ConfigPanel(World world, WorldDraw worldDraw){
        this.world = world;
        this.worldDrawer = worldDraw;

        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.TRAILING);
        this.addGamePlayControl();
        this.addViewControl();
        this.addLifeCreationControl();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void addLifeCreationControl(){
        JButton createCellsButton = new JButton("start/stop create");
        JTextField creationTimeInterval = new JTextField("Time Interval");

        int createLifeTimeUnit = 300;
        Timer createLifeTime = new Timer(createLifeTimeUnit, e -> {
            ViewPort viewPort = worldDrawer.getViewPort();
            world.setOneCellRandomlyAlive(viewPort.getStartX(), viewPort.getEndX(), viewPort.getStartY(), viewPort.getEndY());
            this.repaint();
        });
        createCellsButton.addActionListener(e -> {
            if(createLifeTime.isRunning()){
                createLifeTime.stop();
            } else {
                createLifeTime.start();
            }
        });
        creationTimeInterval.addActionListener(e -> {
            String input = creationTimeInterval.getText();
            int ms = Integer.parseInt(input);
            createLifeTime.setDelay(ms);
        });

        this.add(createCellsButton);
        this.add(creationTimeInterval);
    }
    private void addGamePlayControl(){
        JButton playGameBtn = new JButton("play game");
        playGameBtn.addActionListener(e -> {
            world.ensureLawsOnWorldChange();
            this.repaint();
        });

        JButton simulateBtn = new JButton("simulate game");

        int simulateTimeUnit = 300;
        Timer timer = new Timer(simulateTimeUnit, e -> {
            world.ensureLawsOnWorldChange();
            this.repaint();
        });

        simulateBtn.addActionListener(e -> {
            if(timer.isRunning()){
                System.out.println("stop timer");
                timer.stop();
            } else {
                System.out.println("start timer");
                timer.start();
            }
        });
        JTextField simulationTimeUnitInput = new JTextField("simulation time unit");
        simulationTimeUnitInput.addActionListener(e -> {
            String input = simulationTimeUnitInput.getText();
            int ms = Integer.parseInt(input);
            timer.setDelay(ms);
        });

        JButton clearButton = new JButton("clear world");
        clearButton.addActionListener(e -> {
            world.setAllCellsDead();
            this.repaint();
        });

        this.add(playGameBtn);
        this.add(simulateBtn);
        this.add(simulationTimeUnitInput);
        this.add(clearButton);
    }
    private void addViewControl(){
        // zoom and viewport
        JTextField zoomFactorInput = new JTextField("Zoom Factor");
        zoomFactorInput.addActionListener(e -> {
            String input = zoomFactorInput.getText();
            double size = Double.parseDouble(input);
            worldDrawer.setZoomFactor(size);
            this.repaint();
        });
        this.add(zoomFactorInput);
    }
}
