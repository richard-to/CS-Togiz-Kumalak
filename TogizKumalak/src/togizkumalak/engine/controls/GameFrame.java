package togizkumalak.engine.controls;

import java.awt.Dimension;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.view.ICanvas;

public class GameFrame
{
	public static final int FRAME_HEIGHT = 500;
	public static final int FRAME_WIDTH = 500;
	
	protected BoardPanel _boardPanel;
	protected TextPanel _statusPanel;
	protected TextPanel _errorPanel;
	protected ICanvas _canvas;
	
	public GameFrame(ICanvas canvas)
	{
		_canvas = canvas;
		_canvas.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
	}

	public GameFrame(ICanvas canvas, Dimension dimension)
	{
		_canvas = canvas;
		_canvas.setSize(dimension);
	}
		
	/**
	 * Write game status text
	 * 
	 * @param text
	 */
	public void writeStatus(String text)
	{
		_statusPanel.clear();
		_statusPanel.writeln(text);
	}
	
	/**
	 * Get game error text
	 * 
	 * @param text
	 */
	public void writeError(String text)
	{
		_errorPanel.clear();
		_errorPanel.writeln(text);
	}
	
	/**
	 * Wrapper method to draw fresh game board
	 * 
	 * @param p1
	 * @param p2
	 */
	public void drawFreshBoard(Player p1, Player p2)
	{
		try {
			_boardPanel.drawFreshBoard(p1, p2);
		} catch(InvalidCupException e){
			
		}
	}

	/**
	 * Wrapper method to update game board
	 * 
	 * @param p1
	 * @param p2
	 */
	public void updateBoard(Player p1, Player p2)
	{
		try {
			_boardPanel.updateBoard(p1, p2);
		} catch(InvalidCupException e){
			
		}
	}
	
	/**
	 * Clear panel
	 * 
	 * @param text
	 */
	public void clear()
	{
		_statusPanel.clear();
		_errorPanel.clear();
	}
}
