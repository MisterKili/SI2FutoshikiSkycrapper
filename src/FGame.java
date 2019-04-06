public class FGame {

    int size;
    FNode [][] game;

    public int getSize(){
        return size;
    }

    public void setSize(int s){
        game = new FNode[s][s];
        size = s;
    }

    public void setValue(int val, int i, int j){
        game[i][j] = new FNode(val);
    }

    public FNode getNode(int i, int j){
        return game[i][j];
    }

    public void addConstraint(FNode smaller, FNode bigger){

    }

    public void printBoard(){
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                System.out.print(game[i][j].value);
            }
            System.out.println();
        }
    }
}
