import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class GamePanel {
	//main frame where the game takes place
	private static JFrame frame;
	private static JTextArea timeArea,livesArea;
	private int doorSelected = 0;
	private static int under10s = 1;
	private static Timer clock;
	
	public GamePanel() {
		
		if (frame == null)
			frame = new JFrame("Escape Room");
		else {
			frame.getContentPane().removeAll();
			frame.repaint();
			under10s = 1;
		}
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\ICON.png"));
		frame.setSize(1200, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		timeArea = new JTextArea();
		timeArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		timeArea.setEditable(false);
		timeArea.setBounds(43, 470, 169, 30);
		timeArea.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(timeArea);
		timeArea.setText("Time Left: " + GameFunctions.TimeConversion(Player.getRemainingTime()));
		timeArea.setOpaque(false);
		setTimeTextAreaForeground();
		
		livesArea = new JTextArea();
		livesArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		livesArea.setText("Lives: " + Player.getLives());
		livesArea.setEditable(false);
		livesArea.setBounds(43, 490, 169, 30);
		livesArea.setBackground(Color.LIGHT_GRAY);
		livesArea.setOpaque(false);
		setLivesTextAreaForeground();
		
		frame.getContentPane().add(livesArea);
		
		JTextArea scoreArea = new JTextArea();
		scoreArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		scoreArea.setText("Score: " + Player.getScore());
		scoreArea.setBounds(43, 510, 169, 30);
		scoreArea.setEditable(false);
		scoreArea.setBackground(Color.LIGHT_GRAY);
		scoreArea.setOpaque(false);
		frame.getContentPane().add(scoreArea);
		
		JTextArea roomArea = new JTextArea();
		roomArea.setText("Room " +  Player.getCurrentRoom());
		roomArea.setFont(new Font("Monospaced", Font.PLAIN, 50));
		roomArea.setBounds(605, 26, 184, 75);
		roomArea.setEditable(false);
		roomArea.setBackground(Color.LIGHT_GRAY);
		roomArea.setOpaque(false);
		frame.getContentPane().add(roomArea);
		
		JTextArea questionArea = new JTextArea();
		questionArea.setText(Story.getQuestion());
		questionArea.setBounds(410, 144, 591, 40);
		questionArea.setEditable(false);
		questionArea.setBackground(Color.LIGHT_GRAY);
		questionArea.setHighlighter(null);
		questionArea.setLineWrap(true);
		questionArea.setFont(new Font(null, Font.BOLD, 16));
		questionArea.setOpaque(false);
		frame.getContentPane().add(questionArea);
		
		JLabel playerImage = new JLabel();
		Image playerIcon = Player.getPhoto();
		playerImage.setIcon(new ImageIcon(playerIcon));
		playerImage.setForeground(Color.GRAY);
		playerImage.setBounds(13, 156, 200, 300);
		frame.getContentPane().add(playerImage);
		
		//Door class did not implemented (in contradiction to SDS). Instead each door is represented with a JButton
		JButton door1 = new JButton("");
		JButton door2 = new JButton("");
		JButton door3 = new JButton("");
		
		door1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				door1.setIcon(new ImageIcon("images\\DOOROPEN.png"));
				//close other doors
				door2.setIcon(new ImageIcon("images\\DOOR.png"));
				door3.setIcon(new ImageIcon("images\\DOOR.png"));
				doorSelected = 1;
				Sound.playSoundEffect("sounds/Door.wav");
			}
		});
		door1.setBounds(428, 195, 170, 300);
		
		door2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				door2.setIcon(new ImageIcon("images\\DOOROPEN.png"));
				//close other doors
				door1.setIcon(new ImageIcon("images\\DOOR.png"));
				door3.setIcon(new ImageIcon("images\\DOOR.png"));
				doorSelected = 2;
				Sound.playSoundEffect("sounds/Door.wav");
			}
		});
		door2.setBounds(628, 195, 170, 300);
		
		door3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				door3.setIcon(new ImageIcon("images\\DOOROPEN.png"));
				//close other doors
				door1.setIcon(new ImageIcon("images\\DOOR.png"));
				door2.setIcon(new ImageIcon("images\\DOOR.png"));
				doorSelected = 3;
				Sound.playSoundEffect("sounds/Door.wav");
			}
		});
		door3.setBounds(836, 195, 170, 300);

		//button transparency 
		door1.setOpaque(false);
		door1.setContentAreaFilled(false);
		door1.setBorderPainted(false);
		door2.setOpaque(false);
		door2.setContentAreaFilled(false);
		door2.setBorderPainted(false);
		door3.setOpaque(false);
		door3.setContentAreaFilled(false);	
		door3.setBorderPainted(false);
		
		Image doorIcon = new ImageIcon("Images/Door.png").getImage();
		door1.setIcon(new ImageIcon(doorIcon));
		door2.setIcon(new ImageIcon(doorIcon));
		door3.setIcon(new ImageIcon(doorIcon));
		
		frame.getContentPane().add(door1);	
		frame.getContentPane().add(door2);
		frame.getContentPane().add(door3);	

		JButton skipBtn = new JButton();
		skipBtn.setIcon(new ImageIcon("images\\SkipButton.png"));
		skipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Player.stopTheTimer();
					GameFunctions.skipBtn();
			}
		});
		skipBtn.setBounds(1059, 580, 125, 40);
		skipBtn.setOpaque(false);
		skipBtn.setContentAreaFilled(false);
		frame.getContentPane().add(skipBtn);
		
		JButton restartBtn = new JButton();
		restartBtn.setIcon(new ImageIcon("images\\RestartButton.png"));
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showOptionDialog(null, "Are you sure you want to Restart?", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (response == JOptionPane.YES_NO_OPTION) {
					frame.dispose();
					Player.reset();
					Sound.stopBackgroundMusic();
					Main.launchMainMenu();
				}
			}
		});
		restartBtn.setBounds(1059, 529, 125, 40);
		restartBtn.setOpaque(false);
		restartBtn.setContentAreaFilled(false);
		frame.getContentPane().add(restartBtn);
		
		JButton exitBtn = new JButton();
		exitBtn.setIcon(new ImageIcon("images\\ExitButton2.png"));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showOptionDialog(null, "Are you sure you want to Exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (response == 0)
					System.exit(0);
			}	
		});
		exitBtn.setBounds(1084, 631, 100, 30);
		exitBtn.setOpaque(false);
		exitBtn.setContentAreaFilled(false);
		frame.getContentPane().add(exitBtn);
		
		ArrayList<String> shuffledAnswers = new ArrayList<>(Story.getAnswers());
		Collections.shuffle(shuffledAnswers);	//shuffle the answers
		
		JTextArea answer1Area = new JTextArea(shuffledAnswers.get(0));
		answer1Area.setEditable(false);
		answer1Area.setBackground(Color.LIGHT_GRAY);
		answer1Area.setHighlighter(null);
		answer1Area.setLineWrap(true);
		answer1Area.setFont(new Font(null, Font.BOLD, 14));
		answer1Area.setBounds(428, 516, 150, 40);
		answer1Area.setOpaque(false);
		frame.getContentPane().add(answer1Area);

		JTextArea answer2Area = new JTextArea(shuffledAnswers.get(1));
		answer2Area.setEditable(false);
		answer2Area.setBackground(Color.LIGHT_GRAY);
		answer2Area.setHighlighter(null);
		answer2Area.setLineWrap(true);
		answer2Area.setFont(new Font(null, Font.BOLD, 14));
		answer2Area.setBounds(628, 516, 150, 40);
		answer2Area.setOpaque(false);
		frame.getContentPane().add(answer2Area);

		JTextArea answer3Area = new JTextArea(shuffledAnswers.get(2));
		answer3Area.setEditable(false);
		answer3Area.setBackground(Color.LIGHT_GRAY);
		answer3Area.setHighlighter(null);
		answer3Area.setLineWrap(true);
		answer3Area.setFont(new Font(null, Font.BOLD, 14));
		answer3Area.setBounds(836, 516, 150, 40);
		answer3Area.setOpaque(false);
		frame.getContentPane().add(answer3Area);
		
		JButton confirmBtn = new JButton();	
		confirmBtn.setIcon(new ImageIcon("images\\ConfirmButton.png"));
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1st Door selected
				if (doorSelected == 1) {
					//correct answer
					if (answer1Area.getText().equals(Story.getFirstRightAnswer()) || answer1Area.getText().equals(Story.getSecondRightAnswer())) {
						GameFunctions.rightAnswer();
					}
					//wrong answer
					else {
						GameFunctions.wrongAnswer();
					}
				}
				else if (doorSelected == 2) {
					if (answer2Area.getText().equals(Story.getFirstRightAnswer()) || answer2Area.getText().equals(Story.getSecondRightAnswer())) {
						GameFunctions.rightAnswer();
					}
					else {
						GameFunctions.wrongAnswer();
					}
				}
				else if (doorSelected == 3) {
					if (answer3Area.getText().equals(Story.getFirstRightAnswer()) || answer3Area.getText().equals(Story.getSecondRightAnswer())) {
						GameFunctions.rightAnswer();
					}
					else {
						GameFunctions.wrongAnswer();
					}		
				}
				else {
					Player.stopTheTimer();
					JOptionPane.showMessageDialog(frame, "Need to select an answer first!", "Information", JOptionPane.INFORMATION_MESSAGE);
					Player.startTheTimer();
				}
				livesArea.setText("Lives: " + Player.getLives());
				setLivesTextAreaForeground();
			}
		}
		);	
		confirmBtn.setBounds(640, 588, 120, 40);
		confirmBtn.setOpaque(false);
		confirmBtn.setContentAreaFilled(false);
		frame.getContentPane().add(confirmBtn);
		
		JLabel backgroundImageLabel = new JLabel();
		backgroundImageLabel.setBounds(0, 0, 1195, 700);
		backgroundImageLabel.setIcon(new ImageIcon("Images\\wall.png"));
		frame.getContentPane().add(backgroundImageLabel);
		
	}
	
	public static void updateTime() {
		
		clock = new Timer(1000, new AbstractAction() {
			public void actionPerformed(ActionEvent ae) {
					timeArea.setText("Time Left: " + GameFunctions.TimeConversion(Player.getRemainingTime()));
					setTimeTextAreaForeground();
				}
			});
		
		clock.setRepeats(true);
		clock.start();
	}
	
	public static void stopTheTime() {
		clock.stop();
	}
	
	//The colour of the displayed lives is green and becomes red when the remaining lives drop below 1
	private void setLivesTextAreaForeground() {
		if (Player.getLives() == 1)
			livesArea.setForeground(Color.RED);
		else
			livesArea.setForeground(Color.BLACK);
	}
	
	private static void setTimeTextAreaForeground() {
		if (under10s == 1 && Player.getRemainingTime() < 10) {
			timeArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
			timeArea.setForeground(Color.red);
		    under10s = 0;
		}
	}
	public static JFrame getFrame() {
		return frame;
	}
}
