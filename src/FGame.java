import java.util.ArrayList;

public class FGame extends Game {

    public FGame(){

    }

    public FGame(FGame other){
        size = other.size;
        board = new Node[size][size];
        for(int i = 0; i< size; i++)
            for(int j=0; j<size; j++) {
                board[i][j] = new Node(other.board[i][j].getValue(), other.board[i][j].getCord_x(), other.board[i][j].getCord_y());
                board[i][j].initDomain(size);
            }
        constraints = other.constraints;
    }

    @Override
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

    public boolean checkConstraints(int x, int y, int num) {
        int x1, x2, y1, y2;
        for(int i=0; i<constraints.length; i++){
            x1 = constraints[i][0];
            y1 = constraints[i][1];
            x2 = constraints[i][2];
            y2 = constraints[i][3];
//            System.out.println("x1 " + x1);
//            System.out.println("y1 " + y1);
//            System.out.println("x2 " + x2);
//            System.out.println("y2 " + y2);
//            System.out.println("x,y " + x+"."+y+", num : "+num);
            if(x1 == x && y1 == y){
//                System.out.println("x2, y2 get val: " + board[x2][y2].getValue());
                if(num == size)
                    return false;
                if(board[x2][y2].getValue() != 0 && num >= board[x2][y2].getValue())
                    return false;

            }else if(x2 == x && y2 == y){
                if(num == 1)
                    return false;
                if(board[x1][y1].getValue() != 0 && num <= board[x1][y1].getValue())
                    return false;
            }
        }
        return true;
    }

    @Override
    public void printConstraints(){
        System.out.println("Futoshiki constraints:");
        for(int i=0; i<constraints.length; i++){
            System.out.println(constraints[i][0] + "\t" + constraints[i][1] + "\t" + constraints[i][2] + "\t" + constraints[i][3]);
        }
    }

    @Override
    public void printBoard(){
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                System.out.print(board[i][j].getValue()+"\t");
                if(checkBetween(i, j, i, j+1)==0){
                    System.out.print("\t");
                }
                if(checkBetween(i, j, i, j+1)==-1){
                    System.out.print("<\t");
                }
                if(checkBetween(i, j, i, j+1)==1){
                    System.out.print(">\t");
                }
            }
            System.out.print("\n");
            for(int j =0; j<size; j++){

                if(checkBetween(i, j, i+1, j)==0)
                    System.out.print("\t");
                if(checkBetween(i, j, i+1, j)==1)
                    System.out.print("v\t");
                if(checkBetween(i, j, i+1, j)==-1)
                    System.out.print("^\t");
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    private int checkBetween(int x1, int y1, int x2, int y2){
        int result = 0;
        for(int k = 0; k<constraints.length && result==0; k++){
            if(constraints[k][0] == x1 && constraints[k][1] == y1 && constraints[k][2] == x2 && constraints[k][3] == y2)
                result = -1;
            if(constraints[k][2] == x1 && constraints[k][3] == y1 && constraints[k][0] == x2 && constraints[k][1] == y2)
                result = 1;
        }
        return result;
    }


}
