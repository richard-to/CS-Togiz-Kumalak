package togizkumalak.engine.view.draw;

import java.awt.Dimension;
import java.awt.Point;

import togizkumalak.engine.view.IDrawCup;
import togizkumalak.engine.view.IDrawCupFactory;

public class DrawCupFactory implements IDrawCupFactory
{
	public IDrawCup buildCup(int seedCount, Point point, Dimension dimension)
	{
		return new DrawCup(seedCount, point, dimension);
	}
}
