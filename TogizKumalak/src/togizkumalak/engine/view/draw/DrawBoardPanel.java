package togizkumalak.engine.view.draw;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import togizkumalak.engine.Cup;
import togizkumalak.engine.CupGroup;
import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.controls.BoardPanel;
import togizkumalak.engine.view.IBoardPanelClickable;
import togizkumalak.engine.view.IDrawCup;
import togizkumalak.engine.view.IDrawCupFactory;

public class DrawBoardPanel extends BoardPanel implements IBoardPanelClickable
{	
	private static final long serialVersionUID = 3474257567248188669L;
	
	private static final int CUP_WIDTH = 43;
	private static final int CUP_HEIGHT = 25;
	private static final int CAPTURED_CUP_WIDTH = 460;
	
	private static final int START_X = 10;
	private static final int CUP_PAD = 10;
	private static final int P1_Y = 70;
	private static final int P2_Y = 120;
	private static final int P1_CAPTURED_Y = 20;
	private static final int P2_CAPTURED_Y = 170;
	
	private IDrawCup _p1CapturedCup;
	private IDrawCup _p2CapturedCup;
	private HashMap<Player, ArrayList<IDrawCup>> _playerCups;
	private IDrawCupFactory _drawCupFactory;

	public DrawBoardPanel(IDrawCupFactory drawCupFactory)
	{
		_drawCupFactory = drawCupFactory;
	}
	
	/**
	 * Click on cup
	 * 
	 * @param player
	 * @param click
	 * 
	 * @param cupIndex
	 */
	public int clickCup(Player player, Point click)
	{
		ArrayList<IDrawCup> pCups = _playerCups.get(player);
		for(int i = 0; i < pCups.size(); ++i){
			if(pCups.get(i).contains(click)){
				return i + 1;
			}
		}
		return 0;		
	}
	
	/**
	 * Hover on cup
	 * 
	 * @param player
	 * @param hover
	 */
	public void hoverCup(Player player, Point hover)
	{
		Cursor cursor = Cursor.getDefaultCursor();
		ArrayList<IDrawCup> pCups = _playerCups.get(player);
		for(IDrawCup gCup : pCups){
			if(gCup.contains(hover)){
				cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				break;
			}
		}
		setCursor(cursor);
	}
	
	/**
	 * Draws game board from scratch
	 * 
	 * @param p1
	 * @param p2
	 * @throws InvalidCupException
	 */
	public void drawFreshBoard(Player p1, Player p2) throws InvalidCupException
	{
		_playerCups = new HashMap<Player, ArrayList<IDrawCup>>();
		
		ArrayList<IDrawCup> p1Cups = new ArrayList<IDrawCup>();
		ArrayList<IDrawCup> p2Cups = new ArrayList<IDrawCup>();
		
		Cup cup;
		CupGroup cupGroup;
		int cupCount;
		int x;
		
		_p1CapturedCup = _drawCupFactory.buildCup(
			p1.countCapturedSeeds(), 
			new Point(START_X, P1_CAPTURED_Y), 
			new Dimension(CAPTURED_CUP_WIDTH, CUP_HEIGHT));
		
		cupGroup = p1.getCups();
		cupCount = cupGroup.count();
		x = START_X + (CUP_WIDTH + CUP_PAD) * (cupCount - 1);
		
		for(int i = 1; i <= cupCount; i++){
			cup = cupGroup.getCup(i);
			IDrawCup gCup = _drawCupFactory.buildCup(
				cup.count(), new Point(x, P1_Y), new Dimension(CUP_WIDTH, CUP_HEIGHT));
			p1Cups.add(gCup);
			x -= CUP_WIDTH + CUP_PAD;
		}
		
		cupGroup = p2.getCups();
	    cupCount = cupGroup.count();
		x = START_X;
		for(int i = 1; i <= cupCount; i++){
			cup = cupGroup.getCup(i);
			IDrawCup gCup = _drawCupFactory.buildCup(
				cup.count(), new Point(x, P2_Y), new Dimension(CUP_WIDTH, CUP_HEIGHT));
			p2Cups.add(gCup);
			x += CUP_WIDTH + CUP_PAD;
		}

		_p2CapturedCup = _drawCupFactory.buildCup(
			p2.countCapturedSeeds(), 
			new Point(START_X, P2_CAPTURED_Y), 
			new Dimension(CAPTURED_CUP_WIDTH, CUP_HEIGHT));
		
		_playerCups.put(p1, p1Cups);
		_playerCups.put(p2, p2Cups);
		
		repaint();
	}

	/**
	 * Update game board
	 * 
	 * @param p1
	 * @param p2
	 * @throws InvalidCupException
	 */
	public void updateBoard(Player p1, Player p2) throws InvalidCupException
	{
		_p1CapturedCup.setSeedCount(p1.countCapturedSeeds());
		_p2CapturedCup.setSeedCount(p2.countCapturedSeeds());
		updateCupGroup(p1.getCups(), _playerCups.get(p1));
		updateCupGroup(p2.getCups(), _playerCups.get(p2));
		repaint();
	}
	
	/**
	 * Helper method update the seed counts in various cups groups
	 * 
	 * @param cGroup
	 * @param gcGroup
	 * @throws InvalidCupException
	 */
	private void updateCupGroup(CupGroup cGroup, ArrayList<IDrawCup> gcGroup) throws InvalidCupException
	{
		Cup cup;
		for(int i = 1; i <= cGroup.count(); i++){
			cup = cGroup.getCup(i);
			gcGroup.get(i-1).setSeedCount(cup.count());
		}		
	}
	
	/**
	 * Draw game board
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		
		_p1CapturedCup.drawCup(g2);
		
		for(ArrayList<IDrawCup> pCups : _playerCups.values()){
			for(IDrawCup gCup : pCups){
				gCup.drawCup(g2);
			}
		}

		_p2CapturedCup.drawCup(g2);
	}
}
