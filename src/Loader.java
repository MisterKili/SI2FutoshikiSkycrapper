import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

    public Loader(){

    }

    public FGame readFuto(String fileName){
        String fullPath = "data/"+fileName;
        File file = new File(fullPath);
        FGame game = new FGame();
        try(Scanner scanner  = new Scanner(file)){
            int dim = scanner.nextInt();
            game.setSize(dim);
            String currLine;
            scanner.nextLine();
            scanner.nextLine();
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
            scanner.nextLine();

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

    public SGame readSky(String fileName){
        String fullPath = "data/"+fileName;
        File file = new File(fullPath);
        SGame game = new SGame();
        try(Scanner scanner  = new Scanner(file)){
            int dim = scanner.nextInt();
//            System.out.println(dim);
            game.setSize(dim);
            scanner.nextLine();
            ArrayList<String> constraints = new ArrayList<>();
            while (scanner.hasNext()){
                constraints.add(scanner.nextLine());
            }
            game.addConstraints(constraints);
//            int []cons;
//            while (scanner.hasNext()){
//                String []splited = scanner.nextLine().split(";");
//                cons = new int [splited.length-1];
//                for(int i = 1; i<splited.length; i++){
//                    cons[i-1] = Integer.parseInt(splited[i]);
//                }
//                constraints.add(cons);
//
//            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }


}
