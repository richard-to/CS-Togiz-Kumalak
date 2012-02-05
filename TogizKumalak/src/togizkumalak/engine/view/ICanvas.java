package togizkumalak.engine.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JMenuBar;

public interface ICanvas 
{
	public void setSize(Dimension dimension);
	public Container getContentPane();
	public void addKeyListener(KeyListener l);
	public void setJMenuBar(JMenuBar menuBar);
	public void setFocusable(boolean focusable);
}
