import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class AnimalCard extends FlipCard
{
	private String animalvalue;
	private JFrame f;
	private ImageIcon img;
	private JLabel l;
	private boolean flipped = false;
	private boolean hasReaction = false;

	public AnimalCard(String cardValue2)
	{
		super(cardValue2);
		setPreferredSize(new Dimension(100, 100));
		setText("");
	}

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

	public String getValue2()
	{
		return cardValue2;
	}

	@Override
	public void doReaction()
	{
		// f = new JFrame();
		// img = new ImageIcon("worm.gif");
		// l = new JLabel("",img,JLabel.CENTER);
		// f.setSize(new Dimension(600,400));
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f.setLocationRelativeTo(null);
		// f.add(l);
		// f.setVisible(true);
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
