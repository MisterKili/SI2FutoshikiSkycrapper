import java.util.ArrayList;

public class SGame extends Game {

    public SGame(){

    }

    public SGame(SGame other){
        size = other.size;
        board = new Node[size][size];
        for(int i = 0; i< size; i++)
            for(int j=0; j<size; j++) {
                board[i][j] = new Node(other.board[i][j].getValue(), other.board[i][j].getCord_x(), other.board[i][j].getCord_y());
                board[i][j].initDomain(size);
            }
        constraints = other.constraints;
    }

    public void setSize(int s){
        size = s;
        board = new Node[size][size];
      /*  for(int i = 0; i<size; i++)
            for (int j = 0; j<size; j++)
               setValue(0, i, j);*/
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
        //z góry
        int visible = 0;
        int wanted = constraints[0][y];
        int maxVal = board[0][y].value;
        for(int i=0;i<=x;i++){
            if(board[i][y].value>=maxVal){
                maxVal = board[i][y].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                return false;
            }
        }
        //z dołu
        visible = 0;
        wanted = constraints[1][y];
        maxVal = board[size-1][y].value;
        for(int i=size-1;i>=x;i--){
            if(board[i][y].value>=maxVal){
                maxVal = board[i][y].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                return false;
            }
        }
        //z lewej
        visible = 0;
        wanted = constraints[2][y];
        maxVal = board[x][0].value;
        for(int i=0;i<=y;i++){
            if(board[x][i].value>=maxVal){
                maxVal = board[x][i].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                return false;
            }
        }
        //z prawej
        visible = 0;
        wanted = constraints[3][y];
        maxVal = board[x][size-1].value;
        for(int i=size-1;i>=y;i--){
            if(board[x][i].value>=maxVal){
                maxVal = board[x][i].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                return false;
            }
        }

        return true;
    }
}
