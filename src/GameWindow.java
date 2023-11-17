import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameWindow extends JFrame implements Runnable
{
	private ArrayList<FlipCard> cards = new ArrayList<>();
	private List<Integer> cardValues = new ArrayList<>();
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

	public GameWindow(List<Player> players)
	{
		super("Memory Game");
		this.players = players;

		final int WINDOW_WIDTH = 600;
		final int WINDOW_HEIGHT = 400;
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 1; i <= 8; i++)
		{
			cardValues.add(i);
			cardValues.add(i);
		}
		Collections.shuffle(cardValues);

		for (int value : cardValues)
		{
			FlipCard flipCard = new FlipCard(value);
			cards.add(flipCard);
		}

		JPanel cardPanel = new JPanel(new GridLayout(4, 4));
		for (FlipCard card : cards)
		{
			cardPanel.add(card);
		}

		// Add score Panel
		JPanel scorePanel = new JPanel(new GridLayout(3, 0));
		score1Label = new JLabel(players.get(currentPlayerIndex).toString());
		currentPlayerIndex = 1;
		score2Label = new JLabel(players.get(currentPlayerIndex).toString());
		// Add timer label
		timerLabel = new JLabel("Time: " + secondsRemaining + "s");
		scorePanel.add(timerLabel);
		scorePanel.add(score1Label);
		scorePanel.add(score2Label);

		add(cardPanel, BorderLayout.CENTER);
		add(scorePanel, BorderLayout.EAST);
		setVisible(true);

		// reset index to the first player
		currentPlayerIndex = 0;

		// Set up the action listener for the FlipCards
		for (FlipCard card : cards)
		{
			card.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					handleCardClick(card);
				}
			});
		}
		Thread gameThread = new Thread(this);
		gameThread.start();

	}

	private void handleCardClick(FlipCard clickedCard)
	{
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
			if (firstCard.getValue() == secondCard.getValue())
			{
				// Cards match
				firstCard.setMatched(true);
				secondCard.setMatched(true);
				JOptionPane.showMessageDialog(rootPane, "Two cards match!");
				players.get(currentPlayerIndex).incrementScore();
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

	//
	// let the window open for 10 seconds
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(15000);
			// tell player to switch
			JOptionPane.showMessageDialog(firstCard, "Switch Player");
			// reset all cards
			for (FlipCard card : cards)
			{
				card.setText("");
			}
			// update player for scoring
			currentPlayerIndex = 1;
			for (FlipCard card1 : cards)
			{
				card1.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						handleCardClick(card1);
					}
				});
			}
			// add time for second player
			Thread.sleep(15000);
			// end game
			JOptionPane.showMessageDialog(this, "Game over");
			dispose();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		// Example usage: Create an instance of PlayerWindow with a player name
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");

		List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);

		PlayerWindow playerWindow = new PlayerWindow(player1, player2);
		playerWindow.setVisible(true);

		// After the players are added, create the GameWindow
		new GameWindow(players);
	}

}
