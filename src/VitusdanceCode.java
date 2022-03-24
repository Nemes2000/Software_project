
public class VitusdanceCode extends GeneticCode
{
	public VitusdanceCode() {
		super();
	}
	
	@Override
	public void createAgens(AgensUsable au)
	{
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Vitusdance());
	}
}
