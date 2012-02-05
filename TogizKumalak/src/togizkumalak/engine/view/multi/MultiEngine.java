package togizkumalak.engine.view.multi;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import togizkumalak.engine.view.ICreateGameSubscriber;
import togizkumalak.engine.view.IExitSubscriber;
import togizkumalak.engine.view.IViewEngine;
import togizkumalak.engine.view.IViewEngineFactory;
import togizkumalak.engine.view.listener.CreateGameListener;
import togizkumalak.engine.view.listener.ExitListener;

public class MultiEngine implements ICreateGameSubscriber, IExitSubscriber
{
	private IViewEngineFactory _viewEngineFactory;
	private String _title;
	public MultiEngine(IViewEngineFactory viewEngineFactory, String title)
	{
		_viewEngineFactory = viewEngineFactory;
		_title = title;
	}
	
	/**
	 * Run multi-engine
	 */
	public void run()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem createItem = new JMenuItem("Create Game");
		fileMenu.add(createItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);

		createItem.addActionListener(new CreateGameListener(this));
		exitItem.addActionListener(new ExitListener(this));
		
		new MultiFrame(_title, menuBar);
	}

	/**
	 * Handles create game action
	 */
	public void createGameAction()
	{
		IViewEngine viewEngine = _viewEngineFactory.create();
		viewEngine.run();
	}
	
	/**
	 * Handles close action
	 */
	public void exitAction()
	{
		System.exit(0);
	}
}
