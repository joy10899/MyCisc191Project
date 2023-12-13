import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
 * This class create GUI for welcome window that allow
 * players to see the instruction enter their name
 * 
 * @author Joy
 */
public class WelcomeWindow extends JFrame
{

	private JPanel mainPanel;
	private JPanel PanelCenter;
	private JLabel PlayerName;
	private JLabel Welcome;
	private JLabel instructions;
	private JTextArea textArea;
	private JTextField PlayerNameTextField;
	private int currentPlayer = 1;
	private JButton EnterButton;
	private String playerName;
	private Player player1;
	private Player player2;

	public WelcomeWindow()
	{

		// call superclass constructor and title the window
		super("Welcome");

		// maximize the window size
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// set the program to end when the window is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Make a JPanel and add it to the frame
		try
		{
			createPanel();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// display the window
		setVisible(true);

		// add listener to the Enter button
		EnterButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				try
				{
					// Store the first player's name from the text field
					playerName = PlayerNameTextField.getText();

					// Check if the playerName contains any number
					if (ContainsNumber(playerName))
					{
						throw new ValidName(
								"Player name must not have numbers.");
					}
					// enter name of the second player
					if (currentPlayer == 1)
					{
						player1 = new Player(playerName);
						currentPlayer = 2;
						PlayerName.setText("Player " + currentPlayer + " Name");
						PlayerNameTextField.setText("");
					}
					// dispose the current window and explore the player window
					else if (currentPlayer == 2)
					{
						player2 = new Player(playerName);
						dispose();
						JFrame playerWindow = new PlayerWindow(player1,
								player2);
						playerWindow.setVisible(true);
					}

				}
				catch (ValidName ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage(),
							"Name Validation Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	//
	/**
	 * Helper method to check if a string contains any digit
	 * 
	 * @param s
	 * @return true/false
	 */
	private boolean ContainsNumber(String s)
	{
		for (char c : s.toCharArray())
		{
			if (Character.isDigit(c))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * create Panel
	 * 
	 * @throws IOException
	 */
	private void createPanel() throws IOException
	{
		mainPanel = new JPanel();

		// create label welcome
		Welcome = new JLabel("WELCOME TO MEMORY GAME");

		// create label player name and set font
		PlayerName = new JLabel("Player " + currentPlayer + " Name");
		Font customFont = new Font("Serif", Font.PLAIN, 20);
		PlayerName.setFont(customFont);

		// set font for Welcome
		Welcome.setFont(customFont);
		Welcome.setForeground(Color.red);

		// create JTextFiels to enter name
		PlayerNameTextField = new JTextField("");
		PlayerNameTextField.setFont(customFont);
		PlayerNameTextField.setPreferredSize(new Dimension(200, 50));

		// create Enter button
		EnterButton = new JButton("Enter");
		EnterButton.setFont(customFont);

		// add buttons and labels to mainPanel
		mainPanel.add(Welcome);
		mainPanel.add(PlayerName);
		mainPanel.add(PlayerNameTextField);
		mainPanel.add(EnterButton);

		// Create PanelCenter for instruction Label and TextArea
		PanelCenter = new JPanel();
		instructions = new JLabel("Instructions");
		instructions.setFont(customFont);
		textArea = new JTextArea("", 100, 100);
		textArea.setFont(customFont);

		// set layout and add Label, TextArea
		PanelCenter.setLayout(new BorderLayout());
		PanelCenter.add(instructions, BorderLayout.NORTH);
		PanelCenter.add(textArea, BorderLayout.CENTER);

		// Print instruction in TextArea
		Scanner scanner = null;
		try
		{
			// Obtain and store information of File name
			File inFile = new File("gameInstruction.txt");
			// create scanner object connect to the input file
			scanner = new Scanner(inFile);
			StringBuffer str = new StringBuffer("");
			// check if input File has next line
			while (scanner.hasNextLine())
			{
				// store the current line to String line
				String line = scanner.nextLine();
				// append line to this character sequence
				str.append(line + System.lineSeparator());
			}
			// set text to str
			textArea.setText(str.toString());
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			scanner.close();
		}

		// Create a content pane for the JFrame
		Container contentPane = getContentPane();

		// Use a FlowLayout with center alignment for the content pane
		contentPane.setLayout(new BorderLayout());

		// Add the mainPanel to the content pane
		contentPane.add(mainPanel, BorderLayout.NORTH);
		contentPane.add(PanelCenter, BorderLayout.CENTER);
	}

	public static void main(String[] args)
	{
		// Create an instance of GameWindow
		new WelcomeWindow();
	}
}
