package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import togizkumalak.engine.Cup;
import togizkumalak.engine.CupGroup;
import togizkumalak.engine.InvalidCupException;

public class _TestCupGroup 
{
	
	@Test
	public void testCount()
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		assertEquals(9, cupGroup.count());
	}

	@Test
	public void testGetCup() throws InvalidCupException
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		Cup cup = cupGroup.getCup(1);
		assertEquals(9, cup.count());
	}

	@Test
	public void testGetCupPos() throws InvalidCupException
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		Cup cup = cupGroup.getCup(1);
		assertEquals(1, cupGroup.getCupPos(cup));
	}

	@Test
	public void testGetCupPosInvalid()
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		Cup cup = new Cup(3);
		assertEquals(0, cupGroup.getCupPos(cup));
	}

	@Test
	public void testHasCup() throws InvalidCupException
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		Cup cup = cupGroup.getCup(1);
		assertEquals(true, cupGroup.hasCup(cup));
	}

	@Test
	public void testHasCupInvalid()
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		Cup cup = new Cup(2);
		assertEquals(false, cupGroup.hasCup(cup));
	}
	
	@Test
	public void testEmptyCups()
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		assertEquals(false, cupGroup.emptyCups());
	}

	@Test
	public void testCountTotalSeeds()
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		assertEquals(81, cupGroup.countTotalSeeds());
	}

	@Test
	public void testRemoveSeedsFromCapturedCup() throws InvalidCupException
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		Cup cup = cupGroup.getCup(1);
		Cup capturedSeeds = cupGroup.removeSeedsFromCapturedCup(cup);
		assertEquals(0, cup.count());
		assertEquals(9, capturedSeeds.count());
	}

	@Test
	public void testRemoveSeedsFromCapturedCupEvenSeeds() throws InvalidCupException
	{
		CupGroup cupGroup = new CupGroup(9, 10);
		Cup cup = cupGroup.getCup(1);
		Cup capturedSeeds = cupGroup.removeSeedsFromCapturedCup(cup);
		assertEquals(10, cup.count());
		assertEquals(0, capturedSeeds.count());
	}
	
	@Test
	public void testRemoveSeedsFromCapturedCupInvalid()
	{
		CupGroup cupGroup = new CupGroup(9, 9);
		Cup cup = new Cup(3);
		Cup capturedSeeds = cupGroup.removeSeedsFromCapturedCup(cup);
		assertEquals(3, cup.count());
		assertEquals(0, capturedSeeds.count());
	}
}
