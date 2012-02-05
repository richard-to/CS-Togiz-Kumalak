package togizkumalak.engine;

import java.io.Serializable;

public class Player implements Serializable
{
	private static final long serialVersionUID = 6735247234237319440L;
	
	private String _name;
	private CupGroup _cups;
	private Cup _capturedCup;
	
	/**
	 * Initializes an empty cup to hold captured seeds
	 * and create a name for the player and cup group
	 * 
	 * @param name
	 * @param cups
	 */
	public Player(String name, CupGroup cups)
	{
		_capturedCup = new Cup(0);
		_name = name;
		_cups = cups;
	}
	
	/**
	 * Gets the player's set of cups
	 * 
	 * @return PlayerCupSet
	 */
	public CupGroup getCups()
	{
		return _cups;
	}

	/**
	 * Displays player's name
	 * 
	 * @return String
	 */
	public String toString()
	{
		return _name;
	}

	/**
	 * Select cup
	 * 
	 * @param po
	 * 
	 * @return Cup
	 */
	public Cup selectCup(int pos) throws InvalidCupException
	{
		return _cups.getCup(pos);		
	}

	/**
	 * Gets all seeds from the selected cup
	 * 
	 * @param Cup
	 * 
	 * @return Cup
	 */
	public Cup grabHandful(Cup selectedCup)
	{
		Cup handful = new Cup();
		handful.capture(selectedCup);
		return handful;		
	}
	
	/**
	 * Give captured seeds to opponent
	 * 
	 * @param player
	 * @param capturedCup
	 */
	public void giveCapturedSeedsToOpponent(Player player, Cup capturedCup)
	{
		Cup lostSeeds = _cups.removeSeedsFromCapturedCup(capturedCup);
		player._capturedCup.capture(lostSeeds);
	}
	
	/**
	 * Counts amount of captured seeds
	 * 
	 * @return Integer
	 */
	public int countCapturedSeeds()
	{
		return _capturedCup.count();
	}

	/**
	 * Counts total seeds
	 * 
	 * @return Integer
	 */
	public int countTotalSeeds()
	{
		int totalSeeds = _cups.countTotalSeeds();
		totalSeeds += _capturedCup.count();
		return totalSeeds;
	}
	
	/**
	 * Checks if all of the player's cups are empty
	 * 
	 * @return boolean
	 */
	public boolean allCupsEmpty()
	{
		return _cups.emptyCups();
	}
}
