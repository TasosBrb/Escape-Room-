import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class WelcomeScreen extends JFrame{

	//FigurePanel did not implemented (in contradiction to SDS). Instead the character select feature is included here
	private JButton playButton,rulesButton,exitButton,char1,char2;
	private JLabel imageLabel;
	private JTextArea storyTextArea;
	private static int howToPlayFrameFlag = 0, locationXOfFrame = 0 , locationYOfFrame = 0, characterSelected = 0;
	private static JFrame welcomeScreenFrame;
	
	public WelcomeScreen() throws IOException {		

		getContentPane().setLayout(null);
		if (welcomeScreenFrame == null) {
			welcomeScreenFrame = new JFrame("Escape Room");
		}
		else {
			characterSelected = 0;
			welcomeScreenFrame.getContentPane().removeAll();
			welcomeScreenFrame.repaint();
		}
		playButton = new JButton("");
		playButton.setIcon(new ImageIcon("images\\PlayButton.png"));
		playButton.setBackground(new Color(0, 128, 128));
		playButton.setForeground(new Color(0, 128, 128));
		playButton.setBounds(150, 150, 200, 60);
		PlayButtonListener playListener = new PlayButtonListener();
		playButton.addActionListener(playListener);
		welcomeScreenFrame.getContentPane().add(playButton);
		
		rulesButton = new JButton("");
		rulesButton.setForeground(new Color(0, 128, 128));
		rulesButton.setBackground(new Color(0, 128, 128));
		rulesButton.setIcon(new ImageIcon("images\\RulesButton.png"));
		rulesButton.setBounds(150, 220, 200, 60);		
		RulesButtonListener rulesListener = new RulesButtonListener();
		rulesButton.addActionListener(rulesListener);	
		welcomeScreenFrame.getContentPane().add(rulesButton);
		
		exitButton = new JButton("");
		exitButton.setForeground(new Color(0, 128, 128));
		exitButton.setBackground(new Color(0, 128, 128));
		exitButton.setIcon(new ImageIcon("images\\ExitButton.png"));
		exitButton.setBounds(150, 290, 200, 60);		
		ExitButtonListener exitListener = new ExitButtonListener();
		exitButton.addActionListener(exitListener);	
		welcomeScreenFrame.getContentPane().add(exitButton);
				
		imageLabel = new JLabel("");
		imageLabel.setBounds(0, 0, 484, 482);
		imageLabel.setIcon(new ImageIcon("images\\keys.jpg"));
		welcomeScreenFrame.getContentPane().add(imageLabel);
		
		//transparency for buttons
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		rulesButton.setOpaque(false);
		rulesButton.setContentAreaFilled(false);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		

		welcomeScreenFrame.pack();
		welcomeScreenFrame.setLocationRelativeTo(null);
		welcomeScreenFrame.setVisible(true);
		welcomeScreenFrame.setTitle("Escape Room 007\r\n");
		welcomeScreenFrame.setSize(490, 510);
		welcomeScreenFrame.setResizable(false);
		welcomeScreenFrame.setForeground(new Color(255, 255, 255));
		welcomeScreenFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\icon.png"));
		welcomeScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Play button. Previous name: ButtonListener1 renamed to
	//PlayButtonListener for better readability.
	class PlayButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			
			welcomeScreenFrame.getContentPane().removeAll();
			welcomeScreenFrame.repaint();
			
			JButton backButton = new JButton("");
			backButton.setForeground(new Color(0, 128, 128));
			backButton.setBackground(new Color(0, 128, 128));
			backButton.setIcon(new ImageIcon("images\\BackButton.png"));
			backButton.setBounds(140, 20, 200, 60);		
			BackButtonListener backListener = new BackButtonListener();
			backButton.addActionListener(backListener);	
			
			getContentPane().setLayout(null);
			welcomeScreenFrame.getContentPane().add(backButton);			
			
			char1 = new JButton("");
			char1.setIcon(new ImageIcon("images\\char1.png"));
			char1.setBackground(Color.WHITE);
			char1.setBounds(28, 90, 200, 300);
			Char1ButtonListener char1Listener = new Char1ButtonListener();
			char1.addActionListener(char1Listener);
			
			welcomeScreenFrame.getContentPane().add(char1);	
			
			char2 = new JButton("");
			char2.setIcon(new ImageIcon("images\\char2.png"));
			char2.setBackground(Color.WHITE);
			char2.setBounds(259, 90, 200, 300);
			Char2ButtonListener char2Listener = new Char2ButtonListener();
			char2.addActionListener(char2Listener);			
			
			welcomeScreenFrame.getContentPane().add(char2);
					
			JButton nextButton = new JButton();
			nextButton.setIcon(new ImageIcon("images\\NextButton.png"));
			nextButton.setForeground(new Color(0, 128, 128));
			nextButton.setBackground(new Color(0, 128, 128));
			nextButton.setBounds(140, 401, 200, 60);
			NextButtonListener nextListener = new NextButtonListener();
			nextButton.addActionListener(nextListener);		
			
			welcomeScreenFrame.getContentPane().add(nextButton);
			
			//button transparency 
			char2.setOpaque(false);
			char2.setContentAreaFilled(false);
			char1.setOpaque(false);
			char1.setContentAreaFilled(false);
			nextButton.setOpaque(false);
			nextButton.setContentAreaFilled(false);
			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			
			JLabel imageLabel = new JLabel("");
			imageLabel.setBounds(0, 0, 484, 482);
			imageLabel.setIcon(new ImageIcon("images\\keys.jpg"));
			
			welcomeScreenFrame.getContentPane().add(imageLabel);
		}
	}
	
	//rules button. Renamed from ButtonListener2
	class RulesButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent b) {
				
			//x axis location of the frame 
			locationXOfFrame=welcomeScreenFrame.getX();
			//y axis location of the frame 
			locationYOfFrame=welcomeScreenFrame.getY();
			
			if(howToPlayFrameFlag == 1) { 
				return;
            }		
			new HowToPlayScreen();
			howToPlayFrameFlag = 1;
		}
	}
	
	//exit button. Renamed from ButtonListener3.
	class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent c) {
			Frame[] frames = Frame.getFrames();
			for (final Window w : frames) {
			    w.dispose();
			}
			System.exit(0);
		}
	}
	
	//back button from character selection screen. Renamed from ButtonListener4.
	//The same applies to the rest ButtonListeners.
	class BackButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent c) {
			try {
				new WelcomeScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//character 1 selected
	class Char1ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			characterSelected = 1;
			char1.setIcon(new ImageIcon("images\\char1selected.png"));
			char2.setIcon(new ImageIcon("images\\char2.png"));
		}
	}
	
	//character 2 selected
	class Char2ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent b) {	
			characterSelected = 2;
			char2.setIcon(new ImageIcon("images\\char2selected.png"));
			char1.setIcon(new ImageIcon("images\\char1.png"));
		}
	}
	
	//After a character has been selected and the next button is pressed.
	public void ShowStory() {	
			
			welcomeScreenFrame.getContentPane().removeAll();
			welcomeScreenFrame.repaint();
			getContentPane().setLayout(null);
			
			//read story file
			String story = "";
			try {
				story = new String(Files.readAllBytes(Paths.get("docs\\story.txt")), "ISO-8859-7");
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			storyTextArea = new JTextArea();
			storyTextArea.setToolTipText("");
			storyTextArea.setText(story);
			storyTextArea.setBackground(Color.DARK_GRAY);
			storyTextArea.setForeground(Color.BLACK);
			storyTextArea.setBounds(10, 10, 484, 400);
			storyTextArea.setFont(new Font("Courier New", Font.BOLD, 12));
			storyTextArea.setEditable(false);
			storyTextArea.setOpaque(false);			
			
			JButton okButton = new JButton("");
			okButton.setIcon(new ImageIcon("images\\OkButton.png"));
			okButton.setForeground(new Color(0, 128, 128));
			okButton.setBackground(new Color(0, 128, 128));
			okButton.setBounds(140, 390, 200, 60);
			okButton.setOpaque(false);
			okButton.setContentAreaFilled(false);
			OkButtonListener okListener = new OkButtonListener();
			okButton.addActionListener(okListener);	
					
			JLabel imageLabel = new JLabel("");
			imageLabel.setBounds(0, 0, 484, 482);
			imageLabel.setIcon(new ImageIcon("images\\lake.jpg"));
		
			welcomeScreenFrame.getContentPane().add(okButton);
			welcomeScreenFrame.getContentPane().add(storyTextArea);
			welcomeScreenFrame.getContentPane().add(imageLabel);
		
		}
	
	//Game START when ok button is pressed.
	class OkButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent b) {
			Frame[] frames = Frame.getFrames();
			for (final Window w : frames) {
			    w.dispose();
			}
			Sound.stopBackgroundMusic();
			GameFunctions.launchTheGame(characterSelected);
		}
	}
	
	//next button
	class NextButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent b) {	
			if (characterSelected >= 1 )
				ShowStory();
			else
				GameFunctions.showMessage("Select character first!", 1500);;
		}
	}
	
	//Number of how to play screens max = 1 
	//A way to avoid multiple copies of the
	//rules/how to play screen.
	public static void setHowToPlayFrameFlag(int x){
		howToPlayFrameFlag = x;
    }
	
	//get location of WelcomeScreenFrame
	public static int getWelcomeScreenFrameX(){
		return 	locationXOfFrame;
    }
	
	public static int getWelcomeScreenFrameY(){
		return 	locationYOfFrame;
    }
}
