package game.gui;

import game.pojo.World;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(World world, WorldDraw worldDraw) throws HeadlessException {
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setContentPane(new MainPanel(world, worldDraw));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
