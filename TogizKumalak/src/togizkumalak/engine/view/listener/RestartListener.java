package togizkumalak.engine.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import togizkumalak.engine.view.IRestartSubscriber;

public class RestartListener implements ActionListener
{
	private IRestartSubscriber _subscriber;
	
	public RestartListener(IRestartSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		_subscriber.restartAction();
	}
}
