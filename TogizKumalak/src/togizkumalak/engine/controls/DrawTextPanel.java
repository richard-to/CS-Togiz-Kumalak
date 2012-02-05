package togizkumalak.engine.controls;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DrawTextPanel extends TextPanel 
{
	private static final long serialVersionUID = -8697498858902593087L;
	
	private static final String BLANK = "";
	private static final int LINE_HEIGHT = 15;
	private static final int START_X = 10;
	private static final int START_Y = 20;
	private static final boolean MULTILINE_DEFAULT = true;
	
	private ArrayList<StringBuilder> _lines;
	private int _currentLine;
	private boolean _multiline;
	
	public DrawTextPanel()
	{
		super();		
		_init(MULTILINE_DEFAULT);		
	}

	public DrawTextPanel(boolean multiline)
	{
		super();		
		_init(multiline);		
	}

	/**
	 * Helper for constructor initialization
	 * 
	 * @param multiline
	 */
	private void _init(boolean multiline)
	{
		_lines = new ArrayList<StringBuilder>();
		_multiline = multiline;
		clear();		
	}
	
	/**
	 * Clear text on panel
	 */
	public void clear()
	{
		_lines.clear();
		_currentLine = _lines.size();
		_lines.add(new StringBuilder());
		repaint();
	}

	/**
	 * Gets text
	 */
	public String getText()
	{
		return _lines.toString();
	}
	
	/**
	 * Append text on panel
	 * 
	 * @param text
	 */
	public void write(String text)
	{
		StringBuilder line = _lines.get(_currentLine);
		line.append(text);
		repaint();
	}

	/**
	 * Append text on panel and add new line
	 * 
	 * @param text
	 */
	public void writeln(String text)
	{
		if(_multiline){
			write(text);
			_currentLine = _lines.size();
			_lines.add(new StringBuilder());
		} else {
			write(text);
		}
	}

	/**
	 * Add new line
	 * 
	 */
	public void writeln()
	{
		if(_multiline){
			write(BLANK);
			_currentLine = _lines.size();
			_lines.add(new StringBuilder());
		} 
	}
	
	/**
	 * Redraw/Update panel
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);		
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		
		int startX = START_X;
		int startY = START_Y; 
		
		for(StringBuilder line : _lines){
			g2.drawString(line.toString(), startX, startY);
			startY += LINE_HEIGHT;
		}
	}
}
