package togizkumalak.engine;

public class GameBoardFactory 
{
	private int _maxSeeds;
	private int _maxCups;
	
	public GameBoardFactory(int maxCups, int maxSeeds)
	{
		_maxCups = maxCups;		
		_maxSeeds = maxSeeds;
	}
	
	/**
	 * Creates a preconfigured Game board
	 * 
	 * @return gameboard
	 */
	public GameBoard create()
	{
		return new GameBoard(_maxCups, _maxSeeds);
	}
}
