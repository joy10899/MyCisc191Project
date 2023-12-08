import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

public class AnimalGameWindow extends JFrame implements Runnable
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
	private JLabel timerLabel;
	private Timer timer;
	private int secondsRemaining = 15;
	
	public AnimalGameWindow(List<Player> players)
	{
		super("Memory Game");
		this.players = players;

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (String animal : Arrays.asList("monkey", "elephant", "sloth", "duck")) {
		    animalCards.add(new AnimalCard(animal));
		}
		
		for (String animal : Arrays.asList("monkey", "elephant", "sloth", "duck")) {
		    animalCards.add(new AnimalCard(animal));
		}
		
		Collections.shuffle(animalCards);
		
		JPanel cardPanel = new JPanel(new GridLayout(2,4));
		for (FlipCard card : animalCards)
		{
			card.setBackground(Color.cyan);
			cardPanel.add(card);
		}
		
		// Add score Panel
		JPanel scorePanel = new JPanel(new FlowLayout());
//		JPanel scorePanel = new JPanel(new BorderLayout());
		score1Label = new JLabel(players.get(currentPlayerIndex).toString());
		currentPlayerIndex = 1;
		score2Label = new JLabel(players.get(currentPlayerIndex).toString());
		// Add timer label
		timerLabel = new JLabel("Time: " + secondsRemaining + "s");
		timerLabel.setForeground(Color.red);
		scorePanel.add(timerLabel);
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
					handleAnimalCardClick(card);
//					card.doReaction();
				}
			});
		}
		Thread gameThread = new Thread(this);
		gameThread.start();

	}
	
	
	private void handleAnimalCardClick(AnimalCard clickedCard) {
		// If the card is already flipped, ignore the click
				if (clickedCard.isFlipped())
				{
					return;
				}

				if (firstCard == null)
				{
					// First card is clicked
					firstCard = clickedCard;
					firstCard.flip();
					System.out.println("First card selected");
				}
				else if (secondCard == null)
				{
					// Second card is clicked
					secondCard = clickedCard;
					secondCard.flip();
					System.out.println("Second card selected");

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
						// Cards don't match, flip them back
						firstCard.flip();
						secondCard.flip();
						System.out.println("Flipping Card back over");
					}

					// Reset firstCard and secondCard
					firstCard = null;
					secondCard = null;

				}
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(15000);
			// tell player to switch
			JOptionPane.showMessageDialog(this, "Switch Player");
			// reset all cards for the next player to play
			for (AnimalCard card : animalCards)
			{
				card.setText("");
			}
			// update player index for scoring the next player
			currentPlayerIndex = 1;
			// Set up the action listener for the new set of FlipCards
			for (AnimalCard card1 : animalCards)
			{
				card1.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						handleAnimalCardClick(card1);
					}
				});
			}
			//add time to play for second player
			Thread.sleep(15000);
			//end game
			JOptionPane.showMessageDialog(this, "Game over");
			dispose();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}	

}
