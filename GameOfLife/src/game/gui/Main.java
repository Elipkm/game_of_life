package game.gui;

import game.pojo.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;


// TODO: the zooming behaviour is not right after setting a different viewport on the map
public class Main {

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            World world = new World(600, 400);
            WorldDraw worldDraw = new WorldDraw(world);
            JFrame mainFrame = new MainFrame(world, worldDraw);

            world.setOnChange(() -> {
                mainFrame.repaint();
            });
            worldDraw.setOnChange(() -> {
                mainFrame.repaint();
            });
        });
    }
}
