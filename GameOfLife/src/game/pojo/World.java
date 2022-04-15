package game.pojo;

import game.Laws;
import java.util.*;
import java.util.List;

public class World {

    private final int WIDTH = 500;
    private final int HEIGHT = 400;

    private Cell[][] cells;

    public World() {
        this.initCells();
    }

    public Cell[][] getCells() {
        return cells;
    }

    private void initCells(){
        cells = new Cell[WIDTH][HEIGHT];
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
               this.cells[i][j] = new Cell();
            }
        }
    }

    public void setOneCellRandomlyAlive(){
        int i = new Random().nextInt(this.WIDTH);
        int j = new Random().nextInt(this.HEIGHT);

        this.cells[i][j].setAlive(true);
    }

    public void ensureLawsOnWorldChange(){
        Laws laws = new Laws();
        List<Cell> setToDead = new ArrayList<>();
        List<Cell> setToAlive = new ArrayList<>();

        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
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
    }

    private List<Cell> getNeighbours(int i, int j){
        List<Cell> neighbours = new ArrayList<>();
        if(i != 0 && j != 0){
            neighbours.add(cells[i - 1][j - 1]);
        }
        if(i != 0){
            neighbours.add(cells[i - 1][j]);
        }
        if(i != 0 && j < HEIGHT-1) {
            neighbours.add(cells[i - 1][j + 1]);
        }
        if(j != 0){
            neighbours.add(cells[i][j - 1]);
        }
        if(j != 0 && i < WIDTH-1){
            neighbours.add(cells[i + 1][j - 1]);
        }
        if(i < WIDTH-1){
            neighbours.add(cells[i + 1][j]);
        }
        if(j < HEIGHT-1){
            neighbours.add(cells[i][j + 1]);
        }
        if(i < WIDTH-1 && j < HEIGHT-1) {
            neighbours.add(cells[i + 1][j + 1]);
        }

        return neighbours;
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    public int getWidth() {
        return this.WIDTH;
    }
}
