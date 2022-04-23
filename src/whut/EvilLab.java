package whut;

import java.util.ArrayList;

import whut.Runnable.Actions;

public class EvilLab
{
	protected ArrayList<Actions> actionsHere;
	
	public EvilLab()
	{
		super();
		loadactions();
	}
	
	public void loadactions()
	{
		actionsHere = new ArrayList<Actions>();
		actionsHere.add(TOUCH);
		actionsHere.add(CREATEAGENS);
		actionsHere.add(STEALITEM);
		actionsHere.add(STEALMATERIAL);
		actionsHere.add(KILL);
	}
	
	public void touching(Virologus v) //mező érintésekor
	{
		System.out.println(">[:EvilLab].touching(v)");
		//felajánlja a játékosnak a lehetséges cselekvéseket
		Beardance br = new Beardance();
		v.uRAttacked(br,null); //megtámadjad egy medvetáncal
		Runnable.getInput(actionsHere);
	}
	
}
