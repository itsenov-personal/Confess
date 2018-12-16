public class Queen extends Piece{

	
	public Queen(int i,int j, Engine engine, Player player){
		super(i,j,engine, player);
		super.setType("queen");
		super.readImage();
	}
	
	public void updateMoves(){
		movesReset();
        Tile[][] tiles = engine.getTiles();
        int[] neighbors = {-1, 0, 1};
		for (int s: neighbors)
			for (int t: neighbors)
				if (!((s == 0) && (t == 0))) {
					int p = i + s;
					int q = j + t;
					while (p >= 0 && q >= 0 && p < 8 & q < 8) {
						moves[p][q] = checkMove(p, q);
						if (tiles[p][q].getPieceIn())
							break;
						p += s;
						q += t;
					}
				}
	}

	public void updateThreatens(){
		threatensReset();
		Tile[][] tiles = engine.getTiles();
		int[] neighbors = {-1, 0, 1};
		for (int s: neighbors)
			for (int t: neighbors)
				if (!((s == 0) && (t == 0))) {
					pinPieces(s, t);
					int p = i + s;
					int q = j + t;
					while (p >= 0 && q >= 0 && p < 8 & q < 8) {
						threatens[p][q] = true;
						if (tiles[p][q].getPieceIn())
							break;
						p += s;
						q += t;
					}
				}
	}
}