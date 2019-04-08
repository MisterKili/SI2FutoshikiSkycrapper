import java.util.ArrayList;

public abstract class Game {

    int size;
    Node[][] game;
    int [][] constraints;


    public int getSize(){
        return size;
    }

    public void setSize(int s){
        game = new Node[s][s];
        size = s;
    }

    public void setValue(int val, int i, int j){
        game[i][j] = new Node(val, i, j);
    }

    public Node getNode(int i, int j){
        return game[i][j];
    }

    protected int getIntFromChar(char letter){
        return letter%65;
    }

    public void printBoard(){
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                System.out.print(game[i][j].value+"\t");
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
            if((game[x][i].getValue() == num && i!=y) || game[i][y].getValue() == num && i!=x)
                return false;
        }
        return true;
    }


    public abstract void printConstraints();
    public abstract void addConstraints(ArrayList<String> cons);
    //TODO: nadpisanie SGame
    public abstract boolean checkConstraints(int x, int y, int num); //musi być nadpisane w kazdej z gier osobno, true jeśli liczba nie łamie ograniczeń
}
