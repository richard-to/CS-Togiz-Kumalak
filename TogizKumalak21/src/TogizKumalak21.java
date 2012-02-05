import javax.swing.JApplet;

import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.view.ICanvas;
import togizkumalak.engine.view.click.ClickEngine;

public class TogizKumalak21 extends JApplet implements ICanvas
{
	private static final long serialVersionUID = 6602189093701841757L;	
	public void init() 
	{
		ClickEngine viewEngine = new ClickEngine(this);
		TogizKumalak togizKumalak = new TogizKumalak(viewEngine);
		togizKumalak.run();
	}
}
