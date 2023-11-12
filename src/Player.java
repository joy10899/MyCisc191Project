/**
 * Player class build data for the player
 */

public class Player {
	//fields
    private String name;
    private int score;
    //constructor
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
    //method get Player's name
    public String getName() {
        return name;
    }
    //method get Player's score
    public int getScore() {
        return score;
    }
    //method to increase score
    public void incrementScore() {
        score++;
    }
    
    @Override
    public String toString() {
        return "Score :"+name + ": " + score;
    }
}

 
