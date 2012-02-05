package togizkumalak.engine.view.grid;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridCapturedCup extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel _label;
	private JTextField _textField;
	private int _seedCount;
	
	public GridCapturedCup(int seedCount, String label, GridTextKeyAdapter keyAdapter)
	{
		_seedCount = seedCount;
		_label = new JLabel(label);
		_textField = new JTextField(Integer.toString(_seedCount), 10);
		_textField.addKeyListener(keyAdapter);
		add(_label);
		add(_textField);		
	}

	public void setSeedCount(int seedCount)
	{	
		_seedCount = seedCount;
		_textField.setText(Integer.toString(_seedCount));
	}	
}
