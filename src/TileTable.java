public class TileTable
{
    private Tile[][] tiles = new Tile[8][8];
    private PieceList pieceList;
    private Player player;
    private Player opponent;
    private Engine engine;
    private Canvas canvas;

    public TileTable(PieceList pieceList, Engine engine, Player player, Player opponent){
        this.pieceList = pieceList;
        this.engine = engine;
        this.player = player;
        this.opponent = opponent;
    }

    public void createTiles (Canvas canvas) {
        this.canvas = canvas;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i, j, engine);
                canvas.add(tiles[i][j]);
            }
    }

    public void threatenTiles(){
        unthreatenTiles();
        for (Piece p: opponent.getPieces()){
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    if (p.getThreatens()[i][j]){
                        tiles[i][j].setThreatened(true,p);
                    }
        }
    }

    public void unthreatenTiles() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tiles[i][j].setThreatened(false, null);
    }

    public void undrawMoves(){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tiles[i][j].undrawMove();
    }

    public void printPieces(){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (tiles[i][j].getPieceIn())
                    System.out.println("There is a "+tiles[i][j].getPiece().getPlayer().getName()+" "+tiles[i][j].getPiece().getType()+" at "+i+j);
    }

    public void printThreatened(){
        for (int i = 0; i < 8; i++)
        for (int j = 0; j < 8; j++) {
            if (tiles[i][j].getThreatened())
            System.out.println("Tile " +i+" "+j+" is threatened by "+
                    tiles[i][j].getThreateningPiece().getType() + " at "+
                    tiles[i][j].getThreateningPiece().get_i()+" "+
                    tiles[i][j].getThreateningPiece().get_j());
            else System.out.println("Tile " + i+" "+j+" is not threatened");
        }
    }

    public Tile getTile(int i, int j){
        return tiles[i][j];
    }
    public Tile[][] getTiles(){return tiles;}
}
