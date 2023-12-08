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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NumberGameWindow extends JFrame 
{
	private ArrayList<NumberCard> numberCards = new ArrayList<>();
	private List<Integer> number = new ArrayList<>();
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

	public NumberGameWindow(List<Player> players)
	{
		super("Memory Game");
		this.players = players;

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//add numbers for ArrayList NumberCard
		for (int i = 1; i <= 4; i++)
		{
			numberCards.add(new NumberCard(i));
			numberCards.add(new NumberCard(i));
		}
		
		Collections.shuffle(numberCards);

		JPanel cardPanel = new JPanel(new GridLayout(2,4));
		for (NumberCard card : numberCards)
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
		for (NumberCard card : numberCards)
		{
			card.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (card.isFlipped())
					{
						return;
					}
					card.flip();
					card.doReaction();
					handleNumberCardClick(card);
//					card.doReaction();
				}
			});
		}
//		Thread gameThread = new Thread(this);
//		gameThread.start();
	}
	
	
	private void handleNumberCardClick(NumberCard clickedCard)
	{
		// If the card is already flipped, ignore the click
	
//		clickedCard.flip();
		if (firstCard == null)
		{
			// First card is clicked
			firstCard = clickedCard;
//			firstCard.flip();
//			firstCard.revalidate();
//			clickedCard.doReaction();
			System.out.println("First card selected");
		}
		else if (secondCard == null)
		{
			// Second card is clicked
			secondCard = clickedCard;
//			secondCard.flip();
//			clickedCard.doReaction();
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
//				try
//				{
//					Thread.sleep(15000);
//				}
//				catch (InterruptedException e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				JOptionPane.showMessageDialog(this, "Switch Player");
				currentPlayerIndex = (currentPlayerIndex+1)%2;
				firstCard.flip();
				secondCard.flip();
				System.out.println("Flipping Card back over");
			}

			// Reset firstCard and secondCard
			firstCard = null;
			secondCard = null;
//			switchPlayer();
		}
	}
	
	public void announceWinner()
	{
		JFrame f = new JFrame();
		int firstScore = players.get(currentPlayerIndex).getScore();
		currentPlayerIndex = (currentPlayerIndex + 1) % 2;
		int secondScore = players.get(currentPlayerIndex).getScore();
		JLabel winnerLabel = new JLabel();
		winnerLabel.setBackground(Color.PINK);
		winnerLabel.setForeground(Color.green);
		winnerLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		winnerLabel.setPreferredSize(new Dimension(600,400));
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
		f.add(winnerLabel);
		f.setVisible(true);
	}


//	@Override
//	public void run()
//	public void switchPlayer(){
//	
//		try
//		{
//			Thread.sleep(15000);
//			// tell player to switch
//			
//			// reset all cards for the next player to play
//			for (NumberCard card : numberCards)
//			{
//				card.setText("");
//			}
//			// update player index for scoring the next player
////			currentPlayerIndex = 1;
//			// Set up the action listener for the new set of FlipCards
//			for (NumberCard card1 : numberCards)
//			{
//				card1.addActionListener(new ActionListener()
//				{
//					@Override
//					public void actionPerformed(ActionEvent e)
//					{
//						handleNumberCardClick(card1);
//					}
//				});
//			}
//			//add time to play for second player
////			Thread.sleep(15000);x
//			//end game
//			JOptionPane.showMessageDialog(this, "Game over");
//			dispose();
//		}
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
//	}

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
		new NumberGameWindow(players);
	}

}
