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
        /*for(int i = 0; i<size; i++)
            for (int j = 0; j<size; j++)
               board[i][j].setValue(0);*/
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

//    public boolean checkConstraints(int row, int col, int num){
//        printBoard();
//        System.out.println();
//        if(checkLeftConstraint(row, col, num) && checkRightConstraint(row, col, num) &&
//            checkUpConstraint(row,col, num) && checkDownConstraint(row, col, num))
//            return true;
//        else
//            return false;
//    }

//    @Override
//    public boolean checkConstraints(int x, int y, int num) {
//        findNode(x,y).setValue(num);
//        //z góry
//        int visible = 1;
//        int wanted = constraints[0][y];
//        int maxVal = board[0][y].value;
//        int counter = 0;
//        for(int i=0;i<size;i++){
//            if(board[i][y].value != 0){
//                counter++;
//            }
//            if(board[i][y].value > maxVal && maxVal!=0){
//                maxVal = board[i][y].value;
//                visible++;
//            }
//            if(wanted!=0 && visible>wanted){
//                findNode(x,y).setValue(0);
//                return false;
//            }
//        }
//        if(counter == size && visible != wanted && wanted != 0) {
//            findNode(x,y).setValue(0);
//            return false;
//        }
//
//        //z dołu
//        visible = 1;
//        wanted = constraints[1][y];
//        maxVal = board[size-1][y].value;
//        counter = 0;
//        for(int i=size-1;i>=0;i--){
//            if(board[i][y].value != 0){
//                counter++;
//            }
//            if(board[i][y].value > maxVal && maxVal!=0){
//                maxVal = board[i][y].value;
//                visible++;
//            }
//            if(wanted!=0 && visible>wanted){
//                findNode(x,y).setValue(0);
//                return false;
//            }
//        }
//        if(counter == size && visible != wanted && wanted != 0) {
//            findNode(x,y).setValue(0);
//            return false;
//        }
//
//        //z lewej
//        visible = 1;
//        wanted = constraints[2][y];
//        maxVal = board[x][0].value;
//        counter = 0;
//        for(int i=0;i<size;i++){
//            if(board[i][y].value != 0){
//                counter++;
//            }
//            if(board[x][i].value>maxVal && maxVal!=0){
//                maxVal = board[x][i].value;
//                visible++;
//            }
//            if(visible>wanted && wanted!=0){
//                findNode(x,y).setValue(0);
//                return false;
//            }
//        }
//        if(counter == size && visible != wanted && wanted != 0) {
//            findNode(x,y).setValue(0);
//            return false;
//        }
//
//        //z prawej
//        visible = 1;
//        wanted = constraints[3][y];
//        maxVal = board[x][size-1].value;
//        counter = 0;
//        for(int i=size-1;i>=0;i--){
//            if(board[i][y].value != 0){
//                counter++;
//            }
//            if(board[x][i].value>maxVal && maxVal!=0){
//                maxVal = board[x][i].value;
//                visible++;
//            }
//            if(visible>wanted && wanted!=0){
//                findNode(x,y).setValue(0);
//                return false;
//            }
//        }
//        if(counter == size && visible != wanted && wanted != 0) {
//            findNode(x,y).setValue(0);
//            return false;
//        }
////        findNode(x,y).setValue(0);
////        printBoard();
////        System.out.println();
//        return true;
//    }

    private boolean isOK(){
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
//            printBoard();
            return isOK();
        }
        //to poniżej to jest zmodyfikowane przez Wiktora

/*        findNode(x,y).setValue(num);

        //z lewej
        int visible = 1;
        int wanted = constraints[2][y];
        int maxVal = board[x][0].value;
        int counter = 0;
        for(int i=0;i<size;i++){
            if(board[x][i].value != 0){
                counter++;
            }
            if(board[x][i].value>maxVal && maxVal!=0){
                maxVal = board[x][i].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                findNode(x,y).setValue(0);
                return false;
            }
        }
        if(counter == size && visible != wanted && wanted != 0) {
            board[x][y].value = 0;
            return false;
        }

//        //z prawej
        visible = 1;
        wanted = constraints[3][y];
        maxVal = board[x][size-1].value;
        counter = 0;
        for(int i=size-1;i>=0;i--){
            if(board[x][i].value != 0){
                counter++;
            }
            if(board[x][i].value>maxVal && maxVal!=0){
                maxVal = board[x][i].value;
                visible++;
            }
            if(visible>wanted && wanted!=0){
                findNode(x,y).setValue(0);
                return false;
            }
        }
        if(counter == size && visible != wanted && wanted != 0) {
            board[x][y].value = 0;
            return false;
        }

        //z góry
        visible = 1;
        wanted = constraints[0][y];
        maxVal = board[0][y].value;
        counter = 0;
        for(int i=0;i<size;i++){
            if(board[i][y].value != 0){
                counter++;
            }
            if(board[i][y].value > maxVal && maxVal!=0){
                maxVal = board[i][y].value;
                visible++;
            }
            if(wanted!=0 && visible>wanted){
                findNode(x,y).setValue(0);
                return false;
            }
            if(board[i][y].value != 0){
                counter++;
            }
        }
        if(counter == size && visible != wanted && wanted != 0) {
            board[x][y].value = 0;
            return false;
        }

        //z dołu
        visible = 1;
        wanted = constraints[1][y];
        maxVal = board[size-1][y].value;
        counter = 0;
        for(int i=size-1;i>=0;i--){
            if(board[i][y].value != 0){
                counter++;
            }
            if(board[i][y].value > maxVal && maxVal!=0){
                maxVal = board[i][y].value;
                visible++;
            }
            if(wanted!=0 && visible>wanted){
                findNode(x,y).setValue(0);
                return false;
            }
        }
        if(counter == size && visible != wanted && wanted != 0) {
            board[x][y].value = 0;
            return false;
        }*/

//        printBoard();
//        System.out.println();

//        findNode(x,y).setValue(0);
        return true;
    }

    public boolean checkLeftConstraint(int row, int col, int num){
        board[row][col].value = num;
        int wanted = constraints[2][row];
        int visible = 1;
        int counter = 0;
        int maxVal = board[row][0].value;
        int tempVal;
        for(int i = 0; i<size; i++){
            tempVal = board[row][i].value;
            if(tempVal > maxVal && maxVal != 0){
                maxVal = tempVal;
                visible++;
            }
            if(tempVal != 0){
                counter++;
            }
            if(visible > wanted && wanted != 0){
                board[row][col].value = 0;
                return false;
            }
        }
        if(counter == size && wanted != 0){
            board[row][col].value = 0;
            return false;
        }

        return true;
    }

    public boolean checkRightConstraint(int row, int col, int num){
        board[row][col].value = num;
        int wanted = constraints[3][row];
        int visible = 1;
        int counter = 0;
        int maxVal = board[row][size-1].value;
        int tempVal;
        for(int i = size-1; i>=0; i--){
            tempVal = board[row][i].value;
            if(tempVal > maxVal && maxVal != 0){
                maxVal = tempVal;
                visible++;
            }
            if(tempVal != 0){
                counter++;
            }
            if(visible > wanted && wanted != 0){
                board[row][col].value = 0;
                return false;
            }
        }
        if(counter == size && wanted != 0){
            board[row][col].value = 0;
            return false;
        }

        return true;
    }

    public boolean checkUpConstraint(int row, int col, int num){
        board[row][col].value = num;
        int wanted = constraints[0][col];
        int visible = 1;
        int counter = 0;
        int maxVal = board[0][col].value;
        int tempVal;
        for(int i = 0; i<size; i++){
            tempVal = board[i][col].value;
            if(tempVal > maxVal && maxVal != 0){
                maxVal = tempVal;
                visible++;
            }
            if(tempVal != 0){
                counter++;
            }
            if(visible > wanted && wanted != 0){
                board[row][col].value = 0;
                return false;
            }
        }
        if(counter == size && wanted != 0){
            board[row][col].value = 0;
            return false;
        }

        return true;
    }

    public boolean checkDownConstraint(int row, int col, int num){
        board[row][col].value = num;
        int wanted = constraints[1][col];
        int visible = 1;
        int counter = 0;
        int maxVal = board[row][0].value;
        int tempVal;
        for(int i = size-1; i>=0; i--){
            tempVal = board[row][i].value;
            if(tempVal > maxVal && maxVal != 0){
                maxVal = tempVal;
                visible++;
            }
            if(tempVal != 0){
                counter++;
            }
            if(visible > wanted && wanted != 0){
                board[row][col].value = 0;
                return false;
            }
        }
        if(counter == size && wanted != 0){
            board[row][col].value = 0;
            return false;
        }
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

}
