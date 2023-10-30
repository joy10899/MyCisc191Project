import java.awt.Dimension;

import javax.swing.JButton;

class FlipCard extends JButton {
    private int cardValue;
    private boolean flipped = false;
    private boolean matched = false;

    public FlipCard(int cardValue) {
        this.cardValue = cardValue;
        setPreferredSize(new Dimension(100, 100));
        setText("");
    }

    public int getValue() {
        return cardValue;
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
        }
    }

    public void setMatched(boolean isMatched) {
        matched = isMatched;
    }
}