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

public class GameWindow extends JFrame implements Runnable {
    private ArrayList<FlipCard> cards = new ArrayList<>();
    private List<Integer> cardValues = new ArrayList<>();
    private FlipCard firstCard = null;
    private FlipCard secondCard = null;
    private JLabel score1Label;
    private JLabel score2Label;
//    private JLabel name1;
//    private JLabel name2;
//    private JLabel score1;
//    private JLabel score2;
    private int currentPlayerIndex = 0;
    private List<Player> players;

    public GameWindow(List<Player> players) {
        super("Memory Game");
        this.players = players;

        final int WINDOW_WIDTH = 600;
        final int WINDOW_HEIGHT = 400;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 1; i <= 8; i++) {
            cardValues.add(i);
            cardValues.add(i);
        }
        Collections.shuffle(cardValues);

        for (int value : cardValues) {
            FlipCard flipCard = new FlipCard(value);
            cards.add(flipCard);
        }

        JPanel cardPanel = new JPanel(new GridLayout(4, 4));
        for (FlipCard card : cards) {
            cardPanel.add(card);
        }

        // Add score Panel
        JPanel scorePanel = new JPanel(new GridLayout(2,0));
        score1Label = new JLabel(players.get(currentPlayerIndex).toString());
        currentPlayerIndex = 1;
        score2Label = new JLabel(players.get(currentPlayerIndex).toString());
//        name1 = new JLabel(players.get(currentPlayerIndex).getName());
//        score1 = new JLabel(Integer.toString(players.get(currentPlayerIndex).getScore()));
//        currentPlayerIndex = 1;
//        name2 = new JLabel(players.get(currentPlayerIndex).getName());
//        score2 = new JLabel(Integer.toString(players.get(currentPlayerIndex).getScore()));
//        scorePanel.add(name);
        scorePanel.add(score1Label);
        scorePanel.add(score2Label);

        add(cardPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.EAST);
        setVisible(true);

        // Initialize player info
//        updatePlayerInfo();

        // Set up the action listener for the FlipCards
        for (FlipCard card : cards) {
            card.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCardClick(card);
                }
            });
        }
    }

//    public void updatePlayerInfo() {
//    	currentPlayerIndex=0;
//        Player currentPlayer = players.get(currentPlayerIndex);
//        score1Label.setText(currentPlayer.toString());
//        currentPlayerIndex=1;
//        currentPlayer = players.get(currentPlayerIndex);
//        score2Label.setText(currentPlayer.toString());
//        score2Label.setText(Integer.toString(currentPlayer.getScore()));
//    }

    private void handleCardClick(FlipCard clickedCard) {
        // If the card is already flipped, ignore the click
        if (clickedCard.isFlipped()) {
            return;
        }

        if (firstCard == null) {
            // First card is clicked
            firstCard = clickedCard;
            firstCard.flip();
        } else if (secondCard == null) {
            // Second card is clicked
            secondCard = clickedCard;
            secondCard.flip();

            // Check for a match
            if (firstCard.getValue() == secondCard.getValue()) {
                // Cards match
                firstCard.setMatched(true);
                secondCard.setMatched(true);
                JOptionPane.showMessageDialog(secondCard, "Two cards match!");
                players.get(0).incrementScore();
                score1Label.setText(players.get(0).toString());
                
//                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            } else {
                // Cards don't match, flip them back
                firstCard.flip();
                secondCard.flip();
                
            }

            // Reset firstCard and secondCard
            firstCard = null;
            secondCard = null;
        }
    }

    public static void main(String[] args) {
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
