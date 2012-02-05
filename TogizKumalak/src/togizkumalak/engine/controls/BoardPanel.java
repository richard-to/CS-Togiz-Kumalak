package togizkumalak.engine.controls;

import javax.swing.JPanel;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.view.IBoardPanel;

public abstract class BoardPanel extends JPanel implements IBoardPanel
{
	private static final long serialVersionUID = 2833621464002380394L;
	
	public abstract void drawFreshBoard(Player p1, Player p2) throws InvalidCupException;
	public abstract void updateBoard(Player p1, Player p2) throws InvalidCupException;
}
