package togizkumalak.engine.view.console;

import java.io.Console;

import togizkumalak.engine.Cup;
import togizkumalak.engine.CupGroup;
import togizkumalak.engine.GameBoard;
import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;

public class ConsoleEngine 
{
	private GameBoard _gameBoard;
	
	public ConsoleEngine(GameBoard gameBoard)
	{
		_gameBoard = gameBoard;
	}
	
	/**
	 * Run view engine
	 */
	public void run()
	{
		int selectedCupPos;
		boolean validAction;
		
		try {
			while(true) {
				validAction = false;
				drawBoard();
				
				while(!validAction) {
					selectedCupPos = promptPlayerAction();
					validAction = sendPlayerActionToGameBoard(selectedCupPos);
					if(!validAction){
						drawInvalidCupMessage();
					}
				}
				
				if(checkGameBoardForWinner()){
					drawWinner();
					break;
				}
			}
		} catch(InvalidCupException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Draw current game board state
	 */
	private void drawBoard() throws InvalidCupException
	{		
		Cup cup;

		StringBuilder p1Board = new StringBuilder();
		CupGroup cups = _gameBoard.getPlayer1().getCups();
		
		for(int i = cups.count(); i > 0; --i){
			cup = cups.getCup(i);
			p1Board.append(" | " + cup.toString());	
			if(cup.count() < 10){
				p1Board.append(" ");
			}		
		}

		System.out.println();
		drawPlayerStatus(_gameBoard.getPlayer1());
		System.out.println();
		System.out.println(p1Board);

		cups = _gameBoard.getPlayer2().getCups();

		StringBuilder p2Board = new StringBuilder();
		
		for(int i = 1; i <= cups.count(); ++i){
			cup = cups.getCup(i);
			p2Board.append(" | " + cup.toString());	
			if(cup.count() < 10){
				p2Board.append(" ");
			}	
		}
		
		System.out.println(p2Board);
		System.out.println();		
		
		drawPlayerStatus(_gameBoard.getPlayer2());
		System.out.println();
	}
	
	/**
	 * Draw player status
	 * 
	 * @param player
	 */
	private void drawPlayerStatus(Player player)
	{
		System.out.println(player.toString() + ", captured " + player.countCapturedSeeds());		
	}

	/**
	 * Draw invalid cup message
	 * 
	 * @param player
	 */
	private void drawInvalidCupMessage()
	{
		System.out.println("Error: Invalid cup selected.");	
		System.out.println();
	}
	
	/**
	 * Prompt player for input and retrieve
	 * 
	 * @return Integer
	 */
	private int promptPlayerAction()
	{
		Console console = System.console();
		System.out.println("It is " + _gameBoard.getCurrentPlayer().toString() + "'s move.");
		String pos = console.readLine("Please enter a number from 1 to 9 of the cup you want to play: ");
		return Integer.parseInt(pos);		
	}
	
	/**
	 * Send player input to game board
	 * 
	 * @param selectedCupPos
	 * 
	 * @return boolean
	 */
	private boolean sendPlayerActionToGameBoard(int selectedCupPos)
	{
		return _gameBoard.playerAction(selectedCupPos);
	}

	/**
	 * Check to see if there is a winner to the game
	 * 
	 * @return boolean
	 */
	private boolean checkGameBoardForWinner()
	{
		return _gameBoard.hasWinner();
	}

	/**
	 * Draw winner of game
	 */
	private void drawWinner() 
	{
		System.out.println(_gameBoard.getWinner().toString() + " wins the game!");
	}
}
