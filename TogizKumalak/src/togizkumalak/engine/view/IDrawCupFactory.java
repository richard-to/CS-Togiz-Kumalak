package togizkumalak.engine.view;

import java.awt.Dimension;
import java.awt.Point;

public interface IDrawCupFactory
{
	IDrawCup buildCup(int seedCount, Point point, Dimension dimension);
}
