import java.awt.Dimension;

import javax.swing.JButton;

class FlipCard extends JButton {
    private int cardValue;
    protected String cardValue2;
    private boolean flipped = false;
    private boolean matched = false;

    public FlipCard(int cardValue) {
        this.cardValue = cardValue;
        setPreferredSize(new Dimension(100, 100));
        setText("");
    }
    
    public FlipCard(String cardValue2)
  	{
  		this.cardValue2 = cardValue2;
  		setPreferredSize(new Dimension(100, 100));
          setText("");
  	}

    public int getValue() {
        return cardValue;
    }
    
    public String getValue2() {
        return cardValue2;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void flip() {
        flipped = !flipped;
        if (flipped) {
            setText(String.valueOf(cardValue));
        } else {
            setText("");
            revalidate();
            repaint();
        }
    }

    public void setMatched(boolean isMatched) {
        matched = isMatched;
    }

	public void doReaction()
	{
		System.out.println("do Reation");
		
	}
}