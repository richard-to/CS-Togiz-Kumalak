import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.controls.FrameCanvas;
import togizkumalak.engine.view.textbox.TextBoxEngine;

public class TogizKumalak18 
{
	public static final String TITLE = "Togiz Kumalak 18";
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		TextBoxEngine viewEngine = new TextBoxEngine(new FrameCanvas(TITLE));
		TogizKumalak togizKumalak = new TogizKumalak(viewEngine);
		togizKumalak.run();
	}

}
