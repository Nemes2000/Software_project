
public class ForgetCode extends GeneticCode {
	public ForgetCode() {
		super();
	}

	@Override
	public void createAgens(AgensUsable au)
	{
		if(au.getPacket().decreaseMaterial(this.cost))
			au.addAgens(new Protection());
	}
}
