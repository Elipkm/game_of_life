package game;

import javax.swing.*;
import java.awt.*;

public class Time {

    private final World world;

    public Time(World world, JPanel g) {
        this.world = world;

        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                world.randAlive();
                g.repaint();
            }
        }).start();
    }

}
