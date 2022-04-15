package game;

public class Laws {

    public boolean aliveState(Cell current, Cell[] neighbours){
        int neighboursAlive = 0;
        for(Cell neighbour : neighbours){
            if(neighbour.isAlive()){
                neighboursAlive++;
            }
        }
        if(neighboursAlive == 3){
            return true;
        }
        if(neighboursAlive == 2){
            return current.isAlive();
        }
        return false;
    }
}
