import game.Time;
import game.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private World world = new World();
    private Time time = new Time(world);

    // TODO: add automatic time simulation for the game, zoom further out and in with mouse

    public MainPanel() {
        JButton btn = new JButton("start/stop create");
        JTextField creationTimeInterval = new JTextField("Time Interval");
        JButton automationBtn = new JButton("play game");
        JButton simulateBtn = new JButton("simulate game");
        JTextField simulationTimeUnitInput = new JTextField("simulation time unit");
        JTextField cellSize = new JTextField("Cell Size");
        cellSize.addActionListener(e -> {
            String input = cellSize.getText();
            int size = Integer.parseInt(input);
            world.setCellSize(size);
            this.repaint();
        });

        btn.addActionListener(e -> time.toggleActiveState());
        automationBtn.addActionListener(e -> world.ensureLawsOnWorldChange(this));
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

        time.startTime(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.paintComponent(g);
    }

}
