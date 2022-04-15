package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class World extends JComponent {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;

    private int cellSize = 25;
    private int numberOfCellsInRow;
    private int numberOfCellsInCol;

    private Cell[][] cells;

    public World() {
        cells = new Cell[WIDTH / cellSize][HEIGHT / cellSize];
        for(int i = 0; i < WIDTH / cellSize; i++){
            for(int j = 0; j < HEIGHT / cellSize; j++){
                this.cells[i][j] = new Cell(i+80+i*cellSize,j+20+j*cellSize, cellSize);
                this.numberOfCellsInCol = j+1;
            }
            this.numberOfCellsInRow = i+1;
        }
    }

    public void setCellSize(int size){
        this.cellSize = size;
        cells = new Cell[WIDTH / cellSize][HEIGHT / cellSize];
        for(int i = 0; i < WIDTH / cellSize; i++){
            for(int j = 0; j < HEIGHT / cellSize; j++){
                this.cells[i][j] = new Cell(i+80+i*cellSize,j+20+j*cellSize, cellSize);
                this.numberOfCellsInCol = j+1;
            }
            this.numberOfCellsInRow = i+1;
        }
    }


    public void randAlive(){
        int i = new Random().nextInt(this.numberOfCellsInRow);
        int j = new Random().nextInt(this.numberOfCellsInCol);

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

    public void ensureLawsOnWorldChange(JPanel g){
        Laws laws = new Laws();
        List<Cell> setToDead = new ArrayList<>();
        List<Cell> setToAlive = new ArrayList<>();

        for(int i = 0; i < numberOfCellsInRow; i++){
            for(int j = 0; j < numberOfCellsInCol; j++){
                List<Cell> neighbours = getNeighbours(i, j);
                boolean alive = laws.aliveState(this.cells[i][j], neighbours.toArray(new Cell[neighbours.size()]));
                if(alive){
                    setToAlive.add(this.cells[i][j]);
                } else {
                    setToDead.add(this.cells[i][j]);
                }
            }
        }

        for(Cell cell : setToDead){
            cell.setAlive(false);
        }
        for(Cell cell : setToAlive){
            cell.setAlive(true);
        }

        g.repaint();
    }

    private List<Cell> getNeighbours(int i, int j){
        List<Cell> neighbours = new ArrayList<>();
        if(i != 0 && j != 0){
            neighbours.add(cells[i - 1][j - 1]);
        }
        if(i != 0){
            neighbours.add(cells[i - 1][j]);
        }
        if(i != 0 && j < numberOfCellsInCol-1) {
            neighbours.add(cells[i - 1][j + 1]);
        }
        if(j != 0){
            neighbours.add(cells[i][j - 1]);
        }
        if(j != 0 && i < numberOfCellsInRow-1){
            neighbours.add(cells[i + 1][j - 1]);
        }
        if(i < numberOfCellsInRow-1){
            neighbours.add(cells[i + 1][j]);
        }
        if(j < numberOfCellsInCol-1){
            neighbours.add(cells[i][j + 1]);
        }
        if(i < numberOfCellsInRow-1 && j < numberOfCellsInCol-1) {
            neighbours.add(cells[i + 1][j + 1]);
        }

        return neighbours;
    }
}
