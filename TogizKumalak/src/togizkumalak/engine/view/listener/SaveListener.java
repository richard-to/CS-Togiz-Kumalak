package togizkumalak.engine.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import togizkumalak.engine.view.ISaveSubscriber;

public class SaveListener implements ActionListener
{
	private ISaveSubscriber _subscriber;
	
	public SaveListener(ISaveSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		_subscriber.saveAction();
	}
}
