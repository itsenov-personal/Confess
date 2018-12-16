public class King extends Piece {

	public King(int i, int j, Engine engine, Player player) {
		super(i, j, engine, player);
		player.setKing(this);
		super.setType("king");
		super.readImage();
	}

	public void updateMoves() {
		movesReset();
		Tile[][] tiles = engine.getTiles();
		int[] neighbors = {0, 1, -1};
		for (int s : neighbors)
			for (int t : neighbors)
				if (i + s < 8 && j + t < 8 && i + s >= 0 && j + t >= 0
						&& !((s == 0) && (t == 0))) {
					if (!tiles[i + s][j + t].getThreatened()) {
						moves[i + s][j + t] = checkTilePiece(i + s, j + t);
					}
				}
		TileLine leftTiles = new TileLine(tiles,i, j, "left");
		if (leftTiles.existsRook()) {
			Rook leftRook = leftTiles.getRook();
			if (canCastle(leftRook) != 0){
				moves[i - 2][j] = true;
			}
		}
		TileLine rightTiles = new TileLine(tiles,i,j,"right");
		if (rightTiles.existsRook()) {
			Rook rightRook = rightTiles.getRook();
			if (canCastle(rightRook) != 0) {
				moves[i + 2][j] = true;
			}
		}
	}

	public void updateThreatens() {
		threatensReset();
		Tile[][] tiles = engine.getTiles();
		int[] neighbors = {0, 1, -1};
		for (int s : neighbors)
			for (int t : neighbors)
				if (i + s < 8 && j + t < 8 && i + s >= 0 && j + t >= 0
						&& !((s == 0) && (t == 0))) {
					threatens[i + s][j + t] = true;
				}
	}

	@Override
	public void move(int iNew, int jNew){
		Tile[][] tiles = engine.getTiles();
		if (Math.abs(iNew-i) == 2) {
			if (iNew < i){
				TileLine leftTiles = new TileLine(tiles, i, j, "left");
				castle(leftTiles.getRook());
			}
			if (iNew > i){
				TileLine rightTiles = new TileLine(tiles, i, j, "right");
				castle(rightTiles.getRook());
			}
		}
		else super.move(iNew, jNew);

	}

	public void castle(Rook rook){

		int direction;
		if (rook.get_i() < i) direction = -1;
		else direction = 1;

		rook.move(i + direction, j);
		super.move(i + 2*direction, j);
	}

	public int canCastle(Rook rook){
		Tile[][] tiles = engine.getTiles();

		int direction;
		if (rook.get_i() < this.i) direction = -1;
		else direction = 1;

		if (this.j != rook.get_j())
			return 0;
		if (rook.getHasMoved() || this.getHasMoved())
			return 0;
		for (int s = i + direction; s != rook.get_i(); s+=direction)
			if (tiles[s][j].getPieceIn()) {
				return 0;
			}
		if (tiles[i][j].getThreatened() || tiles[i+direction][j].getThreatened()
				|| tiles[i+2*direction][j].getThreatened())
			return 0;
		return direction;
	}


}