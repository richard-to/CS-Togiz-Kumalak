package togizkumalak.engine.controls;

import togizkumalak.engine.GameBoardFactory;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.IViewEngineFactory;
import togizkumalak.engine.view.save.SaveEngine;

public class SaveEngineFactory implements IViewEngineFactory
{
	private GameBoardFactory _gameBoardFactory;
	private String _title;
	
	public SaveEngineFactory(String title)
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
		SaveEngine saveEngine = new SaveEngine(canvas);
		saveEngine.init(_gameBoardFactory.create());
		return saveEngine;
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
