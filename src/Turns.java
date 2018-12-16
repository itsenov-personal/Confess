public class Turns{


	private Player turnPlayer;
	private Player white;
	private Player black;

	public Turns(Player white, Player black){
		turnPlayer = white;
		this.white = white;
		this.black = black;
	}

	public void changeTurn(){
		if (turnPlayer == white){
			turnPlayer = black;
		}
		else{
			if (turnPlayer == black)
			turnPlayer = white;
		}
	}

	public Player getTurnPlayer(){
		return turnPlayer;
	}

}