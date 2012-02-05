package togizkumalak.engine.view.click;

import java.awt.Point;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.controls.GameEngine;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.IClickSubscriber;
import togizkumalak.engine.view.IHoverSubscriber;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.draw.DrawBoardPanel;
import togizkumalak.engine.view.draw.DrawSeedCupFactory;

public class ClickEngine extends GameEngine implements IViewEngine, IClickSubscriber, IHoverSubscriber
{		
	public ClickEngine(ICanvas canvas)
	{
		_frame = new ClickFrame(
			canvas, 
			new DrawBoardPanel(new DrawSeedCupFactory()), 
			new ClickMouseAdapter(this),
			new ClickHoverAdapter(this));
	}

	/**
	 * Updates game state when board hovered
	 * 
	 * @param hover
	 */
	public void hoverAction(Point hover) 
	{
		Player player = _gameBoard.getCurrentPlayer();
		((ClickFrame)_frame).hoverCup(player, hover);
	}
	
	/**
	 * Updates game state when board is clicked
	 * 
	 * @param click
	 */
	public void clickAction(Point click) 
	{
		Player player = _gameBoard.getCurrentPlayer();
		String input = Integer.toString(((ClickFrame)_frame).clickCup(player, click));
		
		if(Integer.parseInt(input) > 0){
			try{
				processInput(input);
			} catch(InvalidCupException e) {
				_frame.writeStatus(e.getMessage());
			}
		}
	}
}
