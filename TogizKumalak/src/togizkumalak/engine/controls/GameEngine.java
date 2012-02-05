package togizkumalak.engine.controls;

import togizkumalak.engine.GameBoard;
import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.view.IViewEngine;

public abstract class GameEngine implements IViewEngine 
{
	public static final int CANCEL_ACTION = 0;
	
	protected GameBoard _gameBoard;
	protected GameFrame _frame;
	
	public GameEngine() {}

	/**
	 * Set the graphics frame to use. 
	 * A frame must be set for engine to work.
	 * 
	 * @param gameFrame
	 */
	public void setFrame(GameFrame gameFrame)
	{
		_frame = gameFrame;
	}
	
	/**
	 * Initializes view engine
	 * 
	 * @param gameBoard
	 */
	public void init(GameBoard gameBoard)
	{
		_gameBoard = gameBoard;
	}
	
	/**
	 * Runs view engine
	 */
	public void run()
	{
		drawPlayerTurn();
		_frame.drawFreshBoard(
			_gameBoard.getPlayer1(),
			_gameBoard.getPlayer2());		
	}
	
	/**
	 * Draw current game board state
	 */
	protected void drawBoard() throws InvalidCupException
	{	
		_frame.updateBoard(
			_gameBoard.getPlayer1(),
			_gameBoard.getPlayer2());
	}
	
	/**
	 * Processes user input
	 * 
	 * @param input
	 * @throws InvalidCupException
	 */
	protected void processInput(String input) throws InvalidCupException
	{
		int selectedCupPos;
		boolean validAction;
		
		_frame.clear();
		
		selectedCupPos = validatePlayerAction(input);
		
		validAction = sendPlayerActionToGameBoard(selectedCupPos);
		if(!validAction){
			drawInvalidCupMessage();
		}

		drawPlayerTurn();
		drawBoard();

		if(checkGameBoardForWinner()){
			drawWinner();
		}	
	}
	
	/**
	 * Draw invalid cup message
	 * 
	 * @param player
	 */
	protected void drawInvalidCupMessage()
	{
		_frame.writeError("Error: Invalid cup selected.");	
	}
		
	/**
	 * Validate player action. Make sure it is a valid integer
	 * 
	 * @param input
	 * 
	 * @return Integer
	 */
	protected int validatePlayerAction(String input)
	{
		int selectedCupPos;
		if(input == null){
			return CANCEL_ACTION;
		} else {
			try{
				selectedCupPos = Integer.parseInt(input);	
			} catch(NumberFormatException e){
				selectedCupPos = CANCEL_ACTION;
			}
		}
		return selectedCupPos;	
	}
	
	/**
	 * Draw Player Turn
	 */
	protected void drawPlayerTurn()
	{
		_frame.writeStatus("It is " + _gameBoard.getCurrentPlayer().toString() + "'s move.");
	}
	
	/**
	 * Send player input to game board
	 * 
	 * @param selectedCupPos
	 * 
	 * @return boolean
	 */
	protected boolean sendPlayerActionToGameBoard(int selectedCupPos)
	{
		return _gameBoard.playerAction(selectedCupPos);
	}

	/**
	 * Check to see if there is a winner to the game
	 * 
	 * @return boolean
	 */
	protected boolean checkGameBoardForWinner()
	{
		return _gameBoard.hasWinner();
	}

	/**
	 * Draw winner of game
	 */
	protected void drawWinner() 
	{
		_frame.writeStatus(_gameBoard.getWinner().toString() + " wins the game!");
	}	
}