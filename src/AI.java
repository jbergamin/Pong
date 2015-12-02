

public class AI {
	
	private int direction, ballY, paddlePositionY;	// 1 for up, 0 for down
	
	public AI(){
		
	}
	
	public void setData(int y, int paddleY){
		ballY = y;
		paddlePositionY = paddleY;
	}
	
	public int getDirection(){
		if(paddlePositionY > ballY){
			direction = 1;
		}
		if(paddlePositionY < ballY){
			direction = 0;
		}
		return direction;
	}

}
