package togizkumalak.engine.view.grid;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import togizkumalak.engine.view.IKeySubscriber;

public class GridTextKeyAdapter extends KeyAdapter 
{
	private IKeySubscriber _subscriber;
	
	public GridTextKeyAdapter(IKeySubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void keyReleased(KeyEvent event)
	{
		if(event.getModifiers() == 2){
			_subscriber.keyAction(event.getKeyChar());
		} else {
			_subscriber.keyAction('-');
		}
	}
}
