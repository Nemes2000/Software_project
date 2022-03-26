import java.util.ArrayList;
import java.util.Random;

//A megvalósított genetikus kódok õsosztálya
//ArrayList<Material> cost - az elkészítéséhez szükséges anyagok
public abstract class GeneticCode {
	protected ArrayList<Material> cost;
	
	//Konstruktor, melyben a cost-nak adok értékeket
	public GeneticCode() 
	{
		Random rand = new Random();
		
		//maximum két anyagba kerülhet
		int costDb = rand.nextInt(1);
		for(int i = 0; i <= costDb; i++)
		{
			//generál egy random értéket mely szerint aminosavat vagy nukleotidot hozunk létre
			boolean melyiket = rand.nextBoolean();
			Material mat;
			if(melyiket)
				mat = new Aminosav();
			else
				mat = new Nukleotid();
			cost.add(mat);
		}	
	}
	
	//ezen függvényt meg kell valósitania a leszármazottaknak
	//létrehozza a megfelelõ ágenst, és hozzáadja a paraméterként kapott ágens használónak
	//AgensUsable au - ezen entity fogja megkapni a létrehozott ágenst
	public abstract void createAgens(AgensUsable au);
}
