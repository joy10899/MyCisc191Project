import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * This class is used to try out the way to check flipped cards of the array 
 * and see if cards are match
 */
public class Controller
{
	private ArrayList<FlipCard> cards;
	private List<Integer> cardValues;
	private FlipCard firstCard;
	private FlipCard secondCard;
	private static int flipCount = 0;
	
	public Controller(ArrayList<FlipCard> cards,List<Integer> cardValues)
	{
		this.cards = cards;
		this.cardValues = cardValues;
	}
	
	public int flipCount()
	{
		try
		{
			for (FlipCard card : cards) {
				if(card.isFlip())
				flipCount++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return flipCount;
	}
	
	public void check(FlipCard firstCard, FlipCard secondCard)
	{
		 if( flipCount < 3)
			{    
			 for (FlipCard card : cards) {
				 if(card.getFlipId()==1) {
					 firstCard = card;
				 }
				 else if(card.getFlipId()==2){
					 secondCard = card;
				 }
					
			}
			}
		 if (firstCard.getValue() == secondCard.getValue())
			{
			JOptionPane.showMessageDialog(secondCard, "Two cards is match");
			
	}
	}

}
