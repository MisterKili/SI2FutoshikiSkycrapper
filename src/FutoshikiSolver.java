public class FutoshikiSolver extends Solver{
    FGame loaded_board;
    FGame board;

    public FutoshikiSolver(FGame game){
        loaded_board = game;
        steps = 0;
        solutions = 0;
        time = 0;
        board = new FGame(loaded_board);
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
            if(forwardChecking(0,0)) {
                System.out.println("--------Solved bt--------");
                System.out.println("In " + steps + " steps");
                System.out.println("-------------------------");

            }
    }

    public boolean backtracking(int x, int y){
        System.out.println("-------------------------");
        board.printBoard();
        System.out.println(board.isComplete());
        if(board.isComplete())
            return true;
        steps++;
        for(int num = 1; num <= board.size; num++){
            if(board.check(x, y, num)){
                if(!board.board[x][y].isConstant) {
                    board.board[x][y].setValue(num);

                }
                if(board.nextNode(x, y) == null)
                    return false;
                if(backtracking(board.nextNode(x, y).getCord_x(), board.nextNode(x, y).getCord_y()))
                    return true;
            }
            board.board[x][y].setValue(0);
        }
        if(board.isComplete())
            return true;
        return false;
    }

    public boolean forwardChecking(int x, int y){
        System.out.println("-------------------------");
        board.printBoard();
        for (int i=0; i<board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                System.out.print("x "+i +" y "+j);
                for (int k = 0; k < board.size; k++)
                    System.out.print(board.board[i][j].domain[k] + " ");
                System.out.println();
            }

        }
        System.out.println(board.isComplete());
        if(board.isComplete())
            return true;
        steps++;
        for(int num = 1; num <= board.size; num++){
            if(board.check(x, y, num)){
                board.board[x][y].setValue(num);
                board.calculateDomains();
                boolean checkDomains = true;
                for(int i = 0; i<board.size && checkDomains; i++){
                    System.out.println(board.isDomainEmpty(i, y));
                    System.out.println(board.isDomainEmpty(x, i));
                    if(board.isDomainEmpty(i, y) || board.isDomainEmpty(x, i))
                        checkDomains = false;
                }
                if(checkDomains) {

                    if (board.nextNode(x, y) == null)
                        return false;
                    if (forwardChecking(board.nextNode(x, y).getCord_x(), board.nextNode(x, y).getCord_y()))
                        return true;
                }
            }
            board.board[x][y].setValue(0);
        }
        return false;
    }

    public boolean backtracking2(int x, int y) {
        if (board.isComplete()) return false;
        else {
            steps++;
            for (int i = 1; i < board.size + 1; i++) {
                if (board.check(x, y, i)) {
                    board.board[x][y].setValue(i);
                    //board.printBoard();
                    //System.out.println();
                    if (board.nextNode(x, y) == null) {
                        System.out.println("*********** BT SOLVED *************");
                        System.out.println("IN " + steps + " STEPS");
                        System.out.println("*********************************** \n");
                        board.printBoard();
                        solutions++;
                        return false;
                    } else {
                        int newX = board.nextNode(x, y).getCord_x();
                        int newY = board.nextNode(x, y).getCord_y();
                        backtracking2(newX, newY);
                    }
                }
                board.board[x][y].setValue(0);
            }
//
            if(board.isComplete())
                return true;
            else
                return false;
        }

    }
}
