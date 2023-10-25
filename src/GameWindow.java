import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.SwingUtilities;

public class GameWindow extends JFrame {
	//This list is used to store all flip card that will be displayed on the cards.
	 private ArrayList<FlipCard> Cards = new ArrayList<>();
	// This list is used to store the numeric values that will be displayed on the cards
	 private List<Integer> cardValues = new ArrayList<>();
	 private FlipCard firstCard;
	 private FlipCard card;
	 
	 public GameWindow() {

	        // Calls the superclass constructor with the title "Memory Game".
	        super("Memory Game");

	        // the size of the window
	        final int WINDOW_WIDTH = 600;
	        final int WINDOW_HEIGHT = 400;

	        // set the size
	        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	        // set the program to end when the window is closed
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Create a list of 1 to 8 numbers for 8 pairs
	        for (int i = 1; i <= 8; i++) {
	            cardValues.add(i);
	            cardValues.add(i); 
	     
	        }
	        Collections.shuffle(cardValues);

	        // Create the flip cards 
	        for (int value : cardValues) {
	            FlipCard flipCard = new FlipCard(value);
	            // Adding flip card to the flipCards list
	            Cards.add(flipCard);
	        }

	        // Create a JPanel to hold the cards and arrange them in a grid
	        JPanel cardPanel = new JPanel(new GridLayout(4, 4)); // 4 rows and 4 columns for 16 cards
	        for (FlipCard card : Cards) {
	            cardPanel.add(card);
	        }

	        // Add the cardPanel to the center of the frame
	        add(cardPanel, BorderLayout.CENTER);

	        // display the window
	        setVisible(true);
	        
	        //ActionListener class
	        class  cardClickListener implements ActionListener {
	        	private FlipCard card;
	        	private FlipCard firstCard = null;
	        	private ArrayList<FlipCard> cards;
	        	
	        	public cardClickListener(FlipCard card, ArrayList<FlipCard> cards)
	        	{
	        		this.card = card;
	        		this.cards = cards;
	        	}

	        	@Override
	        	public void actionPerformed(ActionEvent e)
	        		{
	        			if( firstCard == null)
	        			{
	        				firstCard = card;
	        			}
	        			else if (firstCard != card)
	        			{
	        				if (firstCard.getValue() == card.getValue())
	        					{
	        					JOptionPane.showMessageDialog(GameWindow.this,"Two cards is match");
	        					firstCard.setEnabled(false);
	        					card.setEnabled(false);
	        					firstCard = null;
	        					}
	        				else {
	        					firstCard = null;
	        				}
	                        
	                    }
	        	
	        	
	        	}
	        	}
	        	ActionListener cardClickListener = new cardClickListener(card, Cards);
	        	for (FlipCard card : Cards) {
	                card.addActionListener(cardClickListener);
	            }
	        	
	        	}
	 
	        
	      


	 public static void main(String[] args) {
	   //     SwingUtilities.invokeLater(() -> {
	            new GameWindow();
	      //  });
	    }
	}