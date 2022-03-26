//egy konkrét anyagtípusért felel
public class Aminosav extends Material
{
	//Konstruktor, amit az õsével egyenlõ
	public Aminosav() 
	{
		super();
	}

	
	//megvalósított függvény, mely az õsben nincs implementálva
	//visszaadja a típusát az anyagnak
	@Override
	protected String getType() {
		return "Aminosav";
	}
	
}
