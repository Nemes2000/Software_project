package whut;
//egy konkr�t genetikusk�d�rt felel
public class StunCode extends GeneticCode
{
	//megh�vja az �s konstruktor�t
	public StunCode() 
	{
		super();
	}
	
	//l�trehozza a megfelel� �genst, �s hozz�adja a param�terk�nt kapott �gens haszn�l�nak
	//AgensUsable au - ezen entity fogja megkapni a l�trehozott �genst
	@Override
	public void createAgens(AgensUsable au)
	{
		System.out.println(">[:StunCode].createAgens(au)");
		//ha ki tudta "fizetni" az �gens haszn�l� az �gens l�trehoz�s d�j�t, akkor kap egyet
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Stun());
		
	}
	
	public boolean Check(String s) {
		if(s.equals("ProtectionCode"))
			return true;
		return false;
	}
}
