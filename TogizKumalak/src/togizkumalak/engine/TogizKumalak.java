package togizkumalak.engine;

import togizkumalak.engine.view.IViewEngine;

public class TogizKumalak 
{
	public static final int START_SEEDS = 9;
	public static final int MAX_CUPS = 9;
	
	private IViewEngine _viewEngine;
	
	public TogizKumalak(IViewEngine viewEngine)
	{
		GameBoard gameBoard = new GameBoard(MAX_CUPS, START_SEEDS);
		
		viewEngine.init(gameBoard);
		_viewEngine = viewEngine;
	}
	
	public void run()
	{
		_viewEngine.run();
	}
}
