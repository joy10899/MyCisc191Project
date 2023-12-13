import java.awt.Dimension;
import javax.swing.JButton;

/**
 * A class create functions for FlipCard
 */
public class FlipCard extends JButton
{
	/**
	 * fields
	 */
	private int cardValue;
	protected String cardValue2;
	private boolean flipped = false;
	private boolean matched = false;

	/**
	 * constructor with integer as a parameter
	 * 
	 * @param cardValue
	 */
	public FlipCard(int cardValue)
	{
		this.cardValue = cardValue;
		setPreferredSize(new Dimension(100, 100));
		setText("");
	}

	/**
	 * constructor with String as a parameter
	 * 
	 * @param cardValue2
	 */
	public FlipCard(String cardValue2)
	{
		this.cardValue2 = cardValue2;
		setPreferredSize(new Dimension(100, 100));
		setText("");
	}

	/**
	 * Method get integer value of a card
	 * 
	 * @return cardValue
	 */
	public int getValue()
	{
		return cardValue;
	}

	/**
	 * Method to get string value of a card
	 * 
	 * @return cardValue2
	 */
	public String getValue2()
	{
		return cardValue2;
	}

	/**
	 * Method check if card is flipped
	 * 
	 * @return flipped
	 */
	public boolean isFlipped()
	{
		return flipped;
	}

	/**
	 * Method check if card is matched
	 * 
	 * @return matched
	 */
	public boolean isMatched()
	{
		return matched;
	}

	/**
	 * Method flip the card to show the value and flip it back
	 */
	public void flip()
	{
		// Toggle the flipped state
		flipped = !flipped;
		// If flipped is true, set the text to the card's value
		if (flipped)
		{
			setText(String.valueOf(cardValue));
		}
		// If flipped is false, set the text to an empty string
		else
		{
			setText("");
			revalidate();
			repaint();
		}
	}

	/**
	 * Method to set matched
	 * 
	 * @param isMatched
	 */
	public void setMatched(boolean isMatched)
	{
		matched = isMatched;
	}

	/**
	 * Method to do reaction when player click on the card
	 */
	public void doReaction()
	{
		System.out.println("do Reation");

	}
}