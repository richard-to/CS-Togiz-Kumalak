package togizkumalak.engine;

import java.util.ArrayList;
import java.util.ListIterator;

public class SeedDistributer
{
	private ArrayList<LinkedCup> _linkedCups;
	
	/**
	 * Initialize seed distributer with the cups 
	 * to be linked in order.
	 * 
	 */
	public SeedDistributer()
	{
		_linkedCups = new ArrayList<LinkedCup>();
	}
	
	/**
	 * Append more cups to linked cups list
	 * 
	 * @param cupGroup
	 */
	public void appendCupsToLinkedCups(CupGroup cupGroup)
	{
		LinkedCup linkedCup = null;
		LinkedCup nextCup = null;
		LinkedCup prevCup = null;
		
		if(_linkedCups.size() > 1) {
			prevCup = _linkedCups.get(_linkedCups.size() -1);
		}
		
		ListIterator<Cup> cupIter = cupGroup.listIteratorReverse();
	    while (cupIter.hasPrevious()){
	    	nextCup = new LinkedCup(cupIter.previous());
	    	_linkedCups.add(nextCup);
			
	    	if(prevCup != null) {
				prevCup.link(nextCup);
			}
	    	prevCup = nextCup;
	    }

		if(_linkedCups.size() > 1) {
			linkedCup = _linkedCups.get(_linkedCups.size() -1);
			nextCup = _linkedCups.get(0);
			linkedCup.link(nextCup);	
		}	
	}
	
	/**
	 * Distribute seeds to the cups in order
	 * 
	 * @param selectedCup The cup that was picked by player
	 * @param handful
	 * 
	 * @return Cup
	 */
	public Cup distribute(Cup selectedCup, Cup handful)
	{
		LinkedCup cup = findFirstCup(selectedCup);
		LinkedCup lastCup = null;
		
		while(handful.count() > 0){
			cup.add(handful.remove());
			lastCup = cup;
			cup = cup.next();
		}
		
		if(lastCup == null) {
			return null;
		} else{
			return lastCup.getCup();
		}
	}
	
	/**
	 * Find the first cup we need to distribute to.
	 * 
	 * @param cup
	 * 
	 * @return LinkedCup
	 * 
	 * @TODO Need to handle case where cup is not found
	 */
	private LinkedCup findFirstCup(Cup cup)
	{
		for(LinkedCup linkedCup : _linkedCups){
			if(linkedCup.containsCup(cup)){
				return linkedCup.next();
			}
		}
		return null;
	}
}
