package game.gui;

import game.Time;
import game.pojo.World;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final World world = new World();
    private final WorldDraw worldDraw = new WorldDraw(world);
    private final Time time = new Time(world);

    public MainPanel() {
        JButton btn = new JButton("start/stop create");
        JTextField creationTimeInterval = new JTextField("Time Interval");
        JButton automationBtn = new JButton("play game");

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

        JTextField cellSize = new JTextField("Zoom Factor");
        cellSize.addActionListener(e -> {
            String input = cellSize.getText();
            int size = Integer.parseInt(input);
            worldDraw.setZoomFactor(size);
            this.repaint();
        });

        btn.addActionListener(e -> time.toggleActiveState());
        automationBtn.addActionListener(e -> {
            world.ensureLawsOnWorldChange();
            this.repaint();
        });
        creationTimeInterval.addActionListener(e -> {
            String input = creationTimeInterval.getText();
            int ms = Integer.parseInt(input);
            time.setTimeUnit(ms);
        });

        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        flowLayout.setAlignment(FlowLayout.TRAILING);
        this.add(btn);
        this.add(automationBtn);
        this.add(creationTimeInterval);
        this.add(cellSize);
        this.add(simulateBtn);
        this.add(simulationTimeUnitInput);

        time.startTime(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        worldDraw.paintComponent(g);
    }

}
