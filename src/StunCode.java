//egy konkrét genetikuskódért felel
public class StunCode extends GeneticCode
{
	//meghívja az õs konstruktorát
	public StunCode() 
	{
		super();
	}
	
	//létrehozza a megfelelõ ágenst, és hozzáadja a paraméterként kapott ágens használónak
	//AgensUsable au - ezen entity fogja megkapni a létrehozott ágenst
	@Override
	public void createAgens(AgensUsable au)
	{
		System.out.println(">[:StunCode].createAgens(au)");
		//ha ki tudta "fizetni" az ágens használó az ágens létrehozás díját, akkor kap egyet
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Stun());
		
	}
}
