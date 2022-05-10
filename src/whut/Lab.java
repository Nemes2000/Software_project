package whut;

import java.io.Serializable;

public class Lab extends Field
{
	private GeneticCode geneticCode;
	
	public Lab()
	{
		super();
	}
	
	public Lab(GeneticCode g)
	{
		super();
		geneticCode = g;
	}
	
	
	@Override
	public void touching(Virologus v) //mező érintésekor
	{

	}
	
	public void setGeneticCode(GeneticCode g) //genetikus kód beállítása
	{
		geneticCode = g;
	}
	@Override
	public GeneticCode codeHere() {
		return geneticCode;
	}
	
	public String toString() {
		return "lab";
	}
}

