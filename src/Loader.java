import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

    int futoshiki_n;
    int [][][] futoshiki;


    public Loader(){

    }

    public FGame read(String fileName){
        String fullPath = "data/"+fileName;
        File file = new File(fullPath);
        FGame game = new FGame();
        try(Scanner scanner  = new Scanner(file)){
            int dim = scanner.nextInt();
            game.setSize(dim);
            String currLine;
            System.out.println(scanner.nextLine());
            System.out.println(scanner.nextLine());
            String [] splited;
            int val;
            for (int i = 0; i<dim; i++){
                currLine = scanner.nextLine();
                splited = currLine.split(";");
                for (int j = 0; j<dim; j++){
                    val = Integer.parseInt(splited[j]);
                    game.setValue(val, i, j);
                }
            }
            System.out.println(scanner.nextLine());


        //TODO: wczytywanie ograniczen

            ArrayList<String> constraints = new ArrayList<>();
            while(scanner.hasNext()){
                currLine = scanner.nextLine();
                constraints.add(currLine);
            }
            game.addConstraints(constraints);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }

    public void loadFutoshiki(String filePath){
        FileReader fileReader = null;
        String textLine = "";
        String[] numbers;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("error with opening the file");
            System.exit(1);
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try{

            futoshiki_n = Integer.parseInt(bufferedReader.readLine());
            futoshiki = new int [futoshiki_n][futoshiki_n][5];

            if((textLine = bufferedReader.readLine()).equals("START:")){
                System.out.println(textLine);

                while(!(textLine = bufferedReader.readLine()).equals("REL:")){

                    numbers = textLine.split(";");
                    int j = 0;
                    for(int i = 0; i < futoshiki_n; i++){
                        futoshiki[j][i][0] = Integer.parseInt(numbers[i]);
                        futoshiki[j][i][1] = 0;
                        futoshiki[j][i][2] = 0;
                        futoshiki[j][i][3] = 0;
                        futoshiki[j][i][4] = 0;
                    }
                    j++;
                }
            }
            if(textLine.equals("REL")){
                String[]rel;
                while((textLine = bufferedReader.readLine()) != null){
                    rel=textLine.split(";");
                    int row1 = getIntFromChar(rel[0].charAt(0));
                    int col1 = Integer.parseInt(rel[0].substring(1));
                    int row2 = getIntFromChar(rel[1].charAt(0));
                    int col2 = Integer.parseInt(rel[1].substring(1));
                    int where = UpDownLeftRight(row1, col1, row2, col2);
                    futoshiki[row1][col1][where]= -1;
                }
            }

        }catch (IOException e) {
            System.out.println("error with reading the file");
            System.exit(2);
        }

    }

    private int getIntFromChar(char letter){
        return letter%100;
    }

    private int UpDownLeftRight(int row1, int col1, int row2, int col2){
        if(col1 == col2){
            if(row1 < row2)
                return 1;
            else return 2;
        }else if(col1 < col1)
            return 3;
        return 4;


    }

    public void futoToString(){
        for(int i = 0; i<futoshiki_n; i++){

        }
    }

}
