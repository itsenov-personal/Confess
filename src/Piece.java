import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

abstract public class Piece extends ImageIcon {

	protected int i, j, row, col, x, y;
    protected Boolean hasMoved;
    protected Engine engine;
    protected Boolean[][] moves = new Boolean[8][8];
    protected Boolean[][] threatens = new Boolean[8][8];
    protected Tile[][] tiles;
    protected Tile tile;
    protected Player player;
    protected Player opponent;


    protected Boolean pinned = false;
    protected ArrayList<Tile> pinTiles = null;

    private Image img;
    protected String type;

    private static final int SIZE = 80;
    private static final int CORNER = 75;

    public Piece (int i, int j, Engine engine, Player player) {
        this.i = i;
        this.j = j;
        this.engine = engine;
        tiles = engine.getTiles();
        this.player = player;
        this.opponent = engine.getOppositePlayer(player);
        player.addPiece(this);
        row = i+1;
        col = j+1;
        tile = engine.getTile(i, j);
        tile.setPiece(this);
        hasMoved = false;
        calculateXY();
    }

    public void calculateXY() {
        x = CORNER + (row-1)*(SIZE+1);
        y = (800-CORNER) - (col)*(SIZE+2);
    }

    public void readImage(){
        try {
        img = ImageIO.read(getClass().getResource(type + ".png"));
       } catch (IOException e) {
        System.out.println("Could not read image!");
      }
        setImage(img);
    }

    public abstract void updateMoves();
    public abstract void updateThreatens();

    public void drawMoves(){
        for (int i = 0; i < 8; i++)
            for (int j=0; j < 8; j++){
                if (moves[i][j]){
                    tiles[i][j].drawMove();
                }
            }
    }

    public void move(int iNew, int jNew){
        tile = engine.getTile(iNew,jNew);
        if (tile.getPieceIn())
            tile.getPiece().capturePiece();
        tile.setPiece(this);
        tile = engine.getTile(i, j);
        tile.removePiece();
        this.i = iNew;
        this.j = jNew;
        row = i + 1;
        col = j + 1;
        int newX = CORNER + (row - 1) * (SIZE + 1);
        int newY = (800 - CORNER) - (col) * (SIZE + 2);
        x = newX;
        y = newY;
        hasMoved = true;
    }

    public void pinPieces(int s, int t){
        TileLine line = new TileLine(tiles, i, j, s, t);
        ArrayList<Piece> linePieces = line.getPieces();
        if (linePieces.size() >= 3)
            if (linePieces.get(2) == opponent.getKing()
                    && linePieces.get(1).getPlayer() == opponent)
                linePieces.get(1).pin(line);
    }

    public void pin(TileLine line){
        pinned = true;
        if (pinTiles == null)
            pinTiles = line;
        else
            for (Tile tile: pinTiles) {
                if (!line.contains(tile))
                    pinTiles.remove(tile);
            }
    }

    public void unpin(){
        pinTiles = null;
        pinned = false;
    }

    public boolean checkMove(int s, int t){
        if (checkTilePiece(s,t)) {
            if (pinned) {
                if (pinTiles.contains(tiles[s][t]))
                    return true;
                else return false;
            }
            return true;
        }
        return false;
    }

    protected void movesReset(){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                moves[i][j] = false;
    }

    protected void threatensReset(){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                threatens[i][j] = false;
    }

    private void capturePiece(){
        engine.getTile(i,j).removePiece();
        player.getPieces().removePiece(this);
        engine.getPieceList().removePiece(this);
    }

    public String getType(){
    		return type;
    }
    public void setType(String n){
        this.type = n;
    }
    public int get_i(){
    		return i;
    }
    public int get_j(){
    		return j;
    }
    public Boolean[][] getMoves(){
        return moves;
    }
    public Boolean[][] getThreatens(){ return threatens;}
    public Tile getTile(){
        return tile;
    }
    public Player getPlayer(){
        return player;
    }
    public boolean getHasMoved(){ return hasMoved;}

    public Boolean checkTilePiece(int s, int t){
       Tile[][] tiles = engine.getTiles();
       if (tiles[s][t].getPieceIn()){
           return (tiles[s][t].getPiece().getPlayer() != player);
        }
        else return true;
    }

    public void draw (Graphics g, Canvas canvas){
        this.paintIcon(canvas, g, x, y);
    }
}