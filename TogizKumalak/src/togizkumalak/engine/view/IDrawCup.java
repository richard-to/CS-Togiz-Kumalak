package togizkumalak.engine.view;

import java.awt.Graphics2D;
import java.awt.Point;

public interface IDrawCup 
{
	void setSeedCount(int seedCount);
	void drawCup(Graphics2D g2);
	boolean contains(Point point);
}
