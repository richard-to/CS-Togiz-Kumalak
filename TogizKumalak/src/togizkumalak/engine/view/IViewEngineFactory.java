package togizkumalak.engine.view;

import togizkumalak.engine.GameBoardFactory;

public interface IViewEngineFactory 
{
	public IViewEngine create();
	public void setGameBoardFactory(GameBoardFactory gameBoardFactory);
}
