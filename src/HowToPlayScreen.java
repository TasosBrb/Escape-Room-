import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;


public class HowToPlayScreen extends JFrame {
	//Frame that contains the rules of the game
	private JTextArea rulesTextArea;

	public HowToPlayScreen() {

		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
		this.setTitle("How to play : ");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\Question Mark.png"));
		this.setBounds(100, 100, 410, 450);
		this.getContentPane().setLayout(null);

		//read how to play file
		String howToPlayText = "";
		try {
			howToPlayText = new String(Files.readAllBytes(Paths.get("Docs\\howtoplay.txt")), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		rulesTextArea = new JTextArea();
		rulesTextArea.setEditable(false);
		rulesTextArea.setToolTipText("");
		rulesTextArea.setAlignmentX(CENTER_ALIGNMENT);
		rulesTextArea.setAlignmentY(CENTER_ALIGNMENT);
		rulesTextArea.setText(howToPlayText);
		rulesTextArea.setBackground(Color.DARK_GRAY);
		rulesTextArea.setForeground(Color.BLACK);
		rulesTextArea.setBounds(5, 10, 410, 420);
		this.getContentPane().add(rulesTextArea);
		rulesTextArea.setOpaque(false);
		rulesTextArea.setColumns(10);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setBounds(0, 0, 484, 482);
		imageLabel.setIcon(new ImageIcon("images\\p.jpg"));
		
		this.getContentPane().add(imageLabel);
		
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
		this.setAlwaysOnTop (true);
		this.setLocation(WelcomeScreen.getWelcomeScreenFrameX() - 420, WelcomeScreen.getWelcomeScreenFrameY());
		
		//to avoid multiple HowToPlayScreens
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (true){
		        	WelcomeScreen.setHowToPlayFrameFlag(0);
		        }
		    }
		});
	}	
}

