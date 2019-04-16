public class SkyscrapperSolver extends Solver {
    SGame loaded_board;
    SGame board;
    boolean solved = false;


    public SkyscrapperSolver(SGame game){
        loaded_board = game;
        board = game;
        steps = 0;
        solutions = 0;
        time = 0;
    }

    public void solve(int option, int heuristic){
        this.heuristic = heuristic;
        if(option == 0)
            startBT();
        if(option == 1)
            startFC();
        if(option == 2)
            startBTwithFirst();
        if(option == 3)
            startFCwithFirst();
    }

    private void startBT(){
        time = System.nanoTime();
        steps = 0;
        board = new SGame(loaded_board);
        setStart();
        System.out.println("Solving Skyscprapper - backtracking");
        backtracking(startX,startY, heuristic);
    }

    private void startFC(){
        time = System.nanoTime();
        steps = 0;
        board = new SGame(loaded_board);
        setStart();
        System.out.println("Solving Skyscprapper - forward checking");
        forwardChecking(startX,startY, heuristic);
    }

    private void startBTwithFirst(){
        time = System.nanoTime();
        steps = 0;
        setStart();
        board = new SGame(loaded_board);
        fillConstantValues();
        System.out.println("Solving Skyscprapper - backtracking with filled first values");
        backtracking(startX,startY, heuristic);
    }

    private void startFCwithFirst(){
        time = System.nanoTime();
        steps = 0;
        board = new SGame(loaded_board);
        fillConstantValues();
        setStart();
        System.out.println("Solving Skyscprapper - forward checking with filled first values");
        forwardChecking(startX,startY, heuristic);
    }

    public boolean backtracking(int row, int col, int option){
//        System.out.println("step: "+steps);
//        board.printBoard();
        if(board.board[row][col].isConstant  && board.getNext(row, col, option) != null){
            int nextX = board.getNext(row, col, option).getCord_x();
            int nextY = board.getNext(row, col, option).getCord_y();
            boolean correct = backtracking(nextX, nextY, option);
            //going back
            if (!correct) {
                board.board[row][col].setValue(0);
            }
        }else {
            for (int i = 1; i < board.size + 1; i++) {
                //todo val = heuristicsGetVal(board,row,col)
                int val = i;
                if (board.check(row, col, i)) {
                    board.board[row][col].setValue(i);
//                    board.calculateDomains();
//                    board.printBoard();
                    if (board.check(row, col, i)) {
                        steps++;
                        if (board.getNext(row, col, option) == null) {
                            //no empty field was found -> we found the solution
                            board.printBoard();
                            double endTime = System.nanoTime();
                            System.out.println("*********** BT SOLVED *************");
                            System.out.println("IN " + steps + " STEPS");
                            System.out.println("IN " + (endTime-time)/1000000000 + " SEC");
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



    //TODO: forward

    public boolean forwardChecking(int row, int col, int option){

        if(board.board[row][col].isConstant && board.getNext(row, col, option) != null){
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
                    if(board.checkForward(row,col, val)){
                        board.board[row][col].setValue(i);

                        board.calculateDomains();
                        boolean checkDomains = true;
                        for (int j = 0; j < board.size && checkDomains; j++) {
                            if (board.isDomainEmpty(j, col) || board.isDomainEmpty(row, j))
                                checkDomains = false;
                        }
                        if (checkDomains && board.check(row, col, val)) {
                            steps++;
                            if (board.getNext(row, col, option) == null) {
                                board.printBoard();
                                double endTime = System.nanoTime();
                                System.out.println("*********** FC SOLVED *************");
                                System.out.println("IN " + steps + " STEPS");
                                System.out.println("IN " + (endTime-time)/1000000000 + " SEC");
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
                    } else {
                        board.board[row][col].setValue(0);
                    }
                }
                board.board[row][col].setValue(0);
            }
        }
        return true;
    }

    public void fillConstantValues(){
        System.out.println("Board size "+board.board.length);
        System.out.println("Constants "+board.constraints.length);
        for(int i = 0; i<board.constraints.length; i++){
            for(int j = 0; j<board.size; j++){
                if(board.constraints[i][j] == board.size){
                    if(i == 0){
                        fillColumnAsc(j);
                    }
                    if(i == 1){
                        fillColumnDesc(j);
                    }
                    if(i == 2){
                        fillRowAsc(j);
                    }
                    if(i == 3){
                        fillRowDesc(j);
                    }
                }
                if(board.constraints[i][j] == 1){
                    if(i == 0){
                        board.board[0][j].value = board.size;
                        board.board[0][j].isConstant = true;
                    }
                    if(i == 1){
                        board.board[board.size-1][j].value = board.size;
                        board.board[board.size-1][j].isConstant = true;
                    }
                    if(i == 2){
                        board.board[j][0].value = board.size;
                        board.board[j][0].isConstant = true;
                    }
                    if(i == 3){
                        board.board[j][board.size-1].value = board.size;
                        board.board[j][board.size-1].isConstant = true;
                    }
                }
            }
        }
    }

    //wypelnia kolumne rosnaco
    public void fillColumnAsc(int col){
        for(int i = 0; i<board.size; i++){
            board.board[i][col].value = i+1;
            board.board[i][col].isConstant = true;
        }
    }

    //wypelnia wiersz rosnaco
    public void fillRowAsc(int row){
        for(int i = 0; i<board.size; i++){
            board.board[row][i].value = i+1;
            board.board[row][i].isConstant = true;
        }
    }

    //wypelnia kolumne malejaco
    public void fillColumnDesc(int col){
        for(int i = board.size-1; i>=0; i--){
            board.board[i][col].value = i+1;
            board.board[i][col].isConstant = true;
        }
    }

    //wypelnia wiersz malejaco
    public void fillRowDesc(int row){
        for(int i = board.size-1; i>=0; i--){
            board.board[row][i].value = i+1;
            board.board[row][i].isConstant = true;
        }
    }

    private void setStart(){
        if(heuristic == 0){
            startX = 0;
            startY = 0;
        }
        if(heuristic == 1){
            Node start = board.mostConstrainedNode();
            startX = start.cord_x;
            startY = start.cord_y;
        }
        if(heuristic == 2){
            Node start = board.leastConstrainedNode();
            startX = start.cord_x;
            startY = start.cord_y;
        }
    }

}