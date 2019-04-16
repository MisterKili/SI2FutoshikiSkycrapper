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

    public boolean isOK(){
        if(!checkTop())
            return false;
        if(!checkBottom())
            return false;
        if(!checkLeft())
            return false;
        if(!checkRight())
            return false;
        return true;
    }

    private boolean checkTop(){
        int [] topConstraints = constraints[0];

        for(int col = 0 ; col<size; col++){
            int visible = 1;
            int highest = board[0][col].getValue();
            int wanted = topConstraints[col];
            if(wanted!=0){
                for(int row = 0 ; row < size; row++){
                    if(board[row][col].getValue()>highest) {
                        visible++;
                        highest = board[row][col].getValue();
                    }
                }
                if(visible!=wanted) {
//                    System.out.println("RETURN FALSE top: "+ col);
                    return false;
                }
            }

        }

        return true;
    }
    private boolean checkBottom(){
        int [] bottomConstraints = constraints[1];

        for(int col = 0 ; col<size; col++){
            int visible = 1;
            int highest = board[size-1][col].getValue();
            int wanted = bottomConstraints[col];
            if(wanted!=0){
                for(int row = size-1 ; row >= 0; row--){
                    if(board[row][col].getValue()>highest) {
                        visible++;
                        highest = board[row][col].getValue();
                    }
                }
                if(visible!=wanted) {
//                    System.out.println("RETURN FALSE bottom: "+ col);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkLeft(){
        int [] leftConstraints = constraints[2];
        for(int row = 0; row<size; row ++){
            int visible = 1;
            int highest = board[row][0].getValue();
            int wanted = leftConstraints[row];
            if(wanted!=0){
                for(int col = 0; col<size; col++){
                    if(board[row][col].getValue()>highest){
                        visible++;
                        highest = board[row][col].getValue();
                    }
                }
                if(visible!=wanted) {
//                    System.out.println("RETURN FALSE LEFT: "+ row);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRight(){
        int [] rightConstraints = constraints[3];
        for(int row = 0; row<size; row ++){
            int visible = 1;
            int highest = board[row][size-1].getValue();
            int wanted = rightConstraints[row];
            if(wanted!=0){
                for(int col = size-1; col>=0; col--){
                    if(board[row][col].getValue()>highest){
                        visible++;
                        highest = board[row][col].getValue();
                    }
                }
                if(visible!=wanted) {

//                    System.out.println("RETURN FALSE right: "+ row);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkConstraints(int x, int y, int num) {
        if(isComplete()){
            return isOK();
        }
        return true;
    }

    public boolean calculateDomains(){
        for (int i=0; i<size; i++){
            for (int j = 0; j<size; j++){
                for (int k = 0; k<size; k++)
                    board[i][j].domain[k] = 0;
                for(int num = 1; num <=size; num++){ //nie wiem czy przy wyszukiwaniu wszystkich rozwiązań, nie trzeba będzie zmodyfikować, ale na razie zostawiam
//                    System.out.println(checkForward(i, j, num));
                    if(board[i][j].getValue() == 0 && checkForward(i, j, num)){
//                        printBoard();
//                        printDomains();
                        board[i][j].domain[num-1] = 1;
                    }
                    if(board[i][j].getValue() !=0)
                        board[i][j].domain[board[i][j].getValue()-1] = 1;
                }
            }
        }
        return true;
    }

    public boolean checkForward(int row, int col, int num){
        boolean lastInRow = false;
        boolean lastInCol = false;
        int countRow = 0;
        int countCol = 0;
        for(int i = 0; i<size; i++){
            if(board[row][i].getValue() != 0)
                countRow++;
            if(board[i][col].getValue() != 0)
                countCol++;
        }
//        System.out.println("rowCount: "+countRow + " colCount: "+countCol);
        //ostatni w rzędzie
        if(countRow==size-1){
//            System.out.println("row: " + checkRow(row, col, num));
            return checkRow(row, col, num);

        }else //ostatni w kolumnie
            if(countCol==size-1){
//                System.out.println("col: " + checkRow(row, col, num));
                return checkCol(row, col, num);
            }
        return true;
    }
    private boolean checkCol(int r, int col, int num) {
        board[r][col].setValue(num);
        //kolumna z góry:
        int visible = 1;
        int highest = board[0][col].getValue();
        int wanted = constraints[0][col];
        if(wanted!=0){
            for(int row = 0 ; row < size; row++){
                /*if(row==r){
                    if(num > highest){
                        visible++;
                        highest=num;
                    }
                }else */if(board[row][col].getValue()>highest) {
                    visible++;
                    highest = board[row][col].getValue();
                }
            }
            if(visible!=wanted) {
                board[r][col].setValue(0);
//                    System.out.println("RETURN FALSE top: "+ col);
                return false;
            }
        }

        //kolumna z dołu:
        visible = 1;
        highest = board[size - 1][col].getValue();
        wanted = constraints[1][col];
        if (wanted != 0) {
            for (int row = size - 1; row >= 0; row--) {
                /*if(row==r){
                    if(num > highest){
                        visible++;
                        highest=num;
                    }
                }else */if (board[row][col].getValue() > highest) {
                    visible++;
                    highest = board[row][col].getValue();
                }
            }
            if (visible != wanted) {
//                    System.out.println("RETURN FALSE bottom: "+ col);
                board[r][col].setValue(0);
                return false;
            }
        }
        board[r][col].setValue(0);
        return true;
    }

    private boolean checkRow(int row, int c, int num){
        board[row][c].setValue(num);
        //z lewej:
        int visible = 1;
        int highest = board[row][0].getValue();
        int wanted = constraints[2][row];
        if(wanted!=0){
            for(int col = 0; col<size; col++){
               /* if(col==c){
                    if(num > highest){
                        visible++;
                        highest=num;
                    }
                }else */if(board[row][col].getValue() > highest){
                    visible++;
                    highest = board[row][col].getValue();
                }
            }
            if(visible!=wanted) {
                board[row][c].setValue(0);
//                System.out.println("RETURN FALSE LEFT: "+ row);
                return false;
            }
        }

        visible = 1;
        highest = board[row][size-1].getValue();
        wanted = constraints[3][row];
        if(wanted!=0){
            for(int col = size-1; col>=0; col--){
                /*if(col==c){
                    if(num > highest){
                        visible++;
                        highest=num;
                    }
                }else */if(board[row][col].getValue()>highest){
                    visible++;
                    highest = board[row][col].getValue();
                }
            }
            if(visible!=wanted) {
                board[row][c].setValue(0);
//                    System.out.println("RETURN FALSE right: "+ row);
                return false;
            }
        }
        board[row][c].setValue(0);
        return true;
    }

    public void printBoard(){
        System.out.print("-----------------------\n    ");
        for(int i = 0; i<size; i++){
            System.out.print(constraints[0][i]+" ");
        }

        System.out.print("\n    ");
        for(int i = 0; i<size; i++){
            System.out.print("- ");
        }
        System.out.println();
        for(int row = 0; row<size; row++){
            System.out.print(constraints[2][row]+" | ");
            for(int col = 0; col<size; col++){
                System.out.print(board[row][col].getValue()+" ");
            }
            System.out.println(" | "+constraints[3][row]);
        }
        System.out.print("    ");
        for(int i = 0; i<size; i++){
            System.out.print("- ");
        }
        System.out.print("\n    ");
        for(int i = 0; i<size; i++){
            System.out.print(constraints[1][i]+" ");
        }
        System.out.println("\n-----------------------");
    }

    public boolean removeFromDomain(int row, int col, int val){
        if(board[row][col].domain[val-1]==0)
            return false;
        else{
            board[row][col].domain[val-1] = 0;
            return true;
        }
    }
}
