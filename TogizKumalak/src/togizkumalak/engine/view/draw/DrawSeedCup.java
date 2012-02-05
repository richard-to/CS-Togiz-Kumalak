package togizkumalak.engine.view.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import togizkumalak.engine.view.IDrawCup;

public class DrawSeedCup implements IDrawCup
{
	private static final int BOX_PAD = 2;
	
	private int _seedCount;
	private Rectangle _cupRectangle;
	
	/**
	 * Constructor
	 * 
	 * @param seedCount
	 * @param point
	 * @param dimension
	 */
	public DrawSeedCup(int seedCount, Point point, Dimension dimension)
	{
		_seedCount = seedCount;
		_cupRectangle = new Rectangle(point, dimension);
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
	 * Draw cup with seeds
	 * 
	 * @param g2
	 */
	public void drawCup(Graphics2D g2)
	{
		g2.draw(_cupRectangle);

		DrawSeed seed;
		
		int row0 = _cupRectangle.y + BOX_PAD;
		int row1 = row0 + BOX_PAD + DrawSeed.DIAMETER;
		int currentRow = 0;
		
		int colX = _cupRectangle.x + BOX_PAD;
		int rowY = row0;
		
		for(int i = 0; i < _seedCount; i++){
			seed = new DrawSeed();
			seed.drawSeed(g2, colX, rowY);
			
			currentRow = 1 - currentRow;
			if(currentRow == 1){
				rowY = row1;
			} else {
				rowY = row0;
				colX = colX + BOX_PAD + seed.getDiameter();
			}
		}	
	}
}
