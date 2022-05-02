package game.pojo;

import game.Laws;
import game.utils.Utils;
import java.util.*;
import java.util.List;

public class World {

    private final int WIDTH;
    private final int HEIGHT;

    private Cell[][] cells;

    public World(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
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

    public void setOneCellRandomlyAlive(int xStart, int xEnd, int yStart, int yEnd){
        int i = Utils.getRandomNumberInRange(xStart, xEnd-1);
        int j = Utils.getRandomNumberInRange(yStart, yEnd-1);

        this.cells[i][j].setAlive(true);
        this.onChange();
    }

    public void setCellsRandomlyAlive(int n, int xStart, int xEnd, int yStart, int yEnd){
        for(int i = 0; i < n; i++){
            int x = Utils.getRandomNumberInRange(xStart, xEnd-1);
            int y = Utils.getRandomNumberInRange(yStart, yEnd-1);

            this.cells[x][y].setAlive(true);
        }
        this.onChange();
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

        this.onChange();
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

    public void setAllCellsDead(){
        for(Cell[] cellRow : cells){
            for(Cell cell : cellRow){
                cell.setAlive(false);
            }
        }
        this.onChange();
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    public int getWidth() {
        return this.WIDTH;
    }

    private Runnable onChange;
    public void setOnChange(Runnable runnable){
        onChange = runnable;
    }
    private void onChange(){
        if(this.onChange != null){
            this.onChange.run();
        }
    }

    @Override
    public String toString() {
        return "World{" +
                "WIDTH=" + WIDTH +
                ", HEIGHT=" + HEIGHT +
                '}';
    }
}
