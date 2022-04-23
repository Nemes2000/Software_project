package whut;
import java.util.ArrayList;

import whut.Runnable.Actions;

public class Shelter extends Field
{
	private ArrayList<Item> items;
	
	public Shelter()
	{
		super();
		items = new ArrayList<Item>();
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
		actionsHere.add(PICKUP);
		actionsHere.add(LEAVE);
		actionsHere.add(KILL);
	}
	
	
	//tölri a tárgyat a shelterről
	@Override
	public void removeItem(Item i)
	{
		System.out.println(">[:Shelter].removeItem(i)");
		items.remove(i);
	}
	
	//hozzáadja a tárgyat a shelterhez
	@Override
	public void addItem(Item i)
	{
		System.out.println(">[:Shelter].addItem(i)");
		items.add(i);
	}
	
	@Override
	public void touching(Virologus v) //mező érintésekor
	{
		System.out.println(">[:Shelter].touching(v)");
		//felajánlja a játékosnak a lehetséges cselekvéseket
		Runnable.getInput(actionsHere);
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
}


