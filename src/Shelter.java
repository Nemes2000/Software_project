import java.util.ArrayList;

public class Shelter extends Field
{
	private ArrayList<Item> items;
	
	public Shelter()
	{
		super();
		items = new ArrayList<Item>();
	}
	
	//tölri a tárgyat a shelterről
	public void removeItem(Item i)
	{
		System.out.println(">[:Shelter].removeItem(i)");
		items.remove(i);
	}
	
	//hozzáadja a tárgyat a shelterhez
	public void addItem(Item i)
	{
		System.out.println(">[:Shelter].addItem(i)");
		items.add(i);
	}
	
	//skeletonhoz?
	public void setItem(Item i)
	{
		items.add(i);
	}
	
	@Override
	public void touching(Virologus v) //mező érintésekor
	{
		System.out.println(">[:Shelter].touching(v)");
		//felajánlja a játékosnak a lehetséges cselekvéseket
	}
}


