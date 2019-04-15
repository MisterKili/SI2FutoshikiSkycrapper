public class FutoshikiSolver extends Solver{
    FGame loaded_board;
    FGame board;
    boolean solved = false;


    public FutoshikiSolver(FGame game){
        loaded_board = game;
        steps = 0;
        solutions = 0;
        time = 0;
        board = new FGame(loaded_board);

    }

    public void solve(int option, int heuristic){
            this.heuristic = heuristic;
            if(option == 0)
                startBT();
            if(option == 1)
                startFC();
    }

    private void startBT(){
        steps = 0;
        board = new FGame(loaded_board);
        setStart();
        System.out.println("Solving Futoshiki - backtracking");
        backtracking(startX,startY, heuristic);
    }

    private void startFC(){
        steps = 0;
        board = new FGame(loaded_board);
        setStart();
        System.out.println("Solving Futoshiki - forward checking");
        forwardChecking(startX,startY, heuristic);
    }

    public boolean backtracking(int row, int col, int option) {
        if (board.board[row][col].isConstant) {
            int nextX = board.getNext(row, col, option).getCord_x();
            int nextY = board.getNext(row, col, option).getCord_y();
            boolean correct = backtracking(nextX, nextY, option);
            //going back
            if (!correct) {
                board.board[row][col].setValue(0);
            }
        } else {
            for (int i = 1; i < board.size + 1; i++) {
                //todo val = heuristicsGetVal(board,row,col)
                int val = i;
                if (board.check(row, col, i)) {
                    board.board[row][col].setValue(i);
                    if (board.check(row, col, i)) {
                        steps++;
                        if (board.getNext(row, col, option) == null) {
                            //no empty field was found -> we found the solution
                            board.printBoard();
                            System.out.println("*********** BT SOLVED *************");
                            System.out.println("IN " + steps + " STEPS");
                            System.out.println("*********************************** \n");
                            board.board[row][col].setValue(0);
                        } else {
                            int nextX = board.getNext(row, col, option).getCord_x();
                            int nextY = board.getNext(row, col, option).getCord_y();
                            boolean correct = backtracking(nextX, nextY, option);
                            //going back
                            if (!correct) {
                                board.board[row][col].setValue(0);
                            }
                        }
                    } else {
                        board.board[row][col].setValue(0);
                    }
                }
                board.board[row][col].setValue(0);
            }
        }
        return true;
    }

    public boolean forwardChecking(int row, int col, int option){
        if(board.board[row][col].isConstant){
            int nextX = board.getNext(row, col, option).getCord_x();
            int nextY = board.getNext(row, col, option).getCord_y();
            boolean correct = forwardChecking(nextX, nextY, option);
            //going back
            if (!correct) {
                board.board[row][col].setValue(0);
            }
        }else {
            for (int i = 1; i < board.size + 1; i++) {
                //todo val = heuristicsGetVal(board,row,col)
                int val = i;
                if (board.check(row, col, val)) {
//                    board.calculateDomains();
                        board.board[row][col].setValue(i);

                        board.calculateDomains();
                        boolean checkDomains = true;
                        for (int j = 0; j < board.size && checkDomains; j++) {
                            if (board.isDomainEmpty(j, col) || board.isDomainEmpty(row, j))
                                checkDomains = false;
                        }
//                System.out.println("checking domains: "+checkDomains);
//                        board.printDomains();
                        if (checkDomains && board.check(row, col, val)) {
                            steps++;
                            if (board.getNext(row, col, option) == null) {
                                //no empty field was found -> we found the solution
                                board.printBoard();
                                System.out.println("*********** FC SOLVED *************");
                                System.out.println("IN " + steps + " STEPS");
                                System.out.println("*********************************** \n");

                                board.board[row][col].setValue(0);
                            } else {
                                int nextX = board.getNext(row, col, option).getCord_x();
                                int nextY = board.getNext(row, col, option).getCord_y();
                                boolean correct = forwardChecking(nextX, nextY, option);
                                //going back
                                if (!correct) {
                                    board.board[row][col].setValue(0);
                                }
                            }
                        }
                        else {
                        board.board[row][col].setValue(0);
                    }
                }
                board.board[row][col].setValue(0);
            }
        }
        return true;
    }

    private void setStart(){
        if(heuristic == 0){
            startX = 0;
            startY = 0;
        }
        if(heuristic == 1){
            Node start = board.mostConstrainedNode();
            start.printNode();
            startX = start.cord_x;
            startY = start.cord_y;
        }
        if(heuristic == 2){
            Node start = board.leastConstrainedNode();
            start.printNode();
            startX = start.cord_x;
            startY = start.cord_y;
        }
    }
}
