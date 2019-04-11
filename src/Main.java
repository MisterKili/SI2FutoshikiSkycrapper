public class Main {

    public static void main(String[] args) {
//        Loader loader = new Loader();
//        String path = "D:\\Dokumenty\\PWR\\Semestr VI\\SI\\Lab2\\ai-lab2-data\\test_futo_4_0.txt";
//        loader.loadFutoshiki(path);

        Loader loader = new Loader();
        FGame game = loader.readFuto("test_futo_4_0.txt");

        game.printBoard();
        game.printConstraints();
        System.out.println(game.nextNode(0,1).value);
        FutoshikiSolver f_solver = new FutoshikiSolver(game);
        f_solver.solve(0);


//        SGame gameSky = loader.readSky("test_sky_4_0.txt");
//        System.out.println("Skyscrapper");
//        gameSky.printBoard();
//        gameSky.printConstraints();
    }
}
