public class Node{

    boolean isDone;
    int value;
    int [] posibleValues;
    int cord_x;
    int cord_y;

    public Node(FGame game){
        posibleValues = new int[game.getSize()];
        value = 0;
        isDone = false;
    }

    public Node(int val){
        this.value = val;
        if(val == 0){
            isDone = false;
        }else {
            isDone = true;
        }
    }

    public Node(int val, int x, int y){
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

    public int getValue(){
        return value;
    }

    public void setCords(int x, int y){
        cord_x = x;
        cord_y = y;
    }

}
