package togizkumalak.engine.view;

import java.awt.Point;

import togizkumalak.engine.Player;

public interface IBoardPanelClickable extends IBoardPanel
{
	int clickCup(Player p1, Point click);
	void hoverCup(Player p1, Point hover);
}
