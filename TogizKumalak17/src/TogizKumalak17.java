import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.controls.FrameCanvas;
import togizkumalak.engine.view.dialog.DialogEngine;

public class TogizKumalak17 
{
	public static final String TITLE = "Togiz Kumalak 17";
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		DialogEngine viewEngine = new DialogEngine(new FrameCanvas(TITLE));
		TogizKumalak togizKumalak = new TogizKumalak(viewEngine);
		togizKumalak.run();
	}

}
