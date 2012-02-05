package togizkumalak.engine.view.textbox;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import togizkumalak.engine.controls.BoardPanel;
import togizkumalak.engine.controls.GameFrame;
import togizkumalak.engine.controls.DrawTextPanel;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.ITextInputListener;

public class TextBoxFrame extends GameFrame implements ITextInputListener
{
	private static final long serialVersionUID = 172529339589738830L;
	
	private static final int TEXTFIELD_SIZE = 25;
	private static final int TEXTFIELD_BOX_HEIGHT = 50;
	private static final int STATUS_TEXT_HEIGHT = 35;
	
	private JPanel _textFieldPanel;
	private JTextField _textField;
	
	public TextBoxFrame(
		ICanvas canvas, BoardPanel boardPanel, ActionListener actionListener)
	{
		super(canvas);
		
		_textField = new JTextField(null, TEXTFIELD_SIZE);
		_textField.addActionListener(actionListener);
		_textFieldPanel = new JPanel();
		_textFieldPanel.add(_textField);
		
		_boardPanel = boardPanel;
		_statusPanel = new DrawTextPanel(false);
		_errorPanel = new DrawTextPanel(false);
		
		Dimension textFieldDimension = new Dimension(GameFrame.FRAME_WIDTH, TEXTFIELD_BOX_HEIGHT);
		_textFieldPanel.setPreferredSize(textFieldDimension);
		_textFieldPanel.setMaximumSize(textFieldDimension);
		
		Dimension statusTextDimension = new Dimension(GameFrame.FRAME_WIDTH, STATUS_TEXT_HEIGHT);
		_statusPanel.setPreferredSize(statusTextDimension);
		_statusPanel.setMaximumSize(statusTextDimension);
		_errorPanel.setPreferredSize(statusTextDimension);
		_errorPanel.setMaximumSize(statusTextDimension);
		
		Container contentPane = canvas.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		contentPane.add(_textFieldPanel);
		contentPane.add(_statusPanel);
		contentPane.add(_boardPanel);
		contentPane.add(_errorPanel);
	}
	
	/**
	 * Get text from text field
	 * 
	 * @return String
	 */
	public String getText()
	{
		return _textField.getText();
	}

	/**
	 * Clear text from text field
	 */
	public void clearTextField()
	{
		_textField.setText("");
	}
}
