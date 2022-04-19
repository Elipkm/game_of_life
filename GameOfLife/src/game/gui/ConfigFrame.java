package game.gui;

import game.pojo.World;

import javax.swing.*;

public class ConfigFrame extends JFrame {

    public ConfigFrame(World world, WorldDraw worldDraw){
        this.setTitle("Game of Life Configuration");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setContentPane(new ConfigPanel(world, worldDraw));
        this.setVisible(true);
    }
}
