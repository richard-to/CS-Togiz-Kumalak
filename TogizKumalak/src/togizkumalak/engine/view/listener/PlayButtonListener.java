package togizkumalak.engine.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import togizkumalak.engine.view.IPlayButtonSubscriber;

public class PlayButtonListener implements ActionListener
{
	private IPlayButtonSubscriber _subscriber;
	
	public PlayButtonListener(IPlayButtonSubscriber subscriber)
	{
		_subscriber = subscriber;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		_subscriber.buttonAction((JButton)event.getSource());
	}
}
