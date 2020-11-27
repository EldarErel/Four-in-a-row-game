import javax.swing.*;

/**
 * connect four in a row game
 *
 * @author - Eldar Erel
 */
public class Run {
    public static void main(String[] args) {
        JOptionPane.showOptionDialog(null, "Four in a row game\n Enjoy!", "Welcome", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Let's play"}, "Let's play");
        JFrame frame = new JFrame("Four in a row");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
