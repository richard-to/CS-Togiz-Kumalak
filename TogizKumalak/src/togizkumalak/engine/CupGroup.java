package togizkumalak.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class CupGroup implements Iterable<Cup>, Serializable
{
	private static final long serialVersionUID = 3644783693007217267L;
	
	private int _maxCups;
	private ArrayList<Cup> _cups;
	
	/**
	 * Constructs cup group
	 * 
	 * @param maxCups
	 * @param seedsPerCup
	 */
	public CupGroup(int maxCups, int seedsPerCup)
	{
		_maxCups = maxCups;
		_cups = new ArrayList<Cup>();		
		for(int i = 0; i < this._maxCups; i++){
			_cups.add(new Cup(seedsPerCup));	
		}		
	}

	/**
	 * Gets iterator for cups
	 * 
	 * @return Iterator<Cup>
	 */
	public Iterator<Cup> iterator()
	{
		return _cups.iterator();
	}

	/**
	 * Gets listIterator reverse for cups
	 * 
	 * @return ListIterator<Cup>
	 */
	public ListIterator<Cup> listIteratorReverse()
	{
		return _cups.listIterator(_cups.size());
	}
	
	/**
	 * Count number of cups in group
	 * 
	 * @return Integer
	 */
	public int count()
	{
		return _maxCups;
	}

	/**
	 * Check if cup belongs to this cup group
	 * 
	 * @param cup
	 * 
	 * @return boolean
	 */
	public boolean hasCup(Cup cup)
	{
		return (getCupPos(cup) > 0) ? true : false;
	}
	
	/**
	 * Get position of cup
	 * 
	 * @param cup
	 * 
	 * @return Integer
	 */
	public int getCupPos(Cup cup)
	{
		int index = _cups.indexOf(cup);
		if(index != -1) {
			return index + 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Get cup at the specified position
	 * 
	 * @param pos
	 * 
	 * @return Cup
	 * @throws InvalidCupException 
	 * 
	 * @TODO Shouldn't return null here.
	 */
	public Cup getCup(int pos) throws InvalidCupException
	{
		if(pos > 0 && pos <= _maxCups){
			return _cups.get(pos-1);
		} else {
			throw new InvalidCupException();
		}
	}
	
	/**
	 * Are the cups empty
	 * 
	 * @return boolean
	 */
	public boolean emptyCups()
	{
		int total = countTotalSeeds();
		if(total <= 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Count total player seeds
	 * 
	 * @return Integer
	 */
	public int countTotalSeeds()
	{
		int total = 0;
		for (Cup cup : _cups){
			total += cup.count();
		}
		return total;
	}
	
	/**
	 * Remove seeds from all captured cups
	 * 
	 * @return Integer
	 * 
	 * @TODO Implement a strategy class to use different rules
	 */
	public Cup removeSeedsFromCapturedCup(Cup cup)
	{
		int EVEN = 0;
		Cup lostSeedsCup = new Cup();
		
		if(hasCup(cup) && cup.count() % 2 == EVEN){
			lostSeedsCup.capture(cup);
		}
		return lostSeedsCup;
	}
}
