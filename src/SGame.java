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

 /*   public boolean checkCrossConstraints(int x, int y){

    }*/
    public boolean checkingForward(int x, int y, int num){
        board[x][y].setValue(num);
        if(nextNode(x,y).countDomainSize() == 1){
            nextNode(x,y).setValue(nextNode(x,y).getFirstValueFromDomain());
            if(isOK()){

            }
        }
        return true;
    }

    public boolean calculateDomains(){
        for (int i=0; i<size; i++){
            for (int j = 0; j<size; j++){
                for (int k = 0; k<size; k++)
                    board[i][j].domain[k] = 0;
                for(int num = 1; num <=size; num++){ //nie wiem czy przy wyszukiwaniu wszystkich rozwiązań, nie trzeba będzie zmodyfikować, ale na razie zostawiam
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



    public boolean checkForward(int row, int col, int val){
//        System.out.println("checking roward: row|col|val"+row+"\t"+col+"\t"+val);
        //z góry
        int wanted = constraints[0][col];
        int visibleBefore = 1;
        int visibleAfter = size - val;
        int highest = board[0][col].getValue();
        if(wanted!=0){
            for(int x = 0; x <= row; x++){
                if(board[x][col].getValue()>highest){
                    visibleBefore++;
                    highest = board[x][col].getValue();
                }
            }
            int visible = visibleBefore + visibleAfter;
            if(visible<wanted){
                return false;
            }
        }
        //z dołu
        wanted = constraints[1][col];
        visibleBefore = 1;
        visibleAfter = size - val;
        highest = board[size- 1][col].getValue();
        if(wanted!=0){
            for(int x = size-1; x>=row; x--){
                if(board[x][col].getValue()>highest){
                    visibleBefore++;
                    highest = board[x][col].getValue();
                }
            }
            int visible = visibleBefore + visibleAfter;
            if(visible<wanted){
                return false;
            }
        }

        //z lewej
        wanted = constraints[2][row];
        visibleBefore = 1;
        visibleAfter = size - val;
        highest = board[row][0].getValue();
        if(wanted!=0){
            for(int x = 0; x<=col; x++){
                if(board[row][x].getValue()>highest){
                    visibleBefore++;
                    highest = board[row][x].getValue();
                }
            }
            int visible = visibleBefore + visibleAfter;
            if(visible<wanted){
                return false;
            }
        }

        //z prawej
        wanted = constraints[3][row];
        visibleBefore = 1;
        visibleAfter = size - val;
        highest = board[row][size-1].getValue();
        if(wanted!=0){
            for(int x = size-1; x>=col; x--){
                if(board[row][x].getValue()>highest){
                    visibleBefore++;
                    highest = board[row][x].getValue();
                }
            }
            int visible = visibleBefore + visibleAfter;
            if(visible<wanted){
                return false;
            }
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

    public boolean removeFromDomain(int row, int col, int val){
        if(board[row][col].domain[val-1]==0)
            return false;
        else{
            board[row][col].domain[val-1] = 0;
            return true;
        }
    }



}
