package togizkumalak.engine.view;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;

public interface IBoardPanel 
{
	void drawFreshBoard(Player p1, Player p2) throws InvalidCupException;
	void updateBoard(Player p1, Player p2) throws InvalidCupException;
}
