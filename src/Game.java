import java.util.ArrayList;

public abstract class Game {

    int size;
    Node[][] board;
    int [][] constraints;


    public int getSize(){
        return size;
    }

    public void setSize(int s){
        board = new Node[s][s];
        size = s;
    }

    public void setInitValue(int val, int i, int j){
        board[i][j] = new Node(val, i, j);
    }

    public Node getNode(int i, int j){
        
        return board[i][j];
    }

    protected int getIntFromChar(char letter){

        return  letter%65;
    }

    public void initDomains(){ //inicjuje puste dziedziny
        for(int i = 0 ; i < size ; i++){
            for(int j=0; j<size; j++){
                board[i][j].domain = new int[size]; //przechowywanie dziedziń nr indeksu + 1,to liczba z dziedziny,  np. indeks = 0 więc liczba 1 z dziedziny ; 0 jeśli nie może być, 1, jeśli może
                for(int k = 0; k < size; k++)
                    board[i][j].domain[k] = 0;
            }
        }
    }

    public boolean calculateDomains(){
        for (int i=0; i<size; i++){
            for (int j = 0; j<size; j++){
                for (int k = 0; k<size; k++)
                    board[i][j].domain[k] = 0;
                for(int num = 1; num <=size; num++){ //nie wiem czy przy wyszukiwaniu wszystkich rozwiązań, nie trzeba będzie zmodyfikować, ale na razie zostawiam
                    if(board[i][j].getValue() == 0 && checkConstraints(i, j, num)){
                        board[i][j].domain[num-1] = 1;
                    }
                    if(board[i][j].getValue() !=0)
                        board[i][j].domain[board[i][j].getValue()-1] = 1;
                }
            }
        }
        return true;
    }

    public boolean recalculateCross(int x, int y){
        for(int i = 0; i<size; i++){
            for(int k=0; k<size; k++){
                board[i][y].domain[k] = 0;
                board[y][i].domain[k] = 0;
            }
            for(int num = 1; num <= size; num++){
                if(i != x) {
                    if (board[i][y].getValue() == 0 && check(i, y, num))
                        board[i][y].domain[num - 1] = 1;
                    if (board[i][y].getValue() != 0)
                        board[i][y].domain[board[i][y].getValue() - 1] = 1;
                }
                if(i != y) {
                    if(board[x][i].getValue() == 0 && check(x, i, num))
                        board[x][i].domain[num - 1] = 1;
                    if(board[x][i].getValue() != 0)
                        board[x][i].domain[board[x][i].getValue() - 1] = 1;
                }

            }
        }
        return true;
    }

    public int counter(int x, int y){ //liczy jak dużą dziedzinę posiada node
        int counter = 0;
        for(int i = 0; i < size; i++)
            if(board[x][y].domain[i] == 1)
                counter++;
        return counter;
    }

    public boolean isDomainEmpty(int x, int y){
        return board[x][y].isDomainEmpty();
    }

    public boolean isComplete(){
        for(int i =0; i<size; i++)
            for(int j=0; j<size; j++)
                if (board[i][j].getValue()==0)
                    return false;
        return true;
    }

    //TODO: ogarnąć co to robić ma, bo ni uja jeszcze nie wiem
    public boolean findNode(int x, int y){
        return true;
    }

    public void printBoard(){
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                System.out.print(board[i][j].value+"\t");
            }
            System.out.println();
        }
    }

    public boolean check(int x, int y, int num){ //metoda wywołuje checkConstarints i checkCross, true jeśli można wpisąć liczbę
        if(!checkCross(x, y, num) || !checkConstraints(x, y, num))
            return false;
        else return true;
    }

    public boolean checkCross(int x, int y, int num){ //true jeśli może być umieszczona, false jeśli liczba już się pojawiła
        for (int i=0; i<size; i++){
            if((board[x][i].getValue() == num && i!=y) || board[i][y].getValue() == num && i!=x)
                return false;
        }
        return true;
    }

    public Node nextNode(int x, int y){
        if(y<board.length-1){
            return board[x][y+1];
        }else if(y==board.length-1 && x<board.length-1){
            return board[x+1][0];
        }
        else if(x == board.length-1 && y == board.length-1){
            return null;
        }
    }

    public abstract void printConstraints();
    public abstract void addConstraints(ArrayList<String> cons);
    //TODO: nadpisanie SGame
    public abstract boolean checkConstraints(int x, int y, int num); //musi być nadpisane w kazdej z gier osobno, true jeśli liczba nie łamie ograniczeń
}
