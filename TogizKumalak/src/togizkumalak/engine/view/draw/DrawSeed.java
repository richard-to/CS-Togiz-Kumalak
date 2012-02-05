package togizkumalak.engine.view.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

class DrawSeed
{
	public static final int DIAMETER = 4;

	public DrawSeed(){}

	public int getDiameter()
	{
		return DIAMETER;
	}

	public void drawSeed(Graphics2D g2, int xCoord, int yCoord)
	{
		Ellipse2D.Double dot = new Ellipse2D.Double(xCoord, yCoord, DIAMETER, DIAMETER);
		g2.fill(dot);
		g2.setColor(Color.BLACK);
	}
}