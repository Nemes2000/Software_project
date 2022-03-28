package whut;
import java.util.ArrayList;
import java.util.Random;

//A megval�s�tott genetikus k�dok �soszt�lya
//ArrayList<Material> cost - az elk�sz�t�s�hez sz�ks�ges anyagok
public abstract class GeneticCode {
	protected ArrayList<Material> cost;
	
	//Konstruktor, melyben a cost-nak adok �rt�keket
	public GeneticCode() 
	{
		cost = new ArrayList<Material>();
		Random rand = new Random();
		//maximum k�t anyagba ker�lhet
		int costDb = rand.nextInt(1);
		for(int i = 0; i <= costDb; i++)
		{
			//gener�l egy random �rt�ket mely szerint aminosavat vagy nukleotidot hozunk l�tre
			boolean melyiket = rand.nextBoolean();
			Material mat;
			if(melyiket)
				mat = new Aminosav();
			else
				mat = new Nukleotid();
			cost.add(mat);
		}	
	}
	
	//ezen f�ggv�nyt meg kell val�sitania a lesz�rmazottaknak
	//l�trehozza a megfelel� �genst, �s hozz�adja a param�terk�nt kapott �gens haszn�l�nak
	//AgensUsable au - ezen entity fogja megkapni a l�trehozott �genst
	public abstract void createAgens(AgensUsable au);
}
