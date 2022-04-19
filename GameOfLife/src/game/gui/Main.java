package game.gui;

import game.pojo.World;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            World world = new World(width-100, height-200);
            WorldDraw worldDraw = new WorldDraw(world);
            JFrame mainFrame = new MainFrame(world, worldDraw);
            JFrame configFrame = new ConfigFrame(world, worldDraw);

            world.setOnChange(() -> {
                mainFrame.repaint();
            });

        });
    }
}
