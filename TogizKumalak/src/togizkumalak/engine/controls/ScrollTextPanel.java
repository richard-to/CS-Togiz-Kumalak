package togizkumalak.engine.controls;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollTextPanel extends TextPanel
{
	private static final long serialVersionUID = 8188468320917337555L;
	
	private static final int TEXTAREA_COLS = 36;
	private static final int TEXTAREA_ROWS = 8;
	private static final String BLANK = "";
	private static final String NEWLINE = "\n";
	
	private JTextArea _textArea;
	private JScrollPane _scrollPane;
	
	public ScrollTextPanel()
	{
		_textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLS);
		_textArea.setEditable(false);

		_scrollPane = new JScrollPane(
			_textArea,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(_scrollPane);
	}

	/**
	 * Clear text on panel
	 */
	public void clear()
	{
		_textArea.setText(BLANK);
	}
	
	/**
	 * Append text on panel
	 * 
	 * @param text
	 */
	public void write(String text)
	{
		_textArea.append(text);
	}
	
	/**
	 * Gets text
	 */
	public String getText()
	{
		return _textArea.getText();
	}
	
	/**
	 * Append text on panel and add new line
	 * 
	 * @param text
	 */
	public void writeln(String text)
	{
		_textArea.append(text + NEWLINE);
	}

	/**
	 * Add new line
	 * 
	 */
	public void writeln()
	{
		_textArea.append(NEWLINE);
	}
}
