//egy konkrét anyagtípusért felel
public class Nukleotid extends Material
{
	//Konstruktor, amit az õsével egyenlõ
	public Nukleotid() {
		super();
	}
	
	//megvalósított függvény, mely az õsben nincs implementálva
	//visszaadja a típusát az anyagnak
	@Override
	protected String getType() 
	{
		System.out.println(">[:Nukleotid].getType()");
		return "Nukleotid";
	}

}
