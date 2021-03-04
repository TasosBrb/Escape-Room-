import java.util.Random;

public class Enemy {
    //We decided to not instantiate the enemy or give him any properties (like name e.t.c.)
    //because they wouldn't have any impact (in contradiction to SDS)
	
	//Reduce the time of the Player after a correct answer with a chance
	public static int reduceTime() {
        return (1 + new Random().nextInt(6));
	}

}
