package whut;
import java.util.ArrayList;

//import whut.Runnable.Actions;
import whut.Runnable.Actions;

public class Field
{
	private ArrayList<Entity> entity; // A mezőn található entity-k
	private ArrayList<Field> neighbor; //szomszédos mezők
	protected ArrayList<Actions> actionsHere;
	
	public Field()
	{
		entity = new ArrayList<Entity>(); //létrehozza a virológust
		neighbor = new ArrayList<Field>();
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
	
	//visszaadja a virológusok listáját
	public ArrayList<Entity> getVirologusok()
	{
		System.out.println(">[:Field].getVirologusok()");
		return entity;
		
	}
	
	//törli a virológust a listából
	public void remove(Entity v)
	{
		System.out.println(">[:Field].remove(v)");
		entity.remove(v);
	}
	
	//hozzáadja a virológust a listához
	public void accept(Entity v)
	{
		System.out.println(">[:Field].accept(v)");
		entity.add(v);
	}
	
	//mező érintés esetén hívódik meg
	public void touching(Virologus v)
	{
		System.out.println(">[:Field].touching(v)");
		//felsorolja a lehetséges cselekvéseket a felhasználónak
		Runnable.getInput(actionsHere);
	}
	
	//szomszédos mezők beállítása
	public void setNeighbourhood()
	{
		System.out.println(">[:Field].setNeighbourhood()");
		//skeletonnál még kézzel
	}
	
	//visszaadja a szomszéd mezők listáját
	public ArrayList<Field> getNeighbourhood()
	{
		System.out.println(">[:Field].getNeighbourhood()");
		return neighbor;
	}

	public void removeItem(Item i) {}
	public void addItem(Item i) {}
	
	
	
}

