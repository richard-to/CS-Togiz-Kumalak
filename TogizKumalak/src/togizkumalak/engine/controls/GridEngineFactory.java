package togizkumalak.engine.controls;

import togizkumalak.engine.GameBoardFactory;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.IViewEngineFactory;
import togizkumalak.engine.view.grid.GridEngine;

public class GridEngineFactory implements IViewEngineFactory
{
	private GameBoardFactory _gameBoardFactory;
	private String _title;
	
	public GridEngineFactory(String title)
	{
		_title = title;
	}
	
	/**
	 * Creates an instance of the SaveEngine
	 * 
	 * @return SaveEngine
	 */
	public IViewEngine create()
	{
		FrameCanvas canvas = new FrameCanvas(_title);
		GridEngine gridEngine = new GridEngine(canvas);
		gridEngine.init(_gameBoardFactory.create());
		return gridEngine;
	}
	
	/**
	 * Set game board factory.
	 * 
	 * @param gameBoardFactory
	 */
	public void setGameBoardFactory(GameBoardFactory gameBoardFactory)
	{
		_gameBoardFactory = gameBoardFactory;
	}
}
