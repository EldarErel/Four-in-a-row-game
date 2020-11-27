import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main panel
 */
public class MainPanel extends JPanel implements ActionListener {
    private final int ROWS = 6; //rows in grid
    private final int COLUMNS = 7; // columns in grid
    private final Color PLAYER1 = Color.RED; // player 1 color
    private final Color PLAYER2 = Color.YELLOW; // player 2 color
    private final Grid grid; // grid panel
    private final Buttons buttons; // buttons panel
    private final JPanel plMsg; // messages panel
    private final JTextArea msg;// show messages to player
    private int numOfMoves = 0; // number of moves
    private Color player = PLAYER1; // current player (first player start)
    private String text; // msg content

    public MainPanel() {
        grid = new Grid(ROWS, COLUMNS); // creating the grid
        buttons = new Buttons(COLUMNS); // creating the buttons
        msg = new JTextArea();
        plMsg = new JPanel();
        text = "Player #" + getPlayerNum() + "'s turn";
        msg.setFont(new Font("font", Font.BOLD, 20));
        msg.setForeground(Color.BLACK);
        setLayout(new BorderLayout());
        plMsg.add(msg);
        add(buttons, BorderLayout.SOUTH);
        add(plMsg, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
        for (int i = 0; i < buttons.getNumOfButtons(); i++) { // adding listener
            buttons.getButton(i + 1).addActionListener(this);
        }
        buttons.getClearButton().addActionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        msg.setText(text);
        msg.setBackground(player);
        plMsg.setBackground(player);

    }

    public void actionPerformed(ActionEvent e) { // button has been pressed
        int buttonNum;
        if (e.getSource() == buttons.getClearButton()) { // clear button pressed
            clear();
            return;
        }
        buttonNum = buttons.getButtonNum((JButton) e.getSource()); //checking which button was pressed
        numOfMoves++; // updating number of moves
        if (buttonNum < 0)  // if button does not exists
            return;
        play(buttonNum);
        if (win(grid)) { // win condition
            text = "Player #" + getPlayerNum() + " Won!";
            playAgain(); // ask users if they want to play again

        } else
            changePlayer(); // changing turn
        if (grid.isFullColumn(buttonNum)) // if the column is full
            buttons.grayOut(buttonNum); // graying out the button
        if (numOfMoves == ROWS * COLUMNS) { // checking if the grid is full
            text = "No more moves, its a draw!";
            player = Color.BLUE; // painting the msg bar background to a different color than the players
            playAgain();
        }
    }
    private void playAgain() { // asking the users if they want to play again
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "End of game", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION)
            clear(); //clearing and starting new game
        else
            System.exit(0);

    }
    private void clear() { // resetting grid and buttons
        grid.clear();
        numOfMoves = 0;
        buttons.clear();
        player = PLAYER1;
        text = "Player #" + getPlayerNum() + "'s turn";
        repaint();
    }

    private boolean win(Grid grid) {  // checking win conditions
        for (int row = 0; row < ROWS; row++) {  //check for 4 in a row
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid.getColor(row, col) == player &&
                        grid.getColor(row, col + 1) == player &&
                        grid.getColor(row, col + 2) == player &&
                        grid.getColor(row, col + 3) == player) {
                    return true;
                }
            }
        }
        for (int row = 0; row < ROWS - 3; row++) { //check for 4 up and down
            for (int col = 0; col < COLUMNS; col++) {
                if (grid.getColor(row, col) == player &&
                        grid.getColor(row + 1, col) == player &&
                        grid.getColor(row + 2, col) == player &&
                        grid.getColor(row + 3, col) == player) {
                    return true;
                }
            }
        }
        for (int row = 3; row < ROWS; row++) { //check upward diagonal
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid.getColor(row, col) == player &&
                        grid.getColor(row - 1, col + 1) == player &&
                        grid.getColor(row - 2, col + 2) == player &&
                        grid.getColor(row - 3, col + 3) == player) {
                    return true;
                }
            }
        }
        for (int row = 0; row < ROWS - 3; row++) { //check downward diagonal
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (grid.getColor(row, col) == player &&
                        grid.getColor(row + 1, col + 1) == player &&
                        grid.getColor(row + 2, col + 2) == player &&
                        grid.getColor(row + 3, col + 3) == player) {
                    return true;
                }
            }
        }
        return false; // no win
    }

    private void play(int col) { // making a move
        int freeSpace = grid.getFreePlace(col); // getting the next free place
        if (freeSpace >= 0) {
            grid.setColor(freeSpace, col, player);
            repaint();
        }
    }

    private void changePlayer() { // changing turns
        if (player == PLAYER1)
            player = PLAYER2;
        else
            player = PLAYER1;
        text = "Player #" + getPlayerNum() + "'s turn";
        repaint();
    }

    private int getPlayerNum() { //returns the player's number
        return player == PLAYER1 ? 1 : 2;
    } // returns the current player number
}


