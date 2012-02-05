package togizkumalak.engine.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import togizkumalak.engine.view.ICreateGameSubscriber;

public class CreateGameListener implements ActionListener
{
	private ICreateGameSubscriber _subscriber;
	
	public CreateGameListener(ICreateGameSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		_subscriber.createGameAction();
	}
}
