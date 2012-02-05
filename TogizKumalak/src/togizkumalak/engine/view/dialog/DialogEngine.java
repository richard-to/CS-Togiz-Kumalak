package togizkumalak.engine.view.dialog;

import javax.swing.JOptionPane;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.controls.GameEngine;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.text.TextBoardPanel;

public class DialogEngine extends GameEngine implements IViewEngine
{		
	public DialogEngine(ICanvas canvas)
	{
		_frame = new DialogFrame(canvas, new TextBoardPanel());
	}

	/**
	 * Runs view engine
	 */
	public void run()
	{
		super.run();
		
		boolean quitGame = false;
		int selectedCupPos;
		String input;
		boolean validAction;
		
		try {
						
			while(!quitGame) {
				
				validAction = false;
				
				while(!validAction) {
					input  = promptPlayerAction();
					selectedCupPos = validatePlayerAction(input);
					
					if(selectedCupPos != CANCEL_ACTION) {
						processInput(input);
					} else {
						quitGame = true;
						validAction = true;
					}
				}
				
				if(!quitGame && checkGameBoardForWinner()){
					break;
				}
			}
		} catch(InvalidCupException e) {
			_frame.writeStatus(e.getMessage());
		}
	}
		
	/**
	 * Prompt player for action using dialog box
	 * 
	 * @return String
	 */
	private String promptPlayerAction()
	{
		String input = JOptionPane.showInputDialog("Please enter a number from 1 to 9 of the cup you want to play: ");
		return input;
	}
}