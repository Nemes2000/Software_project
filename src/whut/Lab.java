package whut;

import java.util.ArrayList;

import whut.Runnable.Actions;

public class Lab extends Field
{
	private GeneticCode geneticCode;
	
	public Lab()
	{
		super();
		loadactions();
	}
	
	public Lab(GeneticCode g)
	{
		super();
		geneticCode = g;
		loadactions();
	}
	
	@Override
	public void loadactions()
	{
		actionsHere = new ArrayList<Actions>();
		actionsHere.add(TOUCH);
		actionsHere.add(CREATEAGENS);
		actionsHere.add(STEALITEM);
		actionsHere.add(STEALMATERIAL);
		actionsHere.add(KILL);
		actionsHere.add(LEARN);
	}
	
	
	@Override
	public void touching(Virologus v) //mező érintésekor
	{
		System.out.println(">[:Lab].touching(v)");
		//felajánlja a játékosnak a lehetséges cselekvéseket
		Runnable.getInput(actionsHere);
	}
	
	public void setGeneticCode(GeneticCode g) //genetikus kód beállítása
	{
		geneticCode = g;
	}
}

