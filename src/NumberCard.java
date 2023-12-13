import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
/**
 * A class create functions for Number Card
 */
public class NumberCard extends FlipCard
{
	/**
	 * Constructor
	 * @param cardValue
	 */
	public NumberCard(int cardValue) {
        super(cardValue);
        setPreferredSize(new Dimension(100, 100));
        setText("");
    }
	
	/**
	 * Reaction Method to play Sound
	 */
	@Override
	public void doReaction() {
		try {
			//Load audio file 
			File soundFile = new File("Number.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			//Get a Clip to play the sound 
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			//Play the sound
			clip.start();
			//Wait 1s for the sound to finish
			Thread.sleep(1000);
			//Close the clip 
			clip.close();
		} catch ( LineUnavailableException | IOException | javax.sound.sampled.UnsupportedAudioFileException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
