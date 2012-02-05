package togizkumalak.engine.view.textbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.controls.GameEngine;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.ITextInputListener;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.text.TextBoardPanel;

public class TextBoxEngine extends GameEngine implements IViewEngine, ActionListener
{	
	public TextBoxEngine(ICanvas canvas)
	{
		_frame = new TextBoxFrame(canvas, new TextBoardPanel(), this);
	}

	/**
	 * Run view engine
	 */
	public void run()
	{
		try{
			drawPlayerTurn();
			drawBoard();
		} catch(InvalidCupException e) {
			_frame.writeError(e.getMessage());
		}
	}

	/**
	 * Updates game state when text is entered into text field
	 * 
	 * @param event
	 */
	public void actionPerformed(ActionEvent event)
	{	
		ITextInputListener textInput = (ITextInputListener)_frame;
		String input = textInput.getText();
		textInput.clearTextField();
		
		try{
			processInput(input);
		} catch(InvalidCupException e) {
			_frame.writeStatus(e.getMessage());
		}
	}	
}