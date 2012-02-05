package togizkumalak.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameBoard 
{	
	public static final int PLAYER1 = 0;
	public static final int PLAYER2 = 1;
	public static final int MAX_PLAYERS = 2;
	public static final int STARTING_MOVE = 0;
	private Player[] _players;
	
	private Player _currentPlayer;
	private Player _opposingPlayer;
	private Player _winner;
	
	SeedDistributer _seedDistributer;
	
	private int _startPlayer;
	private int _maxSeeds;
	private int _maxCups;
	
	int _turn;
	int _moveCount;
	
	/**
	 * Initialize game board
	 * 
	 * @param maxCups
	 * @param maxSeeds
	 */
	public GameBoard(int maxCups, int maxSeeds)
	{
		_maxCups = maxCups;
		_maxSeeds = maxSeeds;
		_startPlayer = PLAYER1;
				
		reset();
	}
	
	/**
	 * Player selects cup and board will distribute the seeds 
	 * and calculate winnings.
	 * 
	 * @param pos
	 * 
	 * @return boolean
	 */
	public boolean playerAction(int pos)
	{
		boolean validAction = true;
		try{
			Cup selectedCup = _currentPlayer.selectCup(pos);
			Cup handful = _currentPlayer.grabHandful(selectedCup);
			Cup capturedCup = _seedDistributer.distribute(selectedCup, handful);	
			_opposingPlayer.giveCapturedSeedsToOpponent(_currentPlayer, capturedCup);
			_moveCount++;
			togglePlayerTurn();
		} catch(InvalidCupException e){
			validAction = false;	
		}
		return validAction;
	}
	
	/**
	 * Checks if there is a winner in the game.
	 * 
	 * @return boolean
	 */
	public boolean hasWinner()
	{
		if(_currentPlayer.allCupsEmpty()){
			int currentPlayerTotal = _currentPlayer.countTotalSeeds();
			int opposingPlayerTotal = _opposingPlayer.countTotalSeeds();
			_winner = (currentPlayerTotal > opposingPlayerTotal) ? _currentPlayer : _opposingPlayer;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets winner of game
	 * 
	 * @return Player
	 */
	public Player getWinner()
	{
		return _winner;
	}

	/**
	 * Gets current player
	 * 
	 * @return Player
	 */
	public Player getCurrentPlayer()
	{
		return _currentPlayer;
	}

	/**
	 * Gets player 1
	 * 
	 * @return Player
	 */
	public Player getPlayer1()
	{
		return _players[PLAYER1];
	}

	/**
	 * Gets player 2
	 * 
	 * @return Player
	 */
	public Player getPlayer2()
	{
		return _players[PLAYER2];
	}
	
	/**
	 * Changes to next players turn and sets the previous
	 * player to the opposing player.
	 */
	private void togglePlayerTurn()
	{
		_turn = 1 - _turn;
		setPlayerTurn(_turn);
	}

	/**
	 * Sets player turn explicitly
	 * 
	 * @param turn
	 */
	private void setPlayerTurn(int turn)
	{
		_currentPlayer = _players[turn];
		_opposingPlayer = _players[1 - turn];
	}
	
	/**
	 * Get move count
	 * 
	 * @return moveCoutn
	 */
	public int getMoveCount()
	{
		return _moveCount;
	}
	
	/**
	 * Resets game to starting state
	 */
	public void reset()
	{
		_moveCount = STARTING_MOVE;
		
		Player[] players = new Player[MAX_PLAYERS];

		for(int i = 0; i < MAX_PLAYERS; i++) {		
			CupGroup cupGroup = new CupGroup(_maxCups, _maxSeeds);
			players[i] = new Player("Player" + i, cupGroup);
		}
		
		_players = players;
		
		_seedDistributer = new SeedDistributer();
		for(int i = 0; i < _players.length; i++) {		
			_seedDistributer.appendCupsToLinkedCups(_players[i].getCups());
		}
		
		_turn = _startPlayer;
		
		setPlayerTurn(_turn);		
	}

	/**
	 * Load game state
	 * 
	 * @param filename
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void load(String filename) throws IOException, ClassNotFoundException
	{
		ObjectInputStream objIn = new ObjectInputStream(
			new FileInputStream(filename));
		load(objIn);
	}
	
	/**
	 * Load game state using existing ObjectInputStream
	 * 
	 * @param objIn
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void load(ObjectInputStream objIn) throws IOException, ClassNotFoundException
	{
		_players = (Player[])objIn.readObject();
		_turn = (Integer)objIn.readObject();
		_moveCount = (Integer)objIn.readObject();
		_seedDistributer = new SeedDistributer();
		for(int i = 0; i < _players.length; i++) {		
			_seedDistributer.appendCupsToLinkedCups(_players[i].getCups());
		}
		
		setPlayerTurn(_turn);
	}

	/**
	 * Save game state
	 * 
	 * @param filename
	 * 
	 * @throws IOException
	 */
	public void save(String filename) throws IOException
	{
		ObjectOutputStream objOut = new ObjectOutputStream(
			new FileOutputStream(filename));
		save(objOut);
	}
	
	/**
	 * Save game state using existing ObjectOutputStream
	 * 
	 * @param objOut
	 * 
	 * @throws IOException
	 */
	public void save(ObjectOutputStream objOut) throws IOException
	{
		objOut.writeObject(_players);
		objOut.writeObject(_turn);
		objOut.writeObject(_moveCount);
	}
}
