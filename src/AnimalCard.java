import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 * A class create functions for Animal Card
 */
public class AnimalCard extends FlipCard
{
	private String animalvalue;
	private JFrame f;
	private ImageIcon img;
	private JLabel l;
	private boolean flipped = false;
	private boolean hasReaction = false;
	
	/**
	 * Constructor
	 * @param cardValue2
	 */
	public AnimalCard(String cardValue2)
	{
		super(cardValue2);
		setPreferredSize(new Dimension(100, 100));
		setText("");
	}

	/**
	 * Implement flip method to show the string 
	 */
	@Override
	public void flip()
	{
		flipped = !flipped;
		if (flipped)
		{
			setText(String.valueOf(cardValue2));
		}
		else
		{
			setText("");
		}
	}

	/**
	 * Method to get value
	 * @return cardValue2
	 */
	public String getValue2()
	{
		return cardValue2;
	}

	/**
	 * Method to do animated icon reaction 
	 */
	@Override
	public void doReaction()
	{
		if (flipped)
		{
			img = new ImageIcon("worm.gif");
			setIcon(img);
			hasReaction = true;
		}
	}
	
	public boolean hasReaction() {
		return hasReaction;
	}

}
