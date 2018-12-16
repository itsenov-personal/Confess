import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Tile extends JPanel implements MouseListener {

//	Board Image Constants
	private static final int GAMESIZE = 800;
	private static final int SIZE = 80;
	private static final int CORNER = 75;
//	Tile images
	private ImageIcon layer;
	private JLabel label;
	private Image attacked;
	private Image canMove;
	private Image empty;
//	Game components
	private Engine engine;
//	Gameplay field
	private int row, col, x, y, i, j;
	private Piece piece;
	private Boolean pieceIn;
	private Boolean threatened;
	private Piece threateningPiece;
	private Boolean marked;
	private Boolean enPassant;

	public Tile (int i, int j, Engine engine) {

//		Field
		pieceIn = false;
		threatened = false;
		enPassant = false;
		marked = false;
		this.i = i;
		this.j = j;
		row = i+1;
		col = j+1;
		this.engine = engine;
		addMouseListener(this);
//		Graphics
		try {
			readImages();
		} catch(IOException e){e.printStackTrace(); }
		layer = new ImageIcon(empty);
		label = new JLabel(layer);
		this.add(label);
		calculateXY();
        setBounds(x, y, SIZE, SIZE);
		setOpaque(false);
	}

	public void drawMove(){
		if (pieceIn) 
			layer = new ImageIcon(attacked);
		else
			layer = new ImageIcon(canMove);
		label.setIcon(layer);
		marked = true;
	}

	public void undrawMove(){
		layer = new ImageIcon(empty);
		label.setIcon(layer);
		marked = false;
	}

	public void enableEnPassant(){
		this.enPassant = true;
	}

	public Boolean getEnPassant() {
		return enPassant;
	}

	public void resetEnPassant(){
		this.enPassant = false;
	}

	public void setThreatened(Boolean threatened, Piece threateningPiece){
		this.threatened = threatened;
		this.threateningPiece = threateningPiece;
	}

	public Piece getThreateningPiece() {
		return threateningPiece;
	}
	public Boolean getThreatened(){
		return threatened;
	}
    public Boolean getPieceIn(){
    	return pieceIn;
    }
    public Boolean getMarked(){
    	return marked;
    }
	public int get_i(){
		return i;
	}
	public int get_j(){
		return j;
	}

	public void setPiece (Piece piece) {
	 	this.piece = piece;
	 	pieceIn = true;
	}
	public Piece getPiece (){
		return piece;
	}	
	public void removePiece(){
		pieceIn = false;
		piece = null;
	}

	public void readImages() throws IOException{
		attacked = ImageIO.read(getClass().getResource("TileAttacked.png"));
		canMove = ImageIO.read(getClass().getResource("TileMove.png"));
		empty = ImageIO.read(getClass().getResource("TileEmpty.png"));
	}
	public void calculateXY() {
		x = CORNER + (row-1)*(SIZE+2);
		y = (GAMESIZE-CORNER) - (col)*(SIZE+2);
	}
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		layer.paintIcon(this, g, 0, 0);
	}

	public void mousePressed (MouseEvent event) {
		engine.tilePressed(this);
	}
	public void mouseReleased (MouseEvent event) {}
    public void mouseClicked (MouseEvent event) {}
    public void mouseEntered (MouseEvent event) {}
    public void mouseExited (MouseEvent event) {}

}