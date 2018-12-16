public class Bishop extends Piece {

	public Bishop(int i, int j, Engine engine, Player player) {
		super(i, j, engine, player);
		super.setType("bishop");
		super.readImage();
	}

	public void updateMoves() {
		movesReset();
		Tile[][] tiles = engine.getTiles();
		int[] neighbors = {-1, 1};
		for (int s : neighbors)
			for (int t : neighbors) {
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

	public void updateThreatens() {
		threatensReset();
		Tile[][] tiles = engine.getTiles();
		int[] neighbors = {-1, 1};
		for (int s : neighbors)
			for (int t : neighbors) {
				pinPieces(s,t);
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
