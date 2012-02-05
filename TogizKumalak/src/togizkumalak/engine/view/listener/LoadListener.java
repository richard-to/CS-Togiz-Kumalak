package togizkumalak.engine.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import togizkumalak.engine.view.ILoadSubscriber;

public class LoadListener implements ActionListener
{
	private ILoadSubscriber _subscriber;
	
	public LoadListener(ILoadSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		_subscriber.loadAction();
	}
}
