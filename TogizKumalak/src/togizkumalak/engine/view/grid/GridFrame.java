package togizkumalak.engine.view.grid;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;

import togizkumalak.engine.Player;
import togizkumalak.engine.controls.DrawTextPanel;
import togizkumalak.engine.controls.GameFrame;
import togizkumalak.engine.controls.ScrollTextPanel;
import togizkumalak.engine.controls.TextPanel;
import togizkumalak.engine.view.ICanvas;

public class GridFrame extends GameFrame 
{
	public static final int FRAME_HEIGHT = 700;
	public static final int FRAME_WIDTH = 700;
	
	private static final int STATUS_TEXT_HEIGHT = 35;
	private static final int RECORD_TEXT_HEIGHT = 150;
	
	private TextPanel _recordPanel;
	
	public GridFrame(ICanvas canvas, GridBoardPanel boardPanel, JMenuBar menu,
		KeyAdapter keyAdapter)
	{
		super(canvas, new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		canvas.setJMenuBar(menu);
		
		canvas.addKeyListener(keyAdapter);
		
		_boardPanel = boardPanel;
		_statusPanel = new DrawTextPanel(false);
		_errorPanel = new DrawTextPanel(false);
		_recordPanel = new ScrollTextPanel();
				
		Dimension recordTextDimension = new Dimension(GameFrame.FRAME_WIDTH, RECORD_TEXT_HEIGHT);
		_recordPanel.setPreferredSize(recordTextDimension);
		_recordPanel.setMaximumSize(recordTextDimension);
		
		Dimension statusTextDimension = new Dimension(GameFrame.FRAME_WIDTH, STATUS_TEXT_HEIGHT);
		_statusPanel.setPreferredSize(statusTextDimension);
		_statusPanel.setMaximumSize(statusTextDimension);
		_errorPanel.setPreferredSize(statusTextDimension);
		_errorPanel.setMaximumSize(statusTextDimension);
		
		Container contentPane = canvas.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.add(_statusPanel);
		contentPane.add(_boardPanel);
		contentPane.add(_recordPanel);
		contentPane.add(_errorPanel);
		
		canvas.setFocusable(true);
	}


	/**
	 * Check if a cup was clicked on
	 * 
	 * @param player
	 * @param button
	 */
	public int clickCup(Player player, JButton button)
	{
		return ((GridBoardPanel)_boardPanel).clickCup(player, button);
	}
	
	/**
	 * Gets recorded moves
	 * 
	 * @param move
	 */
	public String getRecordedMoves()
	{
		return _recordPanel.getText();
	}

	/**
	 * Load recorded moves
	 * 
	 * @param move
	 */
	public void loadRecordedMoves(String moves)
	{
		_recordPanel.write(moves);
	}
	
	/**
	 * Record moves
	 * 
	 * @param move
	 */
	public void recordMove(String move)
	{
		_recordPanel.writeln(move);
	}
	
	/**
	 * Clear record
	 */
	public void clearRecord()
	{
		_recordPanel.clear();
	}
}
