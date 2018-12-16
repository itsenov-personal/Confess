public class Game {

    private Engine engine;
    private Canvas canvas;
    private Network network;
    private Server server;
    private Frame frame;

    public static void main (String [] args) {
	    new Game(1);
        new Game(0);
    }

    public Game (int gameType) {

//      Declarations
        engine = new Engine (this);

//      Network setup
        if (gameType == 1){ network = new Server(engine); }
        if (gameType == 0){ network = new Client(engine); }
        Thread networkThread = new Thread(network);
        networkThread.start();

//      Engine and frame setup
        canvas = new Canvas (engine);
        frame = new Frame(canvas);
        engine.setCanvas(canvas);
        engine.setNetwork(network);
        engine.startGame(gameType);
        frame.setVisible(true);
    }
}
