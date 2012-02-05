import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.controls.FrameCanvas;
import togizkumalak.engine.view.clicktext.ClickTextEngine;

public class TogizKumalak22
{
	public static final String TITLE = "Togiz Kumalak 22";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ClickTextEngine viewEngine = new ClickTextEngine(new FrameCanvas(TITLE));
		TogizKumalak togizKumalak = new TogizKumalak(viewEngine);
		togizKumalak.run();
	}
}
