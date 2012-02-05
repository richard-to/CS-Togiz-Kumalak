import togizkumalak.engine.TogizKumalakMulti;
import togizkumalak.engine.controls.SaveEngineFactory;

public class TogizKumalak23
{
	public static final String TITLE = "Togiz Kumalak 23";
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		SaveEngineFactory viewEngineFactory = new SaveEngineFactory(TITLE);
		TogizKumalakMulti togizKumalak = new TogizKumalakMulti(viewEngineFactory, TITLE);
		togizKumalak.run();
	}
}
