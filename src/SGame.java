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

    public boolean isDone(){

        for(int help = 0; help<size; help++) {
            //z góry
            int visible = 1;
            int wanted = constraints[0][help];
            int maxVal = board[0][help].value;

            for (int i = 0; i < size; i++) {
                if (board[i][help].value > maxVal && maxVal != 0) {
                    maxVal = board[i][help].value;
                    visible++;
                }
                if (wanted != visible) {
                    return false;
                }
            }
            //z dołu
            visible = 1;
            wanted = constraints[1][help];
            maxVal = board[size - 1][help].value;
            for (int i = size - 1; i >= 0; i--) {
                if (board[i][help].value > maxVal && maxVal != 0) {
                    maxVal = board[i][help].value;
                    visible++;
                }
                if (wanted != visible) {

                    return false;
                }
            }
            //z lewej
            visible = 1;
            wanted = constraints[2][help];
            maxVal = board[help][0].value;
            for (int i = 0; i < size; i++) {
                if (board[help][i].value > maxVal && maxVal != 0) {
                    maxVal = board[help][i].value;
                    visible++;
                }
                if (wanted != visible) {

                    return false;
                }
            }
            //z prawej
            visible = 1;
            wanted = constraints[3][help];
            maxVal = board[help][size - 1].value;
            for (int i = size - 1; i >= 0; i--) {
                if (board[help][i].value > maxVal && maxVal != 0) {
                    maxVal = board[help][i].value;
                    visible++;
                }
                if (wanted != visible) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public boolean checkConstraints(int x, int y, int num) {
        findNode(x,y).setValue(num);
        //z góry
        int visible = 1;
        int wanted = constraints[0][y];
        int maxVal = board[0][y].value;

        for(int i=0;i<size;i++){
            if(board[i][y].value > maxVal && maxVal!=0){
                maxVal = board[i][y].value;
                visible++;
            }
            if(wanted!=0 && visible>wanted){
                findNode(x,y).setValue(0);
                return false;
            }
        }
        //z dołu
        visible = 1;
        wanted = constraints[1][y];
        maxVal = board[size-1][y].value;
        for(int i=size-1;i>=0;i--){
            if(board[i][y].value > maxVal && maxVal!=0){
                maxVal = board[i][y].value;
                visible++;
            }
            if(wanted!=0 && visible>wanted){
                findNode(x,y).setValue(0);
                return false;
            }
        }
        //z lewej
        visible = 1;
        wanted = constraints[2][y];
        maxVal = board[x][0].value;
        for(int i=0;i<size;i++){
            if(board[x][i].value>maxVal && maxVal!=0){
                maxVal = board[x][i].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                findNode(x,y).setValue(0);
                return false;
            }
        }
        //z prawej
        visible = 1;
        wanted = constraints[3][y];
        maxVal = board[x][size-1].value;
        for(int i=size-1;i>=0;i--){
            if(board[x][i].value>maxVal && maxVal!=0){
                maxVal = board[x][i].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                findNode(x,y).setValue(0);
                return false;
            }
        }
        findNode(x,y).setValue(0);
        return true;
    }
}
