package togizkumalak.engine.view.click;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BoxLayout;

import togizkumalak.engine.Player;
import togizkumalak.engine.controls.DrawTextPanel;
import togizkumalak.engine.controls.GameFrame;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.draw.DrawBoardPanel;

public class ClickFrame extends GameFrame 
{
	private static final long serialVersionUID = 2862611395803962804L;
	private static final int STATUS_TEXT_HEIGHT = 35;
	
	public ClickFrame(ICanvas canvas, DrawBoardPanel boardPanel, 
		MouseAdapter mouseAdapter, MouseMotionAdapter mMotionAdapter)
	{
		super(canvas);
		_boardPanel = boardPanel;
		_statusPanel = new DrawTextPanel(false);
		_errorPanel = new DrawTextPanel(false);
		
		boardPanel.addMouseListener(mouseAdapter);
		boardPanel.addMouseMotionListener(mMotionAdapter);
		
		Dimension statusTextDimension = new Dimension(GameFrame.FRAME_WIDTH, STATUS_TEXT_HEIGHT);
		_statusPanel.setPreferredSize(statusTextDimension);
		_statusPanel.setMaximumSize(statusTextDimension);
		_errorPanel.setPreferredSize(statusTextDimension);
		_errorPanel.setMaximumSize(statusTextDimension);
		
		Container contentPane = canvas.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.add(_statusPanel);
		contentPane.add(_boardPanel);
		contentPane.add(_errorPanel);
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
}
