package whut;

import java.util.ArrayList;

import whut.Runnable.Actions;

public class Storage extends Field
{
	private Packet packet;
	
	public Storage()
	{
		super();
		packet = new Packet();
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
		actionsHere.add(COLLECT);
	}
	
	
	@Override
	public void touching(Virologus v) //mező érintésekor
	{
		System.out.println(">[:Storage].touching(v)");
		//felajánlja a játékosnak a lehetséges cselekvéseket
		Runnable.getInput(actionsHere);
	}
	
	public void setPacket(Packet p) {
		packet = p;
	}
}


