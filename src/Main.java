public class Main {

    public static void main(String[] args) {

        Loader loader = new Loader();

        /*
        *
        * Futoshiki
        *
        * option:
        *           0 - BT
        *           1 - FC
        *
        * heuristic:
        *           0 - bez
        *           1 - najbardziej ograniczona zmienna
        *           2 - najmniej ograniczona zmienna
        * */

        FGame futoshiki_game = loader.readFuto("test_futo_4_0.txt");

        FutoshikiSolver futoshiki_solver = new FutoshikiSolver(futoshiki_game);

        futoshiki_solver.solve(0, 0);
        futoshiki_solver.solve(0, 1);
        futoshiki_solver.solve(0, 2);



        /*
        *
        * Skyscrapper
        *
        *  option:
        *           0 - BT
        *           1 - FC
        *           2 - BT with fill first
        *           3 - FC with fill first
        *
        *  heuristic:
        *           0 - bez
        *           1 - najbardziej ograniczona zmienna
        *           2 - najmniej ograniczona zmienna
        *
        * */

        SGame skyscarpper_game = loader.readSky("test_sky_4_0.txt");
        SkyscrapperSolver skyscrapper_solver = new SkyscrapperSolver(skyscarpper_game);

        skyscrapper_solver.solve(0, 0);
        skyscrapper_solver.solve(0, 1);
    }
}
