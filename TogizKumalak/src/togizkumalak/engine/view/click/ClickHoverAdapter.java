package togizkumalak.engine.view.click;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import togizkumalak.engine.view.IHoverSubscriber;

public class ClickHoverAdapter extends MouseMotionAdapter 
{
	private IHoverSubscriber _subscriber;
	
	public ClickHoverAdapter(IHoverSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void mouseMoved(MouseEvent event)
	{
		_subscriber.hoverAction(event.getPoint());
	}
}
