import javax.swing.*;
import java.awt.*;
/**
 * Buttons panel
 */
public class Buttons extends JPanel {
    private final int numOfButtons; // number of buttons to add (should match the number of columns )
    private final JButton[] cmdAdd; // to store the buttons
    private final JButton cmdClear; // clear button

    public Buttons(int num) {
        JPanel clearPanel = new JPanel(); // clear buttons panel
        JPanel buttonsPanel = new JPanel(); // game buttons
        numOfButtons = num; // setting number of buttons
        cmdAdd = new JButton[numOfButtons];
        cmdClear = new JButton("Clear");
        buttonsPanel.setLayout(new GridLayout(1, numOfButtons));
        for (int i = 0; i < numOfButtons; i++) { // adding buttons to panel
            cmdAdd[i] = new JButton(String.valueOf(i + 1));
            buttonsPanel.add(cmdAdd[i]);
        }
        setLayout(new BorderLayout());
        clearPanel.add(cmdClear);
        add(buttonsPanel, BorderLayout.CENTER);
        add(clearPanel, BorderLayout.SOUTH);

    }

    public void grayOut(int num) { // graying out a button
        cmdAdd[num].setEnabled(false);
    }

    public int getButtonNum(JButton button) { // returns the button number
        for (int i = 0; i < numOfButtons; i++) {
            if (cmdAdd[i] == button) {
                return i;
            }
        }
        return -1; // if button does not exist
    }

    public JButton getClearButton() {
        return cmdClear;
    } // returns the clear button

    public JButton getButton(int num) { // returns the button selected
        if (num >= 1 && num <= numOfButtons)
            return cmdAdd[num - 1];
        return null;
    }

    public int getNumOfButtons() {
        return numOfButtons;
    } // returns the number of buttons

    public void clear() { // setting all buttons to enable
        for (int i = 0; i < numOfButtons; i++) {
            if (!cmdAdd[i].isEnabled())
                cmdAdd[i].setEnabled(true);
        }
        repaint();
    }
}
