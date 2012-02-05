package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import togizkumalak.engine.Cup;
import togizkumalak.engine.Seed;

public class _TestCup 
{
	@Test
	public void testConstructor() 
	{
		Cup cup = new Cup(9);
		assertEquals(9, cup.count());
	}

	@Test
	public void testSeedRemove() 
	{
		Cup cup = new Cup(9);
		cup.remove();
		assertEquals(8, cup.count());
	}

	@Test
	public void testSeedAdd() 
	{
		Cup cup = new Cup(9);
		cup.add(new Seed());
		assertEquals(10, cup.count());
	}

	@Test
	public void testSeedEmpty() 
	{
		Cup cup = new Cup(9);
		assertEquals(false, cup.empty());;
	}

	@Test
	public void testResetCupsWithStartingSeeds() 
	{
		Cup cup = new Cup(9);
		cup.add(new Seed());
		cup.resetCupWithStartingSeeds();		
		assertEquals("9", cup.toString());
	}
	
	@Test 
	public void testCupCapture()
	{
		Cup cup = new Cup(9);
		Cup capturedCup = new Cup(9);
		
		cup.capture(capturedCup);
		
		assertEquals(18, cup.count());
		assertEquals(0, capturedCup.count());
		assertEquals(true, capturedCup.empty());
		assertEquals(null, capturedCup.remove());		
	}
}
