package togizkumalak.engine.view.dialog;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;

import togizkumalak.engine.controls.BoardPanel;
import togizkumalak.engine.controls.DrawTextPanel;
import togizkumalak.engine.controls.GameFrame;
import togizkumalak.engine.view.ICanvas;

public class DialogFrame extends GameFrame 
{
	private static final long serialVersionUID = -4841886816490845626L;
	private static final int STATUS_TEXT_HEIGHT = 35;
	
	public DialogFrame(ICanvas canvas, BoardPanel boardPanel)
	{
		super(canvas);
		_boardPanel = boardPanel;
		_statusPanel = new DrawTextPanel(false);
		_errorPanel = new DrawTextPanel(false);
		
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
}
