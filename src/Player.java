import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player {
	
    //We decided to not instantiate the player because there will be no records saved for each player (in contradiction to SDS)
	private static int lives = 2;
	private static int current_room = 1;
	private static int score = 0;
	private static int time = 35;
	//total time reduced to 35 seconds from 5 minutes (in contradiction to SDS) to match the difficulty
	private static final int totalTime = 35;
	private static Timer gameTimer;
	private static boolean skipAvailable = true;
	private static boolean bonus;
	private static Image photo;
	private static int under10s = 1;
	
	public static int getScore() {
		return score;
	}
	
	public static void updateScore() {
		int tempScore = 1000 + (100 * time);
		//if the player answers in less than 5 seconds, he or she receives double points
		if (GameFunctions.getNewRoundTimeMark() - Player.getRemainingTime() <= 5) {
			bonus = true;
			tempScore = 2 * tempScore;
		}
		else
			bonus = false;
		score += tempScore;
	}
	
	public static boolean gotBonus() {
		return bonus;
	}
	
	public static int getLives() {
		return lives;
	}
	
	public static void addALife() {
		lives += 1;
	}
	
	public static void removeALife() {
		lives -= 1;
	}
	
	public static void setPhoto(int photoid) {
		photo= new ImageIcon("Images/char"+photoid+".png").getImage(); 
	}
	
	public static Image getPhoto() {
		return photo;
	}

	public static void startTheTimer() {
		gameTimer = new Timer(1000, new AbstractAction() {

			public void actionPerformed(ActionEvent ae) {
		        	if (time <= 0) {
		        		gameTimer.stop();
		        		GameFunctions.timeIsUp();
		        	}
		        	else {
		        		reduceTime(1);
		        	}
		        	if (under10s == 1 && time < 10) {
		        		Sound.stopBackgroundMusic();
		        		Sound.startBackgroundMusic("sounds/Ending.wav");
		        		under10s = 0;
		        	}
		    	}
			});
		gameTimer.setRepeats(true);
		gameTimer.start();
		GamePanel.updateTime();
	}
	

	public static void stopTheTimer() {
		gameTimer.stop();
		GamePanel.stopTheTime();
	}
	
	public static int getRemainingTime() {
		return time;
	}
	
    //reduces the time left of the Player by the given amount
	public static void reduceTime(int reduceTimeSeconds) {
		if (time - reduceTimeSeconds < 0)
			time = 0;
		else
			time -= reduceTimeSeconds;
	}
	
	public static int getTotalTime() {
		return totalTime;
	}
	public static int getCurrentRoom() {
		return current_room;
	}
	
	public static void updateCurrentRoom() {
		current_room++;
	}
	
	public static boolean isSkipAvailable() {
		return skipAvailable;
	}
	
	public static void setSkipNotAvailable() {
		skipAvailable = false;
	}
	
    //when the game restarts, it resets these fields to the default values
	public static void reset() {
		lives = 2;
		current_room = 1;
		score = 0;
		time = totalTime;
		skipAvailable = true;
		gameTimer.stop();
		under10s = 1;
	}
}
