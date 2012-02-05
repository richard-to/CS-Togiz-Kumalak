package togizkumalak.engine;

import togizkumalak.engine.view.IViewEngineFactory;
import togizkumalak.engine.view.multi.MultiEngine;

public class TogizKumalakMulti 
{
	public static final int START_SEEDS = 9;
	public static final int MAX_CUPS = 9;
	
	private MultiEngine _multiEngine;
	
	public TogizKumalakMulti(IViewEngineFactory viewEngineFactory, String title)
	{
		GameBoardFactory gameBoardFactory = new GameBoardFactory(MAX_CUPS, START_SEEDS);
		viewEngineFactory.setGameBoardFactory(gameBoardFactory);
		_multiEngine = new MultiEngine(viewEngineFactory, title);
	}

	public TogizKumalakMulti(IViewEngineFactory viewEngineFactory, String title, int maxCups, int startSeeds)
	{
		GameBoardFactory gameBoardFactory = new GameBoardFactory(maxCups, startSeeds);
		viewEngineFactory.setGameBoardFactory(gameBoardFactory);
		_multiEngine = new MultiEngine(viewEngineFactory, title);
	}
	
	public void run()
	{
		_multiEngine.run();
	}
}
