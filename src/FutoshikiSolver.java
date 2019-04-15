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

    public boolean backtracking(int x, int y, int option){
        if(board.isComplete()) {
            board.printBoard();

            System.out.println("*********** BT SOLVED *************");
            System.out.println("IN " + steps + " STEPS");
            System.out.println("*********************************** \n");
            return true;
        }
        board.printBoard();
        System.out.println();
        System.out.println("---------");
        System.out.println();
        for(int num = 1; num <= board.size; num++){
            if(board.check(x, y, num)){
                if(!board.board[x][y].isConstant) {
                    board.board[x][y].setValue(num);
                    steps++;
                    System.out.println("pupa");
                }
                if(board.getNext(x, y, option) == null){
                    return false;
                }
                if(backtracking(board.getNext(x, y, option).getCord_x(), board.getNext(x, y, option).getCord_y(), option)) {
                    return true;
                }
                board.getNext(x, y,option).printNode();
            }
            System.out.println(board.isComplete());
            board.board[x][y].setValue(0);

        }
        if(board.isComplete()) {
            return true;
        }
        return false;
    }

    public boolean forwardChecking(int x, int y, int option){
        if(board.isComplete()) {
            board.printBoard();
            System.out.println("*********** FC SOLVED *************");
            System.out.println("IN " + steps + " STEPS");
            System.out.println("*********************************** \n");
            return true;
        }
        steps++;
        for(int num = 1; num <= board.size; num++){
            if(board.check(x, y, num)){
                board.board[x][y].setValue(num);
                board.calculateDomains();
                boolean checkDomains = true;
                for(int i = 0; i<board.size && checkDomains; i++){
                    if(board.isDomainEmpty(i, y) || board.isDomainEmpty(x, i))
                        checkDomains = false;
                }
                if(checkDomains) {
                    if (board.nextNode(x, y) == null)
                        return false;
                    if (forwardChecking(board.getNext(x, y, option).getCord_x(), board.getNext(x, y, option).getCord_y(), option))
                        return true;
                }
            }
            board.board[x][y].setValue(0);
        }
        return false;
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