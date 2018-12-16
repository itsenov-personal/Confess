public class Knight extends Piece{

	public Knight(int i, int j, Engine engine, Player player){
		super(i,j, engine, player);
		super.setType("knight");
		super.readImage();
	}

	public void updateMoves() {
		movesReset();
		int[] neighbors = {-1, 1};
		for (int t : neighbors)
			for (int s : neighbors) {
				if (i + t < 8 && j + 2 * s < 8 && i + t >= 0 && j + 2 * s >= 0) {
					moves[i + t][j + 2 * s] = checkMove(i + t, j + 2 * s);
					threatens[i + t][j + 2 * s] = true;
				}
				if (i + 2 * t < 8 && j + s < 8 && i + 2* t > 0 && j + s > 0){
					moves[i + 2 * t][j + s] = checkMove(i + 2*t, j + s);
					threatens[i + 2 * t][j + s] = true;
				}
			}
	}

	public void updateThreatens(){
		threatensReset();
		int[] neighbors = {-1, 1};
		for (int t : neighbors)
			for (int s : neighbors) {
				if (i + t < 8 && j + 2 * s < 8 && i + t >= 0 && j + 2 * s >= 0) {
					threatens[i + t][j + 2 * s] = true;
				}
				if (i + 2 * t < 8 && j + s < 8 && i + 2* t > 0 && j + s > 0){
					threatens[i + 2 * t][j + s] = true;
				}
			}
	}
}