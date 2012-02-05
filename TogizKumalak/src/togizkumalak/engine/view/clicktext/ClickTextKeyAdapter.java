package togizkumalak.engine.view.clicktext;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import togizkumalak.engine.view.IKeySubscriber;

public class ClickTextKeyAdapter extends KeyAdapter 
{
	private IKeySubscriber _subscriber;
	
	public ClickTextKeyAdapter(IKeySubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void keyTyped(KeyEvent event)
	{
		_subscriber.keyAction(event.getKeyChar());
	}
}
