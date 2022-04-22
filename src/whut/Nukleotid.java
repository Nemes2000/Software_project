package whut;
//egy konkr�t anyagt�pus�rt felel
public class Nukleotid extends Material
{
	//Konstruktor, amit az �s�vel egyenl�
	public Nukleotid() {
		super();
	}
	
	//megval�s�tott f�ggv�ny, mely az �sben nincs implement�lva
	//visszaadja a t�pus�t az anyagnak
	@Override
	protected String getType() 
	{
		System.out.println(">[:Nukleotid].getType()");
		return "Nukleotid";
	}

}
