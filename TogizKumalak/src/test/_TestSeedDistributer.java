package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import togizkumalak.engine.Cup;
import togizkumalak.engine.CupGroup;
import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.SeedDistributer;

public class _TestSeedDistributer 
{
	@Test
	public void TestDistributeSeeds() throws InvalidCupException
	{
		SeedDistributer seedDistributer = new SeedDistributer();
		CupGroup cups = new CupGroup(4,5);
		seedDistributer.appendCupsToLinkedCups(cups);
		Cup handful = new Cup(3);
		seedDistributer.distribute(cups.getCup(3), handful);
		
		assertEquals(0, handful.count());
		assertEquals(6, cups.getCup(1).count());
		assertEquals(5, cups.getCup(3).count());
	}

}
