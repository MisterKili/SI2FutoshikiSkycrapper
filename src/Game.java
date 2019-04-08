import java.util.ArrayList;

public abstract class Game {

    int size;
    Node[][] game;


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
                System.out.print(game[i][j].value);
            }
            System.out.println();
        }
    }

    //TODO: sprawdzanie wszystkich ograniczeń
    public boolean check(){ //metoda wywołuje checkConstarints i checkCross, true jeśli można wpisąć liczbę
        return false;
    }

    //TODO: sprawdzenie czy w krzyżu liczba już wystąpiłą
    public boolean checkCross(){ //true jeśli może być umieszczona, false jeśli liczba już się pojawiła
        return false;
    }


    public abstract void printConstraints();
    public abstract void addConstraints(ArrayList<String> cons);
    //TODO: nadpisanie w FGame i SGame
    public abstract boolean checkConstraints(); //musi być nadpisane w kazdej z gier osobno, true jeśli liczba nie łamie ograniczeń
}
