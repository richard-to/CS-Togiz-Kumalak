package togizkumalak.engine.view.draw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.controls.GameEngine;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.ITextInputListener;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.textbox.TextBoxFrame;

public class DrawEngine extends GameEngine implements IViewEngine, ActionListener
{		
	public DrawEngine(ICanvas canvas)
	{
		_frame = new TextBoxFrame(canvas, new DrawBoardPanel(new DrawCupFactory()), this);
	}

	/**
	 * Updates game state when text is entered into text field
	 * 
	 * @param event
	 */
	public void actionPerformed(ActionEvent arg0) 
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
