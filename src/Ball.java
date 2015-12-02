import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Ball {
	
	private double centerX;
	private double centerY;
	private int radius;
	private int speed;
	private double direction;
	private Color color;
	
	public Ball(int x, int y, int r, Color c){	// constructor
		centerX = x;
		centerY = y;
		radius = r;
		color = c;
	}
	
	public void setPosition(int x, int y){		// move ball to x and y  position
		centerX = x;
		centerY = y;
	}
	
	public void draw(Graphics g){	// draws ball
		Color oldColor = g.getColor();
		g.setColor(color);
		g.drawOval((int)centerX - radius, (int)centerY - radius, radius*2, radius*2);
		g.setColor(oldColor);
	}
	
	public void fill(Graphics g){	// draws filled ball
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fillOval((int)centerX - radius, (int)centerY - radius, radius*2, radius*2);
		g.setColor(oldColor);
	}
	
	public void setSpeed(int s){
		speed = s;
	}

	public void setDirection(double d){	// in radians
		direction = d;
		System.out.println(direction);
	}
	
	public void turn(double d){	// in radians
		direction = (direction + d) % (Math.PI * 2.0);
	}
	
	public void move(double xAmt, double yAmt){
		centerX += xAmt;
		centerY += yAmt;
	}
	
	public void move(){		// move ball based on current speed and direction
		move(((Math.cos(direction) * ((double)speed))),
				((Math.sin(direction) * ((double)speed))));
		// System.out.println((Math.cos(direction) * (double)speed) + "  " + (Math.sin(direction) * (double)speed));
	}
	
	public Rectangle getBounds(){	// returns rectangle used in collisions
		return new Rectangle((int)centerX - radius, (int)centerY - radius, radius * 2, radius * 2);
	}
	
	public double getCenterX(){
		return centerX;
	}
	
	public double getCenterY(){
		return centerY;
	}
	
	public double getDirection(){
		return direction;
	}
	
	public int getRadius(){
		return radius;
	}
}
