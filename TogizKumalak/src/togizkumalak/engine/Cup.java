package togizkumalak.engine;

import java.io.Serializable;
import java.util.ArrayList;

public class Cup implements Serializable
{
	private static final long serialVersionUID = 5865526275042258253L;
	
	private int _startSeeds;
	private ArrayList<Seed> _seeds;

	/**
	 * Constructs empty cup
	 */
	public Cup()
	{
		_startSeeds = 0;
		_seeds = new ArrayList<Seed>();
	}

	/**
	 * Constructs a cup with seeds
	 * 
	 * @param startSeeds
	 */
	public Cup(int startSeeds)
	{
		_startSeeds = startSeeds;
		populateCupWithSeeds(startSeeds);
	}
	
	/**
	 * Refills a cup with specified amount of seeds
	 * 
	 * @param startSeeds
	 */
	private void populateCupWithSeeds(int startSeeds)
	{
		_seeds = new ArrayList<Seed>();		
		for(int i = 0; i < startSeeds; i++){
			_seeds.add(new Seed());	
		}		
	}

	/**
	 * Refills a cup with starting seed amount
	 */
	public void resetCupWithStartingSeeds()
	{
		populateCupWithSeeds(_startSeeds);	
	}
	
	/**
	 * Adds seed to cup
	 * 
	 * @param seed
	 */
	public void add(Seed seed)
	{
		_seeds.add(seed);
	}
	
	/**
	 * Captures seeds
	 * 
	 * @param capturedCup
	 */
	public void capture(Cup capturedCup)
	{
		while(!capturedCup.empty()){
			_seeds.add(capturedCup.remove());
		}
	}
	
	/**
	 * Removes seed from cup
	 * 
	 * @return Seed
	 */
	public Seed remove()
	{
		if(!_seeds.isEmpty()) {
			return _seeds.remove(0);
		}
		return null;
	}
	
	/**
	 * Counts seeds
	 * 
	 * @return integer
	 */
	public int count()
	{
		return _seeds.size();
	}
	
	/**
	 * Empties cup
	 * 
	 * @return boolean
	 */
	public boolean empty()
	{
		return _seeds.isEmpty();
	}
	
	/**
	 * Displays number of seeds in cup
	 * 
	 * @return String
	 */
	public String toString()
	{
		return Integer.toString(_seeds.size());
	}
}
