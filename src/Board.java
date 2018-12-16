import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Board extends ImageIcon {

    private int x = 0, y= 0, width, height;
    private Image img;

    public Board () {
        try {
            img = ImageIO.read(getClass().getResource("board.png"));
        } catch (IOException e) {System.out.println("Could not read image!"); }
        setImage(img);
    }

    public int getX () {
        return x;
    }
    public int getY () {
        return y;
    }
    public void setX (int x) {
        this.x = x;
    }
    public void setY (int y) {
        this.y = y;
    }
    public void draw (Graphics g, Canvas canvas) {
        this.paintIcon(canvas, g, x, y);
    }
}
