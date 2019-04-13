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

           if(backtracking(0,0)) {

            }
        //TODO: wybór algorytmu i heurystyki
    }

    public boolean backtracking(int row, int col){
        for(int i=1;i<board.size+1;i++){
            //todo val = heuristicsGetVal(board,row,col)
            int val = i;
            if(board.check(row,col,i)) {
                board.board[row][col].value = i;
                if(board.check(row,col,i)) {
                    steps++;
                    if (board.nextNode(row, col) == null) {
                        //no empty field was found -> we found the solution
                        board.printBoard();
                        System.out.println("*********** BT SOLVED *************");
                        System.out.println("IN " + steps + " STEPS");
                        System.out.println("*********************************** \n");

                        board.board[row][col].value = 0;
                    } else {
                        int nextX = board.nextNode(row, col).getCord_x();
                        int nextY = board.nextNode(row, col).getCord_y();
                        boolean correct = backtracking(nextX, nextY);
                        //going back
                        if (!correct) {
                            board.board[row][col].value = 0;
                        }
                    }
                }
                else {
                    board.board[row][col].value = 0;
                }
            }
            board.board[row][col].value = 0;
        }
        return true;
    }

    //TODO: forward

}
