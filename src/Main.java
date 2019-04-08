public class Main {

    public static void main(String[] args) {
//        Loader loader = new Loader();
//        String path = "D:\\Dokumenty\\PWR\\Semestr VI\\SI\\Lab2\\ai-lab2-data\\test_futo_4_0.txt";
//        loader.loadFutoshiki(path);

        Loader loader = new Loader();
        FGame game = loader.read("test_futo_4_0.txt");
        game.printBoard();
        game.printConstraints();
    }
}
