public class Node{

    boolean isDone;
    boolean isConstant;
    int value;
    int [] domain;
    int cord_x;
    int cord_y;

    public Node(FGame game){
        domain = new int[game.getSize()];
        value = 0;
        isDone = false;
        isConstant= false;
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
            isConstant = false;
        }else {
            isDone = true;
            isConstant = true;
        }
    }

    public void setValue(int val){
        if(!isConstant) {
            this.value = val;
            this.isDone = true;
        }
    }


    public int getValue(){
        return value;
    }

    public void setCords(int x, int y){
        cord_x = x;
        cord_y = y;
    }

    public boolean isDomainEmpty(){
        for(int i = 0; i < domain.length; i++)
            if(domain[i] == 1)
                return false;
        return true;
    }

    public int countDomainSize(){
        int counter = 0;
        for (int i=0;i<domain.length;i++){
            if(domain[i] == 1){
                counter++;
            }
        }
        return counter;
    }
    public void initDomain(int s){
        domain = new int[s];
    }
    public int getCord_x() {
        return cord_x;
    }

    public int getCord_y() {
        return cord_y;
    }
}
