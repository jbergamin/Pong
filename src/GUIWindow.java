import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUIWindow {
	
	private static JButton onePlayer	= new JButton("1 Player");		// buttons to select one or two players
	private static JButton twoPlayer	= new JButton("2 Player");
	private static final int WIDTH = 800, HEIGHT = 600, PLAYER_BUTTON_WIDTH = 200, PLAYER_BUTTON_HEIGHT = 50;
	private static Container pane;
	private static JFrame theGUI;
	private static MenuPanel menuPanel;
	private static ColorPanel panel;
	
	public static void main(String[] args){
		run();
	}
	
	public static void run(){
		theGUI = new JFrame();
		theGUI.setTitle("Pong");
		theGUI.setResizable(false);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuPanel = new MenuPanel(WIDTH, HEIGHT);	// menu panel, user selects one player or two player
		menuPanel.setLayout(null);
		onePlayer.setBounds(WIDTH / 2 - 150 - (PLAYER_BUTTON_WIDTH / 2), HEIGHT - 100, PLAYER_BUTTON_WIDTH, PLAYER_BUTTON_HEIGHT);
		twoPlayer.setBounds(WIDTH / 2 + 150 - (PLAYER_BUTTON_WIDTH / 2), HEIGHT - 100, PLAYER_BUTTON_WIDTH, PLAYER_BUTTON_HEIGHT);
		menuPanel.add(onePlayer);
		menuPanel.add(twoPlayer);
		pane = theGUI.getContentPane();
		pane.add(menuPanel);
		
		theGUI.pack();
		theGUI.setVisible(true);
		
		onePlayer.addActionListener(new PlayerOneListener());
		twoPlayer.addActionListener(new PlayerTwoListener());
	}
	
	public static void onePlayer(){		// removes menu panel and replaces with game panel
		theGUI.setVisible(false);
		theGUI.getContentPane().removeAll();
		menuPanel = null;
		panel = new ColorPanel(Color.white, WIDTH, HEIGHT, true);
		panel.setLayout(null);
		theGUI.getContentPane().add(panel);
		theGUI.getContentPane().invalidate();
		theGUI.getContentPane().validate();
		theGUI.setVisible(true);
	}
	
	public static void twoPlayer(){		// removes menu panel and replaces with game panel
		theGUI.setVisible(false);
		theGUI.getContentPane().removeAll();
		menuPanel = null;
		panel = new ColorPanel(Color.white, WIDTH, HEIGHT, false);
		panel.setLayout(null);
		theGUI.getContentPane().add(panel);
		theGUI.getContentPane().invalidate();
		theGUI.getContentPane().validate();
		theGUI.setVisible(true);
	}
	
	private static class PlayerOneListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			onePlayer();
		}
	}
	
	private static class PlayerTwoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			twoPlayer();
		}
	}
}
