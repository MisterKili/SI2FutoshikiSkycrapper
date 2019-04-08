import java.util.ArrayList;

public abstract class Game {

    int size;
    Node[][] game;


    public int getSize(){
        return size;
    }

    public void setSize(int s){
        game = new Node[s][s];
        size = s;
    }

    public void setValue(int val, int i, int j){
        game[i][j] = new Node(val, i, j);
    }

    public Node getNode(int i, int j){
        return game[i][j];
    }

    protected int getIntFromChar(char letter){
        return letter%65;
    }

    public void printBoard(){
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                System.out.print(game[i][j].value);
            }
            System.out.println();
        }
    }

    public abstract void printConstraints();
    public abstract void addConstraints(ArrayList<String> cons);
}
