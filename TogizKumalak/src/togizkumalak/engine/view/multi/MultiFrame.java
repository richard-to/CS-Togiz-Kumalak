package togizkumalak.engine.view.multi;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MultiFrame extends JFrame 
{
	private static final long serialVersionUID = -1419516893685864658L;
	
	public static final int FRAME_HEIGHT = 500;
	public static final int FRAME_WIDTH = 500;
	
	public MultiFrame(String title, JMenuBar menu)
	{
		setJMenuBar(menu);
		setTitle(title);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
		addWindowListener(new WindowCloser());
	}
	
	private class WindowCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent event)
		{
			System.exit(0);
		}
	}
}
