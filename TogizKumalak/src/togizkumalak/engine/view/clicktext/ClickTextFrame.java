package togizkumalak.engine.view.clicktext;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BoxLayout;
import javax.swing.JMenuBar;

import togizkumalak.engine.Player;
import togizkumalak.engine.controls.DrawTextPanel;
import togizkumalak.engine.controls.GameFrame;
import togizkumalak.engine.controls.ScrollTextPanel;
import togizkumalak.engine.controls.TextPanel;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.draw.DrawBoardPanel;

public class ClickTextFrame extends GameFrame 
{
	private static final long serialVersionUID = 4881703698743980711L;
	private static final int STATUS_TEXT_HEIGHT = 35;
	private static final int RECORD_TEXT_HEIGHT = 150;
	
	private TextPanel _recordPanel;
	
	public ClickTextFrame(ICanvas canvas, DrawBoardPanel boardPanel, JMenuBar menu, 
		MouseAdapter mouseAdapter, MouseMotionAdapter mMotionAdapter,
		KeyAdapter keyAdapter)
	{
		super(canvas);
		
		canvas.setJMenuBar(menu);
		
		canvas.addKeyListener(keyAdapter);
		
		_boardPanel = boardPanel;
		_statusPanel = new DrawTextPanel(false);
		_errorPanel = new DrawTextPanel(false);
		_recordPanel = new ScrollTextPanel();
		
		boardPanel.addMouseListener(mouseAdapter);
		boardPanel.addMouseMotionListener(mMotionAdapter);
		
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
	 * @param click
	 */
	public int clickCup(Player player, Point click)
	{
		return ((DrawBoardPanel)_boardPanel).clickCup(player, click);
	}
	
	/**
	 * Wrapper for boardPanel hover cup
	 *
	 * @param player
	 * @param hover
	 */
	public void hoverCup(Player player, Point hover)
	{
		((DrawBoardPanel)_boardPanel).hoverCup(player, hover);
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
