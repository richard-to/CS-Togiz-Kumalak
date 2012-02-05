package togizkumalak.engine.view.clicktext;

import java.awt.Point;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.controls.GameEngine;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.IClickSubscriber;
import togizkumalak.engine.view.IExitSubscriber;
import togizkumalak.engine.view.IHoverSubscriber;
import togizkumalak.engine.view.IKeySubscriber;
import togizkumalak.engine.view.IRestartSubscriber;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.click.ClickHoverAdapter;
import togizkumalak.engine.view.click.ClickMouseAdapter;
import togizkumalak.engine.view.draw.DrawBoardPanel;
import togizkumalak.engine.view.draw.DrawSeedCupFactory;
import togizkumalak.engine.view.listener.ExitListener;
import togizkumalak.engine.view.listener.RestartListener;

public class ClickTextEngine extends GameEngine implements 
	IViewEngine, IClickSubscriber, IHoverSubscriber, 
	IKeySubscriber, IExitSubscriber, IRestartSubscriber
{		
	public ClickTextEngine(ICanvas canvas)
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem restartItem = new JMenuItem("Restart");
		fileMenu.add(restartItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		restartItem.addActionListener(new RestartListener(this));
		exitItem.addActionListener(new ExitListener(this));

		_frame = new ClickTextFrame(
			canvas, 
			new DrawBoardPanel(new DrawSeedCupFactory()),
			menuBar,
			new ClickMouseAdapter(this),
			new ClickHoverAdapter(this),
			new ClickTextKeyAdapter(this));
	}
	
	/**
	 * Updates game state when board hovered
	 * 
	 * @param hover
	 */
	public void hoverAction(Point hover) 
	{
		Player player = _gameBoard.getCurrentPlayer();
		((ClickTextFrame)_frame).hoverCup(player, hover);
	}
	
	/**
	 * Updates game state when board is clicked
	 * 
	 * @param click
	 */
	public void clickAction(Point click) 
	{
		Player player = _gameBoard.getCurrentPlayer();
		String input = Integer.toString(((ClickTextFrame)_frame).clickCup(player, click));

		if(Integer.parseInt(input) > 0){
			try{
				processInput(input);
				recordMove(player, input);
			} catch(InvalidCupException e) {
				_frame.writeStatus(e.getMessage());
			}
		}
	}
	
	/**
	 * Updates game state when key is typed
	 * 
	 * @param event
	 */
	public void keyAction(char key)
	{
		Player player = _gameBoard.getCurrentPlayer();
		int nkey = Character.getNumericValue(key);
		if(Character.isDigit(key) && nkey > 0 && nkey <= TogizKumalak.MAX_CUPS){
			String input = Character.toString(key);
			try{
				processInput(input);
				recordMove(player, input);
			} catch(InvalidCupException e) {
				_frame.writeStatus(e.getMessage());
			}			
		}
	}
	
	/**
	 * Record move to screen
	 * 
	 * @param player
	 * @param input
	 */
	private void recordMove(Player player, String input)
	{
		String moveNum = Integer.toString(_gameBoard.getMoveCount());
		String move = moveNum + ": " + player + " selects cup " + input + ".";
		((ClickTextFrame)_frame).recordMove(move);
	}
	
	/**
	 * Handles restart action
	 */
	public void restartAction()
	{
		_gameBoard.reset();
		((ClickTextFrame)_frame).clearRecord();
		run();
	}
	
	/**
	 * Handles close action
	 */
	public void exitAction()
	{
		System.exit(0);
	}
}
