package togizkumalak.engine.view.draw;

import java.awt.Dimension;
import java.awt.Point;

import togizkumalak.engine.view.IDrawCup;
import togizkumalak.engine.view.IDrawCupFactory;

public class DrawSeedCupFactory implements IDrawCupFactory
{
	public IDrawCup buildCup(int seedCount, Point point, Dimension dimension)
	{
		return new DrawSeedCup(seedCount, point, dimension);
	}
}
