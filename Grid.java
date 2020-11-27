import javax.swing.*;
import java.awt.*;

/**
 * 4 connect in a row
 * grid panel
 */

public class Grid extends JPanel {
    private final Square[][] squares; // the grid

    public Grid(int rows, int columns) { //creating the grid panel
        squares = new Square[rows][columns];
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[0].length; col++) {
                squares[row][col] = new Square();
                this.add(squares[row][col]);
            }
        }
        setLayout(new GridLayout(rows, columns));
        setBackground(Color.BLUE);
    }

    public Color getColor(int row, int col) { // getting the color of a square
        return squares[row][col].getColor();
    }

    public void setColor(int row, int col, Color color) { // setting color for a square
        squares[row][col].setColor(color);
    }

    public void clear() { // clearing the grid
        for (Square[] square : squares) {
            for (int col = 0; col < squares[0].length; col++) {
                square[col].clear();
            }
        }
    }

    public int getFreePlace(int col) { // getting the next free place in a column
        for (int i = squares.length - 1; i >= 0; i--) {
            if (squares[i][col].isEmpty())
                return i;
        }
        return -1; // no free place
    }

    public boolean isFullColumn(int col) {
        return getFreePlace(col) < 0; // if there is no free space the column is full
    }
}
