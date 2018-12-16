import java.util.ArrayList;

public class TileLine extends ArrayList<Tile> {

    private Tile[][] tiles;
    private ArrayList<Piece> pieces = new ArrayList<>();

    public TileLine(Tile[][] tiles, int i, int j, String direction){
        this.tiles = tiles;
        int incI = 0, incJ = 0;
        if (direction.equals("up") || direction.equals("upright") ||
            direction.equals("upleft"))
            incJ = 1;
        if (direction.equals("down") || direction.equals("downright") ||
            direction.equals("downleft"))
            incJ = -1;
        if (direction.equals("right") || direction.equals("upright") ||
            direction.equals("downright"))
            incI = 1;
        if (direction.equals("left") || direction.equals("upleft") ||
            direction.equals("downleft"))
            incI = -1;
        addTiles(i, j, incI, incJ);
    }

    public TileLine(Tile[][] tiles, int i, int j, int incI, int incJ){
        this.tiles = tiles;
        addTiles(i, j, incI, incJ);
    }

    public void addTiles(int i, int j, int incI, int incJ) {
        while (i >= 0 && i < 8 && j >= 0 && j < 8) {
            this.add(tiles[i][j]);
            if (tiles[i][j].getPieceIn())
                pieces.add(tiles[i][j].getPiece());
            i += incI;
            j += incJ;
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public boolean existsRook(){
        for (Piece p: pieces){
            if (p.getType().equals("rook"))
                return true;
        }
        return false;
    }

    public boolean existsPlayerKing(Player player){
        for (Piece p: pieces) {
            if (p.getType().equals("king") && p.getPlayer() == player)
                return true;
        }
        return false;
    }

    public Rook getRook() {
        for (Piece p : pieces)
            if (p.getType().equals("rook")) {
                return (Rook) p;
            }
        return null;
    }
}
