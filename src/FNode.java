public class FNode {

    boolean isDone;
    int value;
    int [] posibleValues;
    int cord_x;
    int cord_y;

    public FNode(FGame game){
        posibleValues = new int[game.getSize()];
        value = 0;
        isDone = false;
    }

    public FNode(int val){
        this.value = val;
        if(val == 0){
            isDone = false;
        }else {
            isDone = true;
        }
    }

    public FNode(int val, int x, int y){
        this.value = val;
        cord_x = x;
        cord_y = y;
        if(val == 0){
            isDone = false;
        }else {
            isDone = true;
        }
    }

    public void setValue(int val){
        this.value = val;
        this.isDone = true;
    }

    public void setCords(int x, int y){
        cord_x = x;
        cord_y = y;
    }

}
