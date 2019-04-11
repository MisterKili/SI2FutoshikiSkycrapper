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
            if(bt(0,0)) {
                System.out.println("--------Solved bt--------");
                System.out.println("In " + steps + " steps");
                System.out.println("-------------------------");

            }
    }

    public boolean bt(int x, int y){
        if(solved) return true;
        else {
            steps++;
            for (int i = 1; i < board.size + 1; i++) {
                if (board.check(x, y, i)) {
                    board.board[x][y].setValue(i);
                    //printBoard(board);
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
}
