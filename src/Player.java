public class Player {
	private Engine engine;
	private Piece king;
	private PieceList pieces = new PieceList(this);
	private String name;
	private boolean inCheck;

	public Player(String name, Engine engine){
		this.engine = engine;
		this.name = name;
		inCheck = false;
	}

	public boolean isInCheck() {
		return inCheck;
	}
	public void setInCheck(boolean inCheck) {
		this.inCheck = inCheck;
	}
	public Piece getKing() {
		return king;
	}
	public void setKing(King king){
		this.king = king;
	}
	public PieceList getPieces(){return pieces;}
	public String getName() {
		return name;
	}
	public void addPiece(Piece piece){
		pieces.addPiece(piece);
	}
	public void printName(){
		System.out.println("This player is "+name);
	}
}
