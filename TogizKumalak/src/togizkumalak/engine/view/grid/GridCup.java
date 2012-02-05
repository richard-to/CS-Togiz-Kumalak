package togizkumalak.engine.view.grid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridCup extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public static final String BUTTON_TEXT = "Play Cup";
	
	private JLabel _label;
	private JButton _button;
	private JTextField _textField;
	private int _seedCount;

	private GridCup nextCup;
	
	public GridCup(int seedCount, String label, 
			GridTextKeyAdapter keyAdapter, 
			ActionListener buttonListener)
	{
		_seedCount = seedCount;
		_label = new JLabel(label);
		_button = new JButton(BUTTON_TEXT);
		_textField = new JTextField(Integer.toString(_seedCount), 10);
		_textField.addActionListener(new TextListener());
		_button.addActionListener(buttonListener);
		_button.addKeyListener(keyAdapter);
		_textField.addKeyListener(keyAdapter);
		add(_label);
		add(_button);
		add(_textField);
	}
	
	public boolean contains(JButton button)
	{
		if(_button == button){
			return true;
		} else {
			return false;
		}
	}
	
	public void setNextCup(GridCup gridCup)
	{
		nextCup = gridCup;
	}
	
	public void shiftFocus()
	{
		_textField.requestFocusInWindow();
	}
	
	public void setSeedCount(int seedCount)
	{	
		_seedCount = seedCount;
		_textField.setText(Integer.toString(_seedCount));
	}

	private class TextListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent event)
		{
			nextCup.shiftFocus();
		}
	}
}
