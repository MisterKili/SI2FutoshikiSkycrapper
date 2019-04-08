import java.util.ArrayList;

public class FGame {

    int size;
    FNode [][] game;
    int [][] constraints;

    public int getSize(){
        return size;
    }

    public void setSize(int s){
        game = new FNode[s][s];
        size = s;
    }

    public void setValue(int val, int i, int j){
        game[i][j] = new FNode(val, i, j);
    }

    public FNode getNode(int i, int j){
        return game[i][j];
    }

    public void addConstraint(FNode smaller, FNode bigger){

    }

    public void addConstraints(ArrayList<String> cons){
        constraints = new int[cons.size()][4];
        for(int i = 0; i<cons.size(); i++){
            String[] splited = cons.get(i).split(";");
            int smaller_x = getIntFromChar(splited[0].charAt(0));
            int smalller_y = Integer.parseInt(splited[0].substring(1));
            int bigger_x = getIntFromChar(splited[1].charAt(0));
            int bigger_y = Integer.parseInt(splited[1].substring(1));
            constraints[i][0] = smaller_x;
            constraints[i][1] = smalller_y-1;
            constraints[i][2] = bigger_x;
            constraints[i][3] = bigger_y-1;
        }
    }

    private int getIntFromChar(char letter){
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

    public void printConstraints(){
        for(int i=0; i<constraints.length; i++){
            System.out.println(constraints[i][0] + "\t" + constraints[i][1] + "\t" + constraints[i][2] + "\t" + constraints[i][3]);
        }
    }
}
