package togizkumalak.engine.view.text;

import java.awt.Graphics;
import java.awt.Graphics2D;

import togizkumalak.engine.Cup;
import togizkumalak.engine.CupGroup;
import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.controls.BoardPanel;
import togizkumalak.engine.controls.DrawTextPanel;
import togizkumalak.engine.view.IBoardPanel;

public class TextBoardPanel extends BoardPanel implements IBoardPanel
{
	private static final long serialVersionUID = 1L;
	
	private DrawTextPanel _textBoardPanel;
	
	public TextBoardPanel()
	{
		_textBoardPanel = new DrawTextPanel();
		add(_textBoardPanel);
	}
	
	/**
	 * Draws game board from scratch. In this case,
	 * it is the same as updateBoard
	 * 
	 * @param p1
	 * @param p2
	 * @throws InvalidCupException
	 */
	public void drawFreshBoard(Player p1, Player p2) throws InvalidCupException
	{
		drawBoard(p1, p2);		
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
		drawBoard(p1, p2);
		repaint();
	}
	
	/**
	 * Helper method update the seed counts in various cups groups
	 * 
	 * @param cGroup
	 * @param gcGroup
	 * @throws InvalidCupException
	 */
	private void drawBoard(Player p1, Player p2) throws InvalidCupException
	{
		_textBoardPanel.clear();
		
		Cup cup;
		StringBuilder p1Board = new StringBuilder();
		CupGroup cups = p1.getCups();
		
		for(int i = cups.count(); i > 0; --i){
			cup = cups.getCup(i);
			p1Board.append(" | " + cup.toString());	
			if(cup.count() < 10){
				p1Board.append(" ");
			}		
		}
		
		_textBoardPanel.writeln();
		
		drawPlayerStatus(p1);
		_textBoardPanel.writeln();
		
		_textBoardPanel.writeln(p1Board.toString());

		cups = p2.getCups();

		StringBuilder p2Board = new StringBuilder();
		
		for(int i = 1; i <= cups.count(); ++i){
			cup = cups.getCup(i);
			p2Board.append(" | " + cup.toString());	
			if(cup.count() < 10){
				p2Board.append(" ");
			}	
		}
		
		_textBoardPanel.writeln(p2Board.toString());
		_textBoardPanel.writeln();
		
		drawPlayerStatus(p2);
		_textBoardPanel.writeln();
	}
	
	/**
	 * Draw player status
	 * 
	 * @param player
	 */
	private void drawPlayerStatus(Player player)
	{
		_textBoardPanel.writeln(player.toString() + ", captured " + player.countCapturedSeeds());		
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
		_textBoardPanel.paintComponent(g2);
	}
}
