package whut;
//egy konkret genetikuskodert felel
public class ForgetCode extends GeneticCode
{
	//meghivja az os konstruktorat
	public ForgetCode() 
	{
		super();
	}

	//letrehozza a megfelelo agenst, es hozzaadja a parameterkent kapott agens hasznalonak
	//AgensUsable au - ezen entity fogja megkapni a letrehozott agenst
	@Override
	public void createAgens(AgensUsable au)
	{
		System.out.println(">[:ForgetCode].createAgens(au)");
		//ha ki tudta "fizetni" az agens hasznal�o az agens letrehozas dijat, akkor kap egyet
		if(au.getPacket().decreaseMaterial(cost))
			au.addAgens(new Protection());
	}
	
	public boolean Check(String s) {
		if(s.equals("FogetCode"))
			return true;
		return false;
	}
}
