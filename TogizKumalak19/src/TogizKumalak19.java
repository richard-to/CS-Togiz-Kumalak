import togizkumalak.engine.TogizKumalak;
import togizkumalak.engine.controls.FrameCanvas;
import togizkumalak.engine.view.draw.DrawEngine;

public class TogizKumalak19 
{
	public static final String TITLE = "Togiz Kumalak 19";
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		DrawEngine viewEngine = new DrawEngine(new FrameCanvas(TITLE));
		TogizKumalak togizKumalak = new TogizKumalak(viewEngine);
		togizKumalak.run();
	}
}
