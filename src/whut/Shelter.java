package whut;
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
	@Override
	public void removeItem(Item i)
	{
		items.remove(i);
	}
	
	//hozzáadja a tárgyat a shelterhez
	@Override
	public void addItem(Item i)
	{
		items.add(i);
	}
	
	@Override
	public void touching(Virologus v) //mező érintésekor
	{
		//felajánlja a játékosnak a lehetséges cselekvéseket
		MyRunnable.getInputAfterTouch();
	}

	@Override
	public ArrayList<Item> getItems() {
		return items;
	}
	
	@Override
	public Item getItem(String getThis) {
		for(Item i : items) {
			if(i.Check(getThis)) {
				return i;
			}
		}
		MyRunnable.log("There is no "+ getThis + " here!");
		return null;
	}
	
	public String toString() {
		return "shelter";
	}
}


