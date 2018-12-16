public class Pawn extends Piece {

	private int UP;
	
	public Pawn(int i, int j, Engine engine, Player player){
		super(i,j, engine, player);
		super.setType("pawn");
		super.readImage();
		if (player == engine.getWhite())
			UP = 1;
		if (player == engine.getBlack())
			UP = -1;
		hasMoved = false;
	}

	public void updateMoves(){
		movesReset();
		Tile[][] tiles = engine.getTiles();

		if (!tiles[i][j + UP].getPieceIn())
			moves[i][j + UP] = checkMove(i, j + UP);
		if (i + 1 < 8 && j + UP < 8 && j + UP > 0) {
			if (tiles[i + 1][j + UP].getPieceIn()) {
				moves[i + 1][j + UP] = checkMove(i + 1, j + UP);
			}
		}
		if (i - 1 > 0 && j + UP < 8 && j + UP > 0) {
			if (tiles[i - 1][j + UP].getPieceIn()) {
				moves[i - 1][j + UP] = checkMove(i - 1, j + UP);
			}
		}
		if (!hasMoved && j + 2 * UP > 0 && j + 2*UP < 8)
			if (!tiles[i][j + 2 * UP].getPieceIn() &&
					!tiles[i][j + UP].getPieceIn())
				moves[i][j + 2 * UP] = checkMove(i, j + 2 * UP);
	}

	public void updateThreatens(){
		threatensReset();
		if (i + 1 < 8 && j + UP < 8 && j + UP > 0) {
			threatens[i + 1][j + UP] = true;
		}
		if (i - 1 > 0 && j + UP < 8 && j + UP > 0) {
			threatens[i - 1][j + UP] = true;
		}
	}
}