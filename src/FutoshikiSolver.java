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
            backtracking(0, 0);
    }

    public boolean backtracking(int x, int y){
        System.out.println(board.isComplete());
        board.printBoard();
        if(board.isComplete())
            return true;
        else{
            Node current = board.getNode(x, y);
            for(int num = 1; num<= board.size; num++){
                System.out.println("check: "+ board.check(x, y, num) + ", " + x +", "+y+", num: "+num);
                boolean check = board.check(x, y, num);
                if(board.check(x, y, num)){
                    board.getNode(x, y).setValue(num);
                    steps++;
                    if(board.nextNode(x, y) == null){
                        time = System.nanoTime();
                        System.out.println("--------Solved bt--------");
                        System.out.println("In "+steps+" steps");
                        System.out.println("-------------------------");
                        return true;
                    }else if(board.nextNode(x, y) != null){
                        current = board.nextNode(x, y);
                        System.out.println(current.getCord_x()+", "+ current.getCord_y());
                        backtracking(current.getCord_x(), current.getCord_y());
                    }
                }

            }
            current.setValue(0);

        }
        if(board.isComplete())
            return true;
        else return false;
    }


}
