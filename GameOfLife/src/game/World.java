package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class World extends JComponent {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;

    private final Cell[][] cells = new Cell[WIDTH][HEIGHT];

    public World() {
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                this.cells[i][j] = new Cell(i+150,j+100);
            }
        }
    }


    public void randAlive(){
        int i = new Random().nextInt(WIDTH);
        int j = new Random().nextInt(HEIGHT);
        this.cells[i][j].setAlive(true);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Cell[] cellRow : cells){
            for(Cell cell : cellRow){
                cell.paintComponent(g);
            }
        }
    }
}
