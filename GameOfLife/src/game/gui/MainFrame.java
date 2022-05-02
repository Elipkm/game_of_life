package game.gui;

import game.pojo.World;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel mainPanel;

    public MainFrame(World world, WorldDraw worldDraw) throws HeadlessException {
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLayout(new BorderLayout());
        this.mainPanel = new MainPanel(world, worldDraw);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(new ConfigPanel(world, worldDraw), BorderLayout.NORTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void repaint() {
        super.repaint();
        this.mainPanel.repaint();
    }
}
