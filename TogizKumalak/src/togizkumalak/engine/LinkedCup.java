package togizkumalak.engine;

public class LinkedCup 
{
	private Cup _cup;
	private LinkedCup _nextCup;
	
	public LinkedCup(Cup cup)
	{
		_cup = cup;
	}
	
	/**
	 * Contains cup
	 * 
	 * @param cup
	 * 
	 * @return boolean
	 */
	public boolean containsCup(Cup cup)
	{
		return (_cup == cup);
	}
	
	/**
	 * Get cup
	 * 
	 * @return Cup
	 */
	public Cup getCup()
	{
		return _cup;
	}
	
	/**
	 * Add seed to cup
	 * 
	 * @param seed
	 */
	public void add(Seed seed)
	{
		_cup.add(seed);
	}

	/**
	 * Set the cup that this cup will be linked to
	 * 
	 * @param nextCup
	 */
	public void link(LinkedCup nextCup)
	{
		_nextCup = nextCup;
	}
	
	/**
	 * Get next linked cup
	 * 
	 * @return LinkedCup
	 * 
	 * @TODO Throw exception if not set?
	 */
	public LinkedCup next()
	{
		return _nextCup;
	}
}
