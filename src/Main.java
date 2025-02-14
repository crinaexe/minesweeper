
import helpers.GameManager;

public class Main {




    public static void main(String[] args) throws Exception {
        new Main().run();
    }

    private void run() throws Exception {
        GameManager gameManager = new GameManager();
        gameManager.start();
    }



}