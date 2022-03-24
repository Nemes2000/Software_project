
public class StunCode extends GeneticCode
{
	public StunCode() {
		super();
	}
	
	@Override
	public void createAgens(AgensUsable au)
	{
		//csak akkor vonja le ha meg van hozz� a megfelel� mennyis�g
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Stun());
		
	}
}
