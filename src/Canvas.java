import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// public class Canvas extends JPanel implements ActionListener, KeyListener, MouseListener {
public class Canvas extends JPanel implements MouseListener, KeyListener {

    private Engine engine;

    public Canvas (Engine engine) {
        this.engine = engine;
        setLayout(null);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent (Graphics g) {
        // paints JPanel
        super.paintComponent(g);
        engine.draw(g, this);
    }

    // Keyboard actions
    public void keyPressed (KeyEvent e){
        int c = e.getKeyCode();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        // when various keys are released, different actions occur
        int c = e.getKeyCode();
    }

    // passes in "real" mouse value even when the window is resized or scaled
    public void mousePressed (MouseEvent event) {
        // System.out.println(event.getPoint().x + ", " + event.getPoint().y);
    
    }
    
    public void mouseReleased (MouseEvent event) {}
    public void mouseClicked (MouseEvent event) {}
    public void mouseEntered (MouseEvent event) {}
    public void mouseExited (MouseEvent event) {}

}
