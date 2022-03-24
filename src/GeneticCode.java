import java.util.ArrayList;
import java.util.Random;

public class GeneticCode {
	protected ArrayList<Material> cost;
	
	public GeneticCode() 
	{
		Random rand = new Random();
		int costDb = rand.nextInt(1);
		for(int i =0; i<costDb +1; i++)
		{
			boolean melyiket = rand.nextBoolean();
			Material mat;
			if(melyiket)
				mat = new Aminosav();
			else
				mat = new Nukleotid();
			cost.add(mat);
		}	
	}
	
	public void createAgens(AgensUsable au)
	{
		
	}

}
