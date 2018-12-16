import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Frame extends JFrame implements ComponentListener {

    private Canvas canvas;

    public Frame(Canvas canvas) {
        addComponentListener(this);

        setLocation(50, 50);
        setSize(850, 850);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.canvas = canvas;
        add(canvas);

    }

    public int getWidth () {
        return getSize().width;
    }
    public int getHeight () {
        return getSize().height;
    }

    public void componentResized (ComponentEvent e) {
    }
    public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}

}
