import togizkumalak.engine.TogizKumalakMulti;
import togizkumalak.engine.controls.GridEngineFactory;

public class TogizKumalakFinal
{
	public static final String TITLE = "Togiz Kumalak Final";
	public static final int MAX_CUPS = 10;
	public static final int MAX_SEEDS = 7;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		GridEngineFactory viewEngineFactory = new GridEngineFactory(TITLE);
		TogizKumalakMulti togizKumalak = new TogizKumalakMulti(
			viewEngineFactory, TITLE, MAX_CUPS, MAX_SEEDS);
		togizKumalak.run();
	}
}
