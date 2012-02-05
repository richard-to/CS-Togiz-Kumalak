package togizkumalak.engine.view.save;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import togizkumalak.engine.InvalidCupException;
import togizkumalak.engine.Player;
import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.controls.FrameCanvas;
import togizkumalak.engine.controls.GameEngine;
import togizkumalak.engine.view.IClickSubscriber;
import togizkumalak.engine.view.IExitSubscriber;
import togizkumalak.engine.view.IHoverSubscriber;
import togizkumalak.engine.view.IKeySubscriber;
import togizkumalak.engine.view.ILoadSubscriber;
import togizkumalak.engine.view.IRestartSubscriber;
import togizkumalak.engine.view.ISaveSubscriber;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.click.ClickHoverAdapter;
import togizkumalak.engine.view.click.ClickMouseAdapter;
import togizkumalak.engine.view.clicktext.ClickTextFrame;
import togizkumalak.engine.view.clicktext.ClickTextKeyAdapter;
import togizkumalak.engine.view.draw.DrawBoardPanel;
import togizkumalak.engine.view.draw.DrawSeedCupFactory;
import togizkumalak.engine.view.listener.ExitListener;
import togizkumalak.engine.view.listener.LoadListener;
import togizkumalak.engine.view.listener.RestartListener;
import togizkumalak.engine.view.listener.SaveListener;

public class SaveEngine extends GameEngine implements 
	IViewEngine, IClickSubscriber, IHoverSubscriber, 
	IKeySubscriber, IExitSubscriber, IRestartSubscriber,
	ILoadSubscriber, ISaveSubscriber
{		
	private FrameCanvas _canvas;
	
	public SaveEngine(FrameCanvas canvas)
	{
		_canvas = canvas;
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem loadItem = new JMenuItem("Load");
		fileMenu.add(loadItem);
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);

		JMenuItem restartItem = new JMenuItem("Restart");
		fileMenu.add(restartItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		loadItem.addActionListener(new LoadListener(this));
		saveItem.addActionListener(new SaveListener(this));
		restartItem.addActionListener(new RestartListener(this));
		exitItem.addActionListener(new ExitListener(this));

		_frame = new ClickTextFrame(
			canvas, 
			new DrawBoardPanel(new DrawSeedCupFactory()),
			menuBar,
			new ClickMouseAdapter(this),
			new ClickHoverAdapter(this),
			new ClickTextKeyAdapter(this));
	}
	
	/**
	 * Updates game state when board hovered
	 * 
	 * @param hover
	 */
	public void hoverAction(Point hover) 
	{
		Player player = _gameBoard.getCurrentPlayer();
		((ClickTextFrame)_frame).hoverCup(player, hover);
	}
	
	/**
	 * Updates game state when board is clicked
	 * 
	 * @param click
	 */
	public void clickAction(Point click) 
	{
		Player player = _gameBoard.getCurrentPlayer();
		String input = Integer.toString(((ClickTextFrame)_frame).clickCup(player, click));

		if(Integer.parseInt(input) > 0){
			try{
				processInput(input);
				recordMove(player, input);
			} catch(InvalidCupException e) {
				_frame.writeStatus(e.getMessage());
			}
		}
	}
	
	/**
	 * Updates game state when key is typed
	 * 
	 * @param event
	 */
	public void keyAction(char key)
	{
		Player player = _gameBoard.getCurrentPlayer();
		int nkey = Character.getNumericValue(key);
		if(Character.isDigit(key) && nkey > 0 && nkey <= TogizKumalak.MAX_CUPS){
			String input = Character.toString(key);
			try{
				processInput(input);
				recordMove(player, input);
			} catch(InvalidCupException e) {
				_frame.writeStatus(e.getMessage());
			}			
		}
	}
	
	/**
	 * Record move to screen
	 * 
	 * @param player
	 * @param input
	 */
	private void recordMove(Player player, String input)
	{
		String moveNum = Integer.toString(_gameBoard.getMoveCount());
		String move = moveNum + ": " + player + " selects cup " + input + ".";
		((ClickTextFrame)_frame).recordMove(move);
	}
	
	/**
	 * Restarts game
	 */
	public void restartAction()
	{
		_gameBoard.reset();
		((ClickTextFrame)_frame).clearRecord();
		run();
	}

	/**
	 * Loads game
	 */
	public void loadAction()
	{
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.showOpenDialog(_canvas);
			String filename = fileChooser.getSelectedFile().getPath();

			ObjectInputStream objIn = new ObjectInputStream(
				new FileInputStream(filename));
			
			((ClickTextFrame)_frame).clearRecord();
			String recordedMoves = (String)objIn.readObject();
			((ClickTextFrame)_frame).loadRecordedMoves(recordedMoves);
			
			_gameBoard.load(objIn);
			
			run();
		}catch(IOException e){
			_frame.writeError("Error: Could not load game.");
		} catch(ClassNotFoundException e) {
			_frame.writeError("Error: Invalid or corrupted file.");
		}
	}

	/**
	 * Saves game
	 */
	public void saveAction()
	{
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.showSaveDialog(_canvas);
			String filename = fileChooser.getSelectedFile().getPath();
			
			ObjectOutputStream objOut = new ObjectOutputStream(
					new FileOutputStream(filename));
			
			objOut.writeObject(((ClickTextFrame)_frame).getRecordedMoves());
			
			_gameBoard.save(objOut);			
		} catch(IOException e) {
			_frame.writeError("Error: Could not save game.");
		}

	}
	
	/**
	 * Exits action
	 */
	public void exitAction()
	{
		_canvas.dispose();
	}
}
