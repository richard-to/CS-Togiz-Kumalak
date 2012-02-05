package togizkumalak.engine.controls;

import javax.swing.JFrame;

import togizkumalak.engine.view.ICanvas;

public class FrameCanvas extends JFrame implements ICanvas 
{
	private static final long serialVersionUID = 8740585111074489273L;

	public FrameCanvas(String title)
	{
		setTitle(title);
		setVisible(true);
	}
}
