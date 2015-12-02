import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class MyListener implements KeyListener{
	public static ArrayList<Integer> keys = new ArrayList<Integer>();
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!keys.contains(e.getKeyCode()))
			keys.add(new Integer(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys.remove(new Integer(e.getKeyCode()));
		
		System.out.println(keys);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
