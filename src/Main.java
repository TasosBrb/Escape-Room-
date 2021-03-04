import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		launchMainMenu();
	}
	
	public static void launchMainMenu() {
		Sound.startBackgroundMusic("sounds/Intro.wav");
		try {
			new WelcomeScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}