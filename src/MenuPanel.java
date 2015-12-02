import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4144156826778322656L;
	
	private int width, height;
	private Image background;

	public MenuPanel(int w, int h){
		width = w;
		height = h;
		setPreferredSize(new Dimension(width, height));
		
		try {
			background = ImageIO.read(new File("Resources/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

}
