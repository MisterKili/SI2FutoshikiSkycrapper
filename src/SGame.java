import java.util.ArrayList;

public class SGame extends Game {

    public void setSize(int s){
        size = s;
        board = new Node[size][size];
        for(int i = 0; i<size; i++)
            for (int j = 0; j<size; j++)
                setValue(0, i, j);
    }

    @Override
    public void printConstraints() {
        System.out.println("Skyscrapper constraints:");
        for(int i = 0 ; i<constraints.length ; i++) {
            for (int j = 0; j < constraints[i].length; j++)
                System.out.print(constraints[i][j] + "\t");
            System.out.println();
        }
    }

    @Override
    public void addConstraints(ArrayList<String> cons) {
        constraints = new int[4][size];
        for(int i = 0; i < 4; i++){
            String []splited = cons.get(i).split(";");
            for(int j = 0; j<size; j++){
                constraints[i][j] = Integer.parseInt(splited[j+1]);
            }
        }

    }

    @Override
    public boolean checkConstraints(int x, int y, int num) {
        return false;
    }
}
