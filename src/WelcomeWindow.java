import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
/**
 * This class create GUI for welcome window that allow 
 * players to enter their name
 */
public class WelcomeWindow extends JFrame {

    private JPanel mainPanel;
    private JLabel PlayerName;
    private JLabel Welcome;
    private JTextField PlayerNameTextField;
    private int currentPlayer = 1;
    private JButton EnterButton;
    private String playerName;
    private Player player1;
    private Player player2;

    public WelcomeWindow() {

        // call superclass constructor and title the window
        super("Welcome");

        // a few constants for the size of the window
        final int WINDOW_WIDTH = 600;
        final int WINDOW_HEIGHT = 400;

        // set the size
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // set the program to end when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make a JPanel and add it to the frame
        createPanel();

        // display the window
        setVisible(true);
        
        //add listener to the Enter button
        EnterButton.addActionListener(new ActionListener() {
          
			public void actionPerformed(ActionEvent e) {
                try {
                    // Store the first player's name from the text field
                    playerName = PlayerNameTextField.getText();
                    
                    // Check if the playerName contains any number
                    if (ContainsNumber(playerName)) {
                        throw new ValidName("Player name must not have numbers.");
                    }
                    //enter name of the second player
                    if (currentPlayer ==1)
                    {
                    	player1 = new Player(playerName);
                    	currentPlayer = 2;
                        PlayerName.setText("Player " + currentPlayer + " Name");
                        PlayerNameTextField.setText("");
                    }
                    //dispose the current window and explore the player window
                    else if (currentPlayer == 2)
                    {
                    	player2 = new Player(playerName);
                    	dispose();
                    	JFrame playerWindow = new PlayerWindow(player1, player2);
                        playerWindow.setVisible(true);
                    }
                    
                } catch (ValidName ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Name Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    // Helper method to check if a string contains at least one digit
    private boolean ContainsNumber(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

                
    // create Panel
    private void createPanel() {
        mainPanel = new JPanel();
        //create label welcome
        Welcome = new JLabel("Welcome to Memory Game");
        //create label player name and set size
        PlayerName = new JLabel("Player " + currentPlayer + " Name");
        Font customFont = new Font("Serif", Font.PLAIN, 20);
        PlayerName.setFont(customFont);
        Welcome.setFont(customFont);        
        //create JTextFiels to enter name
        PlayerNameTextField = new JTextField("");
        PlayerNameTextField.setFont(customFont);
        PlayerNameTextField.setPreferredSize(new Dimension(200, 50));
        //create Enter button
        EnterButton = new JButton("Enter");
        EnterButton.setFont(customFont);
        //add buttons and labels to mainPanel
        mainPanel.add(Welcome);
        mainPanel.add(PlayerName);
        mainPanel.add(PlayerNameTextField);
        mainPanel.add(EnterButton);
        
     // Create a content pane for the JFrame
        Container contentPane = getContentPane();
        
        // Use a FlowLayout with center alignment for the content pane
        contentPane.setLayout(new GridLayout(0,3));
        
        // Add the mainPanel to the content pane
        contentPane.add(mainPanel);

        // Add the mainPanel to the JFrame
        add(mainPanel);
    }

    public static void main(String[] args) {
        // Create an instance of GameWindow
        new WelcomeWindow();
    }
}
