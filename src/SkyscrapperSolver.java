public class SkyscrapperSolver extends Solver {
    SGame loaded_board;
    SGame board;
    boolean solved = false;

    public SkyscrapperSolver(SGame game){
        loaded_board = game;
        steps = 0;
        solutions = 0;
        time = 0;
        board = new SGame(loaded_board);

    }

    public void solve(int option){
        if(option == 0)

//            if(backtracking(0,0)) {
//                System.out.println("--------Solved bt--------");
//                System.out.println("In " + steps + " steps");
//                System.out.println("-------------------------");
//
//            }
//        System.out.println(solutions);
//            if(forwardChecking(0,0)) {
//                System.out.println("--------Solved bt--------");
//                System.out.println("In " + steps + " steps");
//                System.out.println("-------------------------");
//
//            }
            if(backtracking2(0,0)) {
//                System.out.println("--------Solved bt--------");
//                System.out.println("In " + steps + " steps");
//                System.out.println("-------------------------");
//                board.printBoard();

            }
    }

    public boolean bt(int x, int y){
        if(solved) return true;
        else {
            steps++;
            for (int i = 1; i < board.size + 1; i++) {
                if (board.check(x, y, i)) {
                    board.board[x][y].setValue(i);
                    System.out.println("----------------");
                    board.printBoard();
                    if (board.nextNode(x, y) == null) {
                            //finishTime = System.nanoTime();
                            System.out.println("*********** SKY SOLVED *************");
                            System.out.println("IN " + steps + " STEPS");
                            System.out.println("*********************************** \n");
                            board.printBoard();
                            solved = true;
                            return true;
                    } else {
                        int nextX = board.nextNode(x, y).getCord_x();
                        int nextY = board.nextNode(x, y).getCord_y();
                        bt(nextX, nextY);
                    }
                }
                board.board[x][y].setValue(0);
            }
            if(solved)
                return true;
            else
                return false;
        }
    }

    public void zrobTestIDrukuj(){
        if(sprawdzTest())
            board.printBoard();
        else
            System.out.println("Lipa jakas");

    }

    public boolean sprawdzTest(){
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                if (board.board[i][j].getValue() == 0) {
                    row = i;
                    col = j;

                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int num = 1; num <= board.size; num++) {
            if (board.check(row, col, num)) {
                board.board[row][col].setValue(num);
                if (sprawdzTest()) {
//                    System.out.println(1);
                    return true;
                } else {
                    board.board[row][col].setValue(0);
                }
            }
        }
        return false;
    }

    public boolean backtracking2(int row, int col){
        for(int i=1;i<board.size+1;i++){
            //todo val = heuristicsGetVal(board,row,col)
            int val = i;
            if(board.check(row,col,i)) {
                board.board[row][col].value = i;
//                System.out.println("["+row+" "+col+"]");
                if(board.check(row,col,i)) {
                    steps++;
//                    System.out.println("val accepted: row: "+row+" col: "+col+" num: "+i);
//                    board.printBoard();
//                    System.out.println("******");
                    if (board.nextNode(row, col) == null) {
                        //no empty field was found -> we found the solution
                        System.out.println("*********** BT SOLVED *************");
                        System.out.println("IN " + steps + " STEPS");
                        System.out.println("*********************************** \n");
                        board.printBoard();
//                    return true;
                        board.board[row][col].value = 0;
                    } else {
                        int nextX = board.nextNode(row, col).getCord_x();
                        int nextY = board.nextNode(row, col).getCord_y();
                        boolean correct = backtracking2(nextX, nextY);
                        //going back
                        if (!correct) {
//                        System.out.println("incorrect");
                            board.board[row][col].value = 0;
                        }
                    }
                }
                else {
//                    System.out.println("broke constraint");
                    board.board[row][col].value = 0;
                }
            }
            else{
                // no unassigned value left
//                System.out.println("alreadyAssignedValue ["+row+" "+col+"] "+i);
//                board[row][col].value = 0;
//                if(i==board.length) return false;
            }
            board.board[row][col].value = 0;
        }
        return true;
    }

}
