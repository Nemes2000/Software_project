
public class StunCode extends GeneticCode
{
	public StunCode() {
		super();
	}
	
	@Override
	public void createAgens(AgensUsable au)
	{
		//csak akkor vonja le ha meg van hozzá a megfelelõ mennyiség
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Stun());
		
	}
}
