import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

class FlipCard extends JButton {
    private int cardValue;
    private boolean flipped = false;

    public FlipCard(int cardValue) {
        this.cardValue = cardValue;
        // Adjust the size as needed
        setPreferredSize(new Dimension(100, 100)); 
        // Initially, the card has no text
        setText(""); // Initially, the card has no text

        // Add a click listener to flip the card
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle the card's state (front/back)
                flipped = !flipped;
                if (flipped) {
                    setText(String.valueOf(cardValue)); // Display the card's value when flipped
                } else {
                    setText(""); // Display no text when not flipped
                }
                repaint();
            }
        });
    }
    
    public int getValue()
    {
    	return cardValue;
    }
    public void flip()
    {
    	flipped = !flipped;
        if (flipped) {
            setText(String.valueOf(cardValue)); // Display the card's value when flipped
        } else {
            setText(""); // Display no text when not flipped
        }
        repaint();
    }
    
   
	public boolean isMatch(FlipCard card1, FlipCard card2)
	{
		if (card1.cardValue == card2.cardValue)
		{
			return true;
//		JOptionPane.showMessageDialog(FlipCard.this,"Two cards is match");
		}
		else {
            setText(""); // Display no text when not flipped
            return false;
        }
	}
}