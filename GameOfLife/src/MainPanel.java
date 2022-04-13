import game.Time;
import game.World;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private World world = new World();
    private Time time;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.paintComponent(g);
        time = new Time(world, this);
    }

}
