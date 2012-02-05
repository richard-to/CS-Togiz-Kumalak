package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import togizkumalak.engine.Cup;
import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.CupGroup;

public class _TestPlayer 
{
	
	@Test
	public void testConstructor() throws InvalidCupException
	{
		CupGroup pCupSet = new CupGroup(9, 9);
		Player p1 = new Player("Player 1", pCupSet);
		
		assertEquals("Player 1", p1.toString());
		assertEquals(0, p1.countCapturedSeeds());
	
		assertEquals(81, p1.countTotalSeeds());
		assertEquals(false, p1.allCupsEmpty());	
	}

	@Test
	public void testTotalSeeds()
	{
		CupGroup pCupSet = new CupGroup(9, 9);
		Player p1 = new Player("Player 1", pCupSet);
		assertEquals(81, p1.countTotalSeeds());
	}

	@Test
	public void testAllCupsEmpty()
	{
		CupGroup pCupSet = new CupGroup(9, 9);
		Player p1 = new Player("Player 1", pCupSet);
		assertEquals(false, p1.allCupsEmpty());
	}

	@Test
	public void testGrabHandful()
	{
		CupGroup pCupSet = new CupGroup(9, 9);
		Player p1 = new Player("Player 1", pCupSet);
		try {
			Cup handful = p1.grabHandful(pCupSet.getCup(2));
			assertEquals(9, handful.count());
			assertEquals(0, p1.getCups().getCup(2).count());
		} catch (InvalidCupException e) {
			
		}
	}
}
