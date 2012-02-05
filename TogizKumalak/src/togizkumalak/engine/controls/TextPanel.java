package togizkumalak.engine.controls;

import javax.swing.JPanel;

public abstract class TextPanel extends JPanel
{
	private static final long serialVersionUID = -8430883913001243952L;
	
	public abstract void clear();
	public abstract void write(String text);
	public abstract void writeln(String text);
	public abstract void writeln();
	public abstract String getText();
}
