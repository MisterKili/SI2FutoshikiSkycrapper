public class Main {

    public static void main(String[] args) {
//        Loader loader = new Loader();
//        String path = "D:\\Dokumenty\\PWR\\Semestr VI\\SI\\Lab2\\ai-lab2-data\\test_futo_4_0.txt";
//        loader.loadFutoshiki(path);

        Loader loader = new Loader();
//        FGame game = loader.readFuto("test_futo_4_0.txt");

        //game.printBoard();
        //game.printConstraints();
//        FutoshikiSolver f_solver = new FutoshikiSolver(game);
        //f_solver.solve(0);

//        SGame s_game = loader.readSky("test_sky_4_0.txt");
//        SkyscrapperSolver sky_solver = new SkyscrapperSolver(s_game);
//        sky_solver.solve(0);


       SGame gameSky = loader.readSky("test_sky_4_1.txt");
       System.out.println("Skyscrapper");
       SkyscrapperSolver solver = new SkyscrapperSolver(gameSky);
//       System.out.println(solver.sprawdzTest());
//        solver.zrobTestIDrukuj();
        solver.solve(0);
//       gameSky.printBoard();
       gameSky.printConstraints();
    }
}
