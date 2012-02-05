package togizkumalak.engine.view.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import togizkumalak.engine.view.IDrawCup;

public class DrawCup implements IDrawCup
{
	private int _seedCount;
	private Point _textPoint;
	private Rectangle _cupRectangle;
	
	/**
	 * Draws cup
	 * 
	 * @param seedCount
	 * @param point
	 * @param dimension
	 */
	public DrawCup(int seedCount, Point point, Dimension dimension)
	{
		_seedCount = seedCount;
		_cupRectangle = new Rectangle(point, dimension);
		_textPoint = new Point(
			_cupRectangle.x + (int)(.25 * _cupRectangle.width),
			_cupRectangle.y + (int)(.6 * _cupRectangle.height));
	}
	
	/**
	 * Contains point
	 * 
	 * @param Point point
	 * 
	 * @return boolean
	 */
	public boolean contains(Point point)
	{
		return _cupRectangle.contains(point);
	}
	
	/**
	 * Set the seed count for cup
	 * 
	 * @param seedCount
	 */
	public void setSeedCount(int seedCount)
	{
		_seedCount = seedCount;
	}
	
	/**
	 * Draw cup
	 * 
	 * @param g2
	 */
	public void drawCup(Graphics2D g2)
	{
		g2.draw(_cupRectangle);
		g2.drawString(Integer.toString(_seedCount), _textPoint.x, _textPoint.y);
	}
}
