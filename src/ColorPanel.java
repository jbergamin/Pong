import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ColorPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private javax.swing.Timer timer;
	private boolean paddle1Wins = false, paddle2Wins = false, aiOn;
	public static Paddle paddle1, paddle2;
	public static Ball ball;
	private AI aiPlayer;
	private int player1Score = 0, player2Score = 0, width, height;
	
	public ColorPanel(Color backColor, int w, int h, boolean ai){
		aiOn = ai;
		width = w;
		height = h;
		setBackground(backColor);
		setPreferredSize(new Dimension(width, height));
		
		ball = new Ball(width / 2, height / 2, 10, Color.red);		// creates ball and two paddles for game
		ball.setSpeed(5);
		ball.setDirection(0);
		paddle1 = new Paddle(15, (height / 2), 100, Color.black);
		paddle2 = new Paddle(width - 35, (height / 2), 100, Color.black);
		paddle1.setPosition(15, (height / 2) - (paddle1.getLength() / 2));
		paddle2.setPosition(width - 35, (height / 2) - (paddle2.getLength() / 2));
		paddle1.setSpeed(4);
		paddle2.setSpeed(4);
		
		if(aiOn){
			aiPlayer = new AI();
			paddle1.setColor(Color.blue);	// change color of paddle controlled by AI
		}
		
		KeyListener listener = new MyListener();	// set up keyboard listener for game control
		addKeyListener(listener);
		setFocusable(true);
		
		timer = new javax.swing.Timer(5, (ActionListener) new MoveListener());	// timer for animation
		timer.start();
	}
	
	public void paintComponent(Graphics g){		// paints the two paddles and the ball
		super.paintComponent(g);
		paddle1.fill(g);
		paddle2.fill(g);
		ball.fill(g);
		g.drawString("Paddle 1: " + player1Score, (getWidth() / 2) - 100, 30);	// displays scores
		g.drawString("Paddle 2: " + player2Score, (getWidth() / 2) + 100, 30);

	}
	
	private class MoveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			// ** Keyboard Input **
			if(!aiOn){	// When AI is off.
				if(MyListener.keys.contains(KeyEvent.VK_W)){
					// System.out.println("contains W");
					paddle1.setDirection((3*Math.PI)/2);
					paddle1.move();
				}
				if(MyListener.keys.contains(KeyEvent.VK_S)){
					// System.out.println("contains S");
					paddle1.setDirection(Math.PI/2);
					paddle1.move();
				}
			}else if(aiOn){	// AI movement of paddle
				aiPlayer.setData((int)ball.getCenterY(), (int)(paddle1.getY() + (paddle1.getLength() / 2.0)));
				if(aiPlayer.getDirection() == 1){
					paddle1.setDirection((3*Math.PI)/2);
					paddle1.move();
				}
				if(aiPlayer.getDirection() == 0){
					paddle1.setDirection(Math.PI/2);
					paddle1.move();
				}
			}
				
			if(MyListener.keys.contains(KeyEvent.VK_UP)){
				// System.out.println("contain UP");
				paddle2.setDirection((3*Math.PI)/2);
				paddle2.move();
			}
			if(MyListener.keys.contains(KeyEvent.VK_DOWN)){
				// System.out.println("contains DOWN");
				paddle2.setDirection(Math.PI/2);
				paddle2.move();
			}
			// ^^ ** Keyboard Input ** ^^
			
			if(ball.getCenterY() > height || ball.getCenterY() < 0){
				ball.setDirection((ball.getDirection() * -1));
			}
			
			ball.move();
			
			// ** top and bottom rebound **
			if(ball.getCenterX() > width){		// if ball goes out of view on right side player 1 wins
				System.out.println("Paddle 1 Wins");
				paddle1Wins = true;
				paddle2Wins = false;
				endGame();
			}
			
			if(ball.getCenterX() < 0){		// if ball goes out of view on left side player 2 wins
				System.out.println("Paddle 2 Wins");
				paddle1Wins = false;
				paddle2Wins = true;
				endGame();
			}
			
			// ** Paddle rebound **
			if(paddle1.getBounds().intersects(ball.getBounds())){	// rebound on paddle1
				// checks for collision and turns 180 degrees if there is one
				double distanceBetweenBallAndCenter = ((double)paddle1.getY() + ((double)paddle1.getLength() /2)) - (double)ball.getCenterY();
				double ratio = distanceBetweenBallAndCenter / (double)paddle1.getLength();
				// System.out.println(ratio);
				ball.setDirection((ball.getDirection() * -1 + Math.PI) + (-(Math.PI / 3.0) * ratio));
				ball.setPosition(paddle1.getX() + paddle1.getWidth() + ball.getRadius(), (int)ball.getCenterY());
			}
			if(paddle2.getBounds().intersects(ball.getBounds())){	// rebound on paddle2
				// checks for collision and turns 180 degrees if there is one
				double distanceBetweenBallAndCenter = ((double)paddle2.getY() + ((double)paddle2.getLength() /2)) - (double)ball.getCenterY();
				double ratio = distanceBetweenBallAndCenter / (double)paddle2.getLength();
				// System.out.println(ratio);
				ball.setDirection((ball.getDirection() * -1) + Math.PI + ((Math.PI / 3.0) * ratio));
				ball.setPosition(paddle2.getX() - ball.getRadius(), (int)ball.getCenterY());
			}
			
			repaint();
		}
	}
	
	public void endGame(){		// does this when one player wins
		String str = "NOT INITIALIZED";
		if(paddle1Wins){
			str = "Paddle 1 Wins!";
			player1Score++;
			
		}
		if(paddle2Wins){
			str = "Paddle 2 Wins!";
			player2Score++;
		}
		if((paddle1Wins && paddle2Wins) || (!paddle1Wins && !paddle2Wins)) str = "** ERROR **";
		JOptionPane.showMessageDialog(getParent(), str);
		ball.setPosition(getWidth() / 2, getHeight() / 2);
		ball.setDirection(0);
		paddle1.setPosition(15, (height / 2) - (paddle1.getLength() / 2));
		paddle2.setPosition(width - 35, (height / 2) - (paddle2.getLength() / 2));
		MyListener.keys.clear();
	}
	
	public void terminateGame(){
		timer.stop();
	}
}
