import java.util.Random;

public abstract class Material {
	
	private int value;
	
	public Material()
	{
		Random rand = new Random();
		value = rand.nextInt(5);
	}
	
	abstract protected String GetType();
	
	public boolean isSame(Material mat) {
		return mat.GetType() == this.GetType();
	}
}
