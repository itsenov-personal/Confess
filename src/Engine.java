import java.awt.*;
import java.io.IOException;


public class Engine {

//  Game components
    private Game game;
    private Network network;
    private Canvas canvas;
    private Board board = new Board();
//  Gameplay components
    private TileTable tileTable;
    private PieceList pieceList;
    private Piece selectedPiece;
    private Turns turns;
    private Player gamePlayer;
    private Player gameOpponent;
    private Player white;
    private Player black;

    public Engine (Game game) {
        this.game = game;
        white = new Player("white", this);
        black = new Player("black", this);
        pieceList = new PieceList(white,black,this);
    }

    public void startGame(int playerType){
        if (playerType == 1) setPlayer(white);
        if (playerType == 0) setPlayer(black);
        tileTable = new TileTable(pieceList,this, gamePlayer, gameOpponent);
        tileTable.createTiles(canvas);
        pieceList.defaultPieces();
        gameOpponent.getPieces().updateThreatens();
        tileTable.threatenTiles();
        gamePlayer.getPieces().updateMoves();
        turns = new Turns(white,black);
    }

    public void move(int i, int j, int iNew, int jNew, Boolean send){
        Piece movingPiece = tileTable.getTile(i,j).getPiece();
//      Check if move is valid
        if (movingPiece == null) System.out.println("Invalid move, no piece");
        else move(movingPiece, iNew, jNew, send);
    }

    public void move(Piece movingPiece, int iNew, int jNew, Boolean send){
        int i = movingPiece.get_i();
        int j = movingPiece.get_j();
        movingPiece.move(iNew, jNew);
//      Reset board
        gamePlayer.getPieces().unpinPieces();
        gameOpponent.getPieces().updateThreatens();
        tileTable.threatenTiles();
        gamePlayer.getPieces().updateMoves();
        repaint();
//      Switch turns and send move through network
        if (send)
            try { network.sendMove(i,j,iNew,jNew);
        } catch (IOException e){ e.printStackTrace(); }
        turns.changeTurn();
    }


    public void tilePressed(Tile tile){
        if (turns.getTurnPlayer() == gamePlayer){
            if (tile.getMarked()){
                int i = tile.get_i();
                int j = tile.get_j();
                tileTable.undrawMoves();
                move(selectedPiece, i, j, true);
            }
            else{
                if (tile.getPieceIn()){
                    if (tile.getPiece().getPlayer() == gamePlayer){
                        tileTable.undrawMoves();
                        tile.getPiece().drawMoves();
                        selectedPiece = tile.getPiece();
                    }
                    else tileTable.undrawMoves();
                 }
                 else tileTable.undrawMoves();
            }
        }
    }

    public void draw (Graphics g, Canvas canvas) {
        board.paintIcon(canvas, g, 0, 0);
        pieceList.drawPieces(g,canvas);
    }
    public void repaint(){
        canvas.repaint();
    }
    public void setCanvas (Canvas canvas) {
        this.canvas = canvas;
    }
    public void setPlayer(Player player){
        this.gamePlayer = player;
        this.gameOpponent = getOppositePlayer(player);
    }
    public Player getOppositePlayer(Player player){
        if (player == white)
            return black;
        if (player == black)
            return white;
        return null;
    }
    public void setNetwork(Network network){
        this.network = network;
    }
    public Tile getTile(int i, int j){
        return tileTable.getTile(i,j);
    }
    public Tile[][] getTiles(){
        return tileTable.getTiles();
    }
    public Player getWhite(){
        return white;
    }
    public Player getBlack(){
        return black;
    }
    public PieceList getPieceList(){return pieceList;}
    public Piece getSelectedPiece(){
        return selectedPiece;
    }
    public void setSelectedPiece(Piece piece){
        selectedPiece = piece;
    }
}
