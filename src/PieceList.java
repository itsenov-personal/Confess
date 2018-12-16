import java.awt.*;
import java.util.ArrayList;

public class PieceList extends ArrayList<Piece>{
    private Engine engine;

    private Player white;
    private Player black;
    private Player player;

    public PieceList(Player player){
        this.player = player;
    }
    public PieceList(Player white, Player black, Engine engine){
        this.white = white;
        this.black = black;
        this.engine = engine;
    }
    
    public void defaultPieces(){

        for (int i=0; i < 8; i++)
            this.add(new Pawn(i, 1, engine, white));
        this.add(new Rook (0,0, engine, white));
        this.add(new Knight(1,0, engine, white));
        this.add(new Bishop(2,0, engine, white));
        this.add(new Queen(3,0, engine, white));
        this.add(new King (4,0, engine, white));
        this.add(new Bishop (5,0, engine, white));
        this.add(new Knight (6,0, engine, white));
        this.add(new Rook(7,0,engine, white));
        for (int i=0; i < 8; i++)
            this.add(new Pawn(i, 6, engine, black));
        this.add(new Rook (0,7, engine, black));
        this.add(new Knight(1,7, engine, black));
        this.add(new Bishop(2,7, engine, black));
        this.add(new Queen(3,7, engine, black));
        this.add(new King (4,7, engine, black));
        this.add(new Bishop (5,7, engine, black));
        this.add(new Knight (6,7, engine, black));
        this.add(new Rook(7,7,engine, black));
    }

    public void updateMoves(){
        for (Piece p: this) {
            p.updateMoves();
        }
    }

    public void updateThreatens(){
        for (Piece p: this) {
            p.updateThreatens();
        }
    }

    public void addPiece(Piece piece){
        this.add(piece);
    }
    public void removePiece(Piece piece){this.remove(piece);}
    public void unpinPieces(){
        for (Piece p: this) {
            p.unpin();
        }
    }
    public ArrayList<Piece> getPieces(){
        return this;
    }
    public void drawPieces(Graphics g, Canvas canvas) {
        for (Piece p : this) {
            p.draw(g, canvas);
        }
    }
}
