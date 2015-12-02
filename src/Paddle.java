import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Paddle {
	private int positionX, positionY, length, speed;	// x and y are top left corner of paddle
	private double direction;
	private final int width = 20;	// thickness of paddle
	private Color color;
	
	public Paddle(int x, int y, int l, Color c){	// Constructor
		positionX = x;
		positionY = y;
		length = l;
		color = c;
	}
	
	public void setPosition(int x, int y){
		positionX = x;
		positionY = y;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public int getX(){
		return positionX;
	}
	
	public int getY(){
		return positionY;
	}
	
	public int getLength(){
		return length;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void draw(Graphics g){	// draws paddle
		Color oldColor = g.getColor();
		g.setColor(color);
		g.drawRect(positionX, positionY, width, length);
		g.setColor(oldColor);
	}
	
	public void fill(Graphics g){	// draws filled paddle
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fillRect(positionX, positionY, width, length);
		g.setColor(oldColor);
	}
	
	public void setSpeed(int s){
		speed = s;
	}
	
	public void setDirection(double d){	// in radians
		direction = d;
	}
	
	public void move(int xAmt, int yAmt){
		positionX += xAmt;
		positionY += yAmt;
	}
	
	public void move(){		// update position based on direction and speed
		move((int)(Math.cos(direction) * (double)speed),
				(int)(Math.sin(direction) * (double)speed));
	}
	
	public Rectangle getBounds(){	// get rectangle used in collisions
		return new Rectangle(positionX, positionY, width, length);
	}
}
