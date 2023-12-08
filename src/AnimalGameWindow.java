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
		super("Memory Game");
		this.players = players;

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (String animal : Arrays.asList("monkey", "elephant", "sloth", "duck","dog")) {
		    animalCards.add(new AnimalCard(animal));
		}
		
		for (String animal : Arrays.asList("monkey", "elephant", "sloth", "duck","dog")) {
		    animalCards.add(new AnimalCard(animal));
		}
		
		Collections.shuffle(animalCards);
		
		JPanel cardPanel = new JPanel(new GridLayout(2,5));
		for (FlipCard card : animalCards)
		{
			card.setBackground(Color.cyan);
			cardPanel.add(card);
		}
		
		// Add score Panel
		JPanel scorePanel = new JPanel(new FlowLayout());
		score1Label = new JLabel(players.get(currentPlayerIndex).toString());
		currentPlayerIndex = 1;
		score2Label = new JLabel(players.get(currentPlayerIndex).toString());
		//Add Score label
		scorePanel.add(score1Label);
		scorePanel.add(score2Label);
		//Add JLabel to the panel
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(cardPanel);
		panel.add(scorePanel, BorderLayout.NORTH);
		add(panel);
		setVisible(true);
		// reset index to the first player
		currentPlayerIndex = 0;

		// Set up the action listener for the FlipCards
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
					card.flip();
//					card.doReaction();
					handleAnimalCardClick(card);
					if (allHaveReactions() == true) {
						announceWinner();
					}
				}
			});
		}

	}
	
	
	private void handleAnimalCardClick(AnimalCard clickedCard) {

				if (firstCard == null)
				{
					// First card is clicked
					firstCard = clickedCard;
				}
				else if (secondCard == null)
				{
					// Second card is clicked
					secondCard = clickedCard;
					// Check for a match
					if (firstCard.getValue2() == secondCard.getValue2())
					{
						// Cards match
						firstCard.setMatched(true);
						secondCard.setMatched(true);
						JOptionPane.showMessageDialog(rootPane, "Two cards match!");
						players.get(currentPlayerIndex).incrementScore();
						firstCard.doReaction();
						secondCard.doReaction();
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
					else
					{
						JOptionPane.showMessageDialog(this, "Switch Player");
						currentPlayerIndex = (currentPlayerIndex + 1)%2;
						// Cards don't match, flip them back
						firstCard.flip();
						secondCard.flip();
					}

					// Reset firstCard and secondCard
					firstCard = null;
					secondCard = null;

				}
	}
	
	public boolean allHaveReactions() {
	for (AnimalCard card : animalCards) {
		if (!card.hasReaction()) {
			return false;
		}
	}
	return true;
	}
	
	public void announceWinner()
	{
		JFrame f = new JFrame();
		int firstScore = players.get(currentPlayerIndex).getScore();
		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
		int secondScore = players.get(currentPlayerIndex).getScore();
		JLabel winnerLabel = new JLabel();
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
		winnerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		winnerLabel.setPreferredSize(new Dimension(800,600));
		winnerLabel.setForeground(Color.gray);
		winnerLabel.setOpaque(true);
		winnerLabel.setBackground(Color.pink);
		f.setSize(new Dimension(800,600));
		f.setLayout(new FlowLayout(FlowLayout.CENTER));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(winnerLabel);
		f.setVisible(true);	
	}

}
