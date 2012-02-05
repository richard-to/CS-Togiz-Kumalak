package togizkumalak.engine.view.click;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import togizkumalak.engine.view.IClickSubscriber;

public class ClickMouseAdapter extends MouseAdapter 
{
	private IClickSubscriber _subscriber;
	
	public ClickMouseAdapter(IClickSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void mouseClicked(MouseEvent event)
	{
		_subscriber.clickAction(event.getPoint());
	}
}
