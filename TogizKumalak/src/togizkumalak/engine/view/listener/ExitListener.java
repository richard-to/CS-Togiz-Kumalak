package togizkumalak.engine.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import togizkumalak.engine.view.IExitSubscriber;

public class ExitListener implements ActionListener
{
	private IExitSubscriber _subscriber;
	
	public ExitListener(IExitSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		_subscriber.exitAction();
	}
}
