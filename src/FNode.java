public class FNode {

    boolean isDone;
    int value;
    int [] posibleValues;

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

    public void setValue(int val){
        this.value = val;
        this.isDone = true;
    }
}
