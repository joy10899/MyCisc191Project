import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
    private ArrayList<FlipCard> cards = new ArrayList<>();
    private List<Integer> cardValues = new ArrayList<>();
    private FlipCard firstCard = null;
    private FlipCard secondCard = null;

    public GameWindow() {
        super("Memory Game");
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

        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);

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
                JOptionPane.showMessageDialog(secondCard, "Two cards is match");
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
        new GameWindow();
    }
}