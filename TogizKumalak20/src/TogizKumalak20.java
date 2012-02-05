import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.controls.FrameCanvas;
import togizkumalak.engine.view.click.ClickEngine;

public class TogizKumalak20
{
	public static final String TITLE = "Togiz Kumalak 20";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ClickEngine viewEngine = new ClickEngine(new FrameCanvas(TITLE));
		TogizKumalak togizKumalak = new TogizKumalak(viewEngine);
		togizKumalak.run();
	}
}
