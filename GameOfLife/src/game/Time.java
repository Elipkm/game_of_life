package game;

import game.pojo.World;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Time {

    private final World world;

    private final AtomicBoolean activated = new AtomicBoolean(false);
    private int timeUnit = 200; // in ms

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public Time(World world) {
        this.world = world;

    }

    public void startTime(JPanel g){
        new Thread(() -> {
            while(true) {
                while (activated.get()) {
                    try {
                        world.setOneCellRandomlyAlive();
                        g.repaint();
                        Thread.sleep(timeUnit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NullPointerException en){
                        en.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void toggleActiveState(){
        activated.set(!activated.get());
    }

}
