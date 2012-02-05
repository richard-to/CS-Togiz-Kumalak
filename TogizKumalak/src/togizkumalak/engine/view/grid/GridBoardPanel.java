package togizkumalak.engine.view.grid;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import togizkumalak.engine.Cup;
import togizkumalak.engine.CupGroup;
import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.controls.BoardPanel;
import togizkumalak.engine.view.IBoardPanel;

public class GridBoardPanel extends BoardPanel implements IBoardPanel
{	
	private static final long serialVersionUID = 1L;
	
	private static final int COLS = 2;
	
	private GridCapturedCup _p1CapturedCup;
	private GridCapturedCup _p2CapturedCup;
	private HashMap<Player, ArrayList<GridCup>> _playerCups;
	
	GridTextKeyAdapter _keyAdapter;
	ActionListener _playBtnListener;
	JPanel _board;
	
	public GridBoardPanel(GridTextKeyAdapter keyAdapter, ActionListener playBtnListener)
	{
		_keyAdapter = keyAdapter;
		_playBtnListener = playBtnListener;
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
		if(_board != null){
			remove(_board);
		}
		_board = new JPanel();
		_playerCups = new HashMap<Player, ArrayList<GridCup>>();
		
		ArrayList<GridCup> p1Cups = new ArrayList<GridCup>();
		ArrayList<GridCup> p2Cups = new ArrayList<GridCup>();
		
		Cup cup;
		CupGroup cupGroup;
		int cupCount;

		Cup cup2;
		CupGroup cupGroup2;
		
		cupGroup = p1.getCups();
		cupCount = cupGroup.count();

		cupGroup2 = p2.getCups();

		_board.setLayout(new GridLayout(cupCount+1, COLS));	
		
		_p1CapturedCup = new GridCapturedCup(p1.countCapturedSeeds(), p1.toString() + ", Captured Cup", _keyAdapter);
		_p2CapturedCup = new GridCapturedCup(p2.countCapturedSeeds(), p2.toString() + ", Captured Cup", _keyAdapter);
		
		_board.add(_p1CapturedCup);
		_board.add(_p2CapturedCup);
		
		for(int i = 1; i <= cupCount; i++){
			cup = cupGroup.getCup(i);
			GridCup gCup = new GridCup(cup.count(), p1.toString() + ", Cup " + i,
				_keyAdapter, _playBtnListener);
			p1Cups.add(gCup);
			
			cup2 = cupGroup2.getCup(i);
			GridCup gCup2 = new GridCup(cup2.count(), p2.toString() + ", Cup " + i, 
				_keyAdapter, _playBtnListener);
			p2Cups.add(gCup2);	
		}

		for(int i = 0; i < cupCount; i++){
			GridCup gCup = p1Cups.get(i);
			
			if(i+1 == cupCount){
				gCup.setNextCup(p2Cups.get(0));				
			} else {
				gCup.setNextCup(p1Cups.get(i+1));
			}
			
			_board.add(gCup);
			
			GridCup gCup2 = p2Cups.get(cupCount - 1 - i);

			if(cupCount - i == cupCount){
				gCup2.setNextCup(p1Cups.get(0));				
			} else {
				gCup2.setNextCup(p2Cups.get(cupCount - i));
			}
			
			_board.add(gCup2);
		}
		
		_playerCups.put(p1, p1Cups);
		_playerCups.put(p2, p2Cups);
		
		add(_board);
		revalidate();
		repaint();
	}

	public int clickCup(Player player, JButton button)
	{
		ArrayList<GridCup> pCups = _playerCups.get(player);
		for(int i = 0; i < pCups.size(); ++i){
			if(pCups.get(i).contains(button)){
				return i + 1;
			}
		}
		return 0;			
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
	}
	
	/**
	 * Helper method update the seed counts in various cups groups
	 * 
	 * @param cGroup
	 * @param gcGroup
	 * @throws InvalidCupException
	 */
	private void updateCupGroup(CupGroup cGroup, ArrayList<GridCup> gcGroup) throws InvalidCupException
	{
		Cup cup;
		for(int i = 1; i <= cGroup.count(); i++){
			cup = cGroup.getCup(i);
			gcGroup.get(i-1).setSeedCount(cup.count());
		}		
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
	}
}
