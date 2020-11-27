import javax.swing.*;
import java.awt.*;

/**
 * Grid square panel
 */
public class Square extends JPanel {
    private final Color DEFAULT_COLOR = Color.WHITE; // default color is white
    private Color color; // to store the player's color

    public Square() {
        color = DEFAULT_COLOR; // default color
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(color); // getting the color
        g.fillOval(5, 5, getWidth()-10 , getHeight() -10 ); // drawing disc

    }

    public Color getColor() { //returns the color of the square
        return color;
    } // returns the disc color

    public void setColor(Color color) { // setting the color
        this.color = color;
    } // sets the disc color

    public void clear() { //setting default color
        color = Color.white;
        repaint();
    }

    public boolean isEmpty() { // returns true if the square has the default color
        return this.color == DEFAULT_COLOR;
    } // returns true if color is set to default
}