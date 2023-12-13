import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * This class create GUI for Player Window
 * and let the players pick the game to play
 * @author Joy
 */
public class PlayerWindow extends JFrame
{

	private JPanel mainPanel;
	private JLabel player1Label;
	private JLabel player2Label;
	private JLabel pickLabel;
	private JButton animalButton;
	private JButton numberButton;
	public static Player player1;
	public static Player player2;
	public static ArrayList<Player> players;

	public PlayerWindow(Player player1, Player player2)
	{
		// Call the superclass constructor and set the window title
		super("Player Window");

		// set JFrame exit on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create the main panel and components
		createPanel(player1, player2);
		
		// maximize the window size
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		//center the frame on the screen
		setLocationRelativeTo(null);

		// Make the window visible
		setVisible(true);

		// Add an ActionListener to the "Number Game" button
		numberButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				//dispose the Player Window
				dispose();
				
				//Initialize player lists for new game window
				List<Player> players = new ArrayList<>();
				players.add(player1);
				players.add(player2);

				// Create and display the new GameWindow
				JFrame numberGameWindow = new NumberGameWindow(players);
				numberGameWindow.setVisible(true);

			}
		});
		// Add an ActionListener to the "Animal Game" button
		animalButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				//dispose the Player Window
				dispose();
				
				//Initialize player lists for new game window
				List<Player> players = new ArrayList<>();
				players.add(player1);
				players.add(player2);

				// Create and display the new GameWindow
				JFrame AnimalGameWindow = new AnimalGameWindow(players);
				AnimalGameWindow.setVisible(true);

			}
		});
	}
	
	/**
	 * Create panel for labels and buttons
	 * @param player1
	 * @param player2
	 */
	private void createPanel(Player player1, Player player2)
	{
		// customFont
		Font customFont = new Font("Serif", Font.PLAIN, 20);

		// Create player1 & player2 Label
		player1Label = new JLabel("Player 1  " + player1.getName());
		player1Label.setFont(customFont);
		player1Label.setForeground(Color.BLUE);
		player1Label.setPreferredSize(new Dimension(200,50));
		player2Label = new JLabel("Player 2  " + player2.getName());
		player2Label.setFont(customFont);
		player2Label.setForeground(Color.BLUE);
		player2Label.setPreferredSize(new Dimension(200,50));

		// Create Ready Label
		pickLabel = new JLabel("Pick the type of cards you want to play!");
		pickLabel.setFont(customFont);
		pickLabel.setPreferredSize(new Dimension(350,50));
		// Create Animal Game Button
		animalButton = new JButton("ANIMAL GAME");
		animalButton.setFont(customFont);
		animalButton.setPreferredSize(new Dimension(200,50));
		// Create Number Game Button
		numberButton = new JButton("NUMBER GAME");
		numberButton.setFont(customFont);
		numberButton.setPreferredSize(new Dimension(200,50));
		// Add labels to panels
		mainPanel = new JPanel(new GridBagLayout());
		
		// Add buttons to the panel with space between rows
		GridBagConstraints gbc = new GridBagConstraints();
		
		// sets the grid x-position, y-position for the component to 0
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		//set 50 pixels of padding at the top
		gbc.insets = new Insets(50,0,0,0);
		mainPanel.add(player1Label, gbc);
		//increments the grid y-position (row) 
		gbc.gridy++;
		mainPanel.add(player2Label,gbc);

		gbc.gridy++;
		mainPanel.add(pickLabel,gbc);
		
		gbc.gridy++;
		mainPanel.add(animalButton,gbc);

		gbc.gridy++;
		mainPanel.add(numberButton,gbc);

		// Create a location for the JFrame
		Container contentPane = getContentPane();

		// Use BorderLayout for the content pane
		contentPane.add(mainPanel,BorderLayout.CENTER);

	}
	/**
	 * Main method calling the GUI
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Example usage: Create an instance of PlayerWindow with a player name

		new PlayerWindow(player1, player2);
		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
	}

}
