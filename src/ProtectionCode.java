
public class ProtectionCode extends GeneticCode
{
	public ProtectionCode() {
		super();
	}
	
	@Override
	public void createAgens(AgensUsable au)
	{
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Protection());
	}

}
