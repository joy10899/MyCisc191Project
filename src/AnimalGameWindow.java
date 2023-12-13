import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class create GUI for Animal Game Window and allow player to play
 */
public class AnimalGameWindow extends JFrame
{
	private ArrayList<AnimalCard> animalCards = new ArrayList<>();
	private List<String> animal = new ArrayList<>();
	private FlipCard firstCard = null;
	private FlipCard secondCard = null;
	private JLabel score1Label;
	private JLabel score2Label;
	private int currentPlayerIndex = 0;
	private List<Player> players;
	public static Player player1;
	public static Player player2;

	public AnimalGameWindow(List<Player> players)
	{
		// call the superclass constructor and title the window
		super("Memory Game");

		// passing variable value to constructor value
		this.players = players;

		// maximize the window size
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// set the program to end when the window is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add animals from ArrayList to Animal Cards
		for (String animal : Arrays.asList("monkey", "elephant", "sloth",
				"duck", "dog"))
		{
			animalCards.add(new AnimalCard(animal));
		}

		for (String animal : Arrays.asList("monkey", "elephant", "sloth",
				"duck", "dog"))
		{
			animalCards.add(new AnimalCard(animal));
		}

		// shuffle the cards
		Collections.shuffle(animalCards);

		// create cardPanel and add layout
		JPanel cardPanel = new JPanel(new GridLayout(2, 5));
		for (FlipCard card : animalCards)
		{
			card.setBackground(Color.cyan);
			cardPanel.add(card);
		}

		// Add score Panel
		JPanel scorePanel = new JPanel(new FlowLayout());

		// Add score label for the first player
		score1Label = new JLabel(players.get(currentPlayerIndex).toString());

		// Add score label for the second player
		currentPlayerIndex = 1;
		score2Label = new JLabel(players.get(currentPlayerIndex).toString());

		// Add Score label
		scorePanel.add(score1Label);
		scorePanel.add(score2Label);

		// Add JLabel to the panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(cardPanel);
		panel.add(scorePanel, BorderLayout.NORTH);
		add(panel);
		setVisible(true);

		// reset index to the first player
		currentPlayerIndex = 0;

		// Set up the action listener for the AnimalCards
		for (AnimalCard card : animalCards)
		{
			card.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// If the card is already flipped, ignore the click
					if (card.isFlipped())
					{
						return;
					}

					// flip card
					card.flip();

					// handle card click
					handleAnimalCardClick(card);

					// if all card are flipped, announce the winner
					if (allHaveReactions() == true)
					{
						announceWinner();
					}
				}
			});
		}

	}

	/**
	 * Method handle card click
	 * 
	 * @param clickedCard
	 */
	public void handleAnimalCardClick(AnimalCard clickedCard)
	{
		// assign first clickedCard to firstCard
		if (firstCard == null)
		{
			firstCard = clickedCard;
		}
		
		// assign second clickedCard to secondCard 
		else if (secondCard == null)
		{
			secondCard = clickedCard;
			// Check for a match
			if (firstCard.getValue2() == secondCard.getValue2())
			{
				// If Cards match, display JOptionPane and increase player score
				firstCard.setMatched(true);
				secondCard.setMatched(true);
				JOptionPane.showMessageDialog(rootPane, "Two cards match!");
				players.get(currentPlayerIndex).incrementScore();
				//Do animated Reaction for matched cards
				firstCard.doReaction();
				secondCard.doReaction();
				// Check index of player and set label to incrementScore
				if (currentPlayerIndex == 0)
				{
					score1Label.setText(
							players.get(currentPlayerIndex).toString());
				}
				else if (currentPlayerIndex == 1)
				{
					score2Label.setText(
							players.get(currentPlayerIndex).toString());
				}
			}
			// If the cards don't match, switch player
			else
			{
				JOptionPane.showMessageDialog(this, "Switch Player");
				//switch player index
				currentPlayerIndex = (currentPlayerIndex + 1) % 2;
				// Cards don't match, flip them back
				firstCard.flip();
				secondCard.flip();
			}

			// Reset firstCard and secondCard
			firstCard = null;
			secondCard = null;

		}
	}

	/**
	 * Method to check if all cards have reaction
	 * @return true/false
	 */
	public boolean allHaveReactions()
	{
		for (AnimalCard card : animalCards)
		{
			if (!card.hasReaction())
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to create a GUI to announce the winner
	 */
	public void announceWinner()
	{
		JFrame f = new JFrame();
		//get score of first player
		int firstScore = players.get(currentPlayerIndex).getScore();
		//get score of second player
		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
		int secondScore = players.get(currentPlayerIndex).getScore();
		JLabel winnerLabel = new JLabel();
		//compare score and set label for player has higher score
		if (secondScore > firstScore)
		{
			winnerLabel = new JLabel(
					" CONGRATS, " + players.get(currentPlayerIndex).getName()
							+ " IS THE WINNER");
		}
		else
		{
			currentPlayerIndex = (currentPlayerIndex + 1) % 2;
			winnerLabel = new JLabel(
					" CONGRATS, " + players.get(currentPlayerIndex).getName()
							+ " IS THE WINNER");
		}
		//set font and color for winnerLabel
		winnerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		winnerLabel.setPreferredSize(new Dimension(800, 600));
		winnerLabel.setForeground(Color.gray);
		//set label Opaque for background color
		winnerLabel.setOpaque(true);
		winnerLabel.setBackground(Color.pink);
		winnerLabel.setHorizontalAlignment(JLabel.CENTER);
		f.setSize(new Dimension(800, 600));
		f.setLayout(new FlowLayout(FlowLayout.CENTER));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(winnerLabel);
		f.setVisible(true);
	}

}
