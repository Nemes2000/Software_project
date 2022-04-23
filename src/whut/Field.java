package whut;
import java.util.ArrayList;

//import whut.Runnable.Actions;
import whut.Runnable.Actions;

public class Field
{
	private ArrayList<AgensUsable> au; // A mezőn található entity-k
	private ArrayList<Field> neighbor; //szomszédos mezők

	
	public Field()
	{
		au = new ArrayList<AgensUsable>(); //létrehozza a virológust
		neighbor = new ArrayList<Field>();
		
	}
	

	
	//visszaadja a virológusok listáját
	public ArrayList<AgensUsable> getVirologusok()
	{
		System.out.println(">[:Field].getVirologusok()");
		return au;
		
	}
	
	//törli a virológust a listából
	public void remove(Entity v)
	{
		System.out.println(">[:Field].remove(v)");
		au.remove(v);
	}
	
	//hozzáadja a virológust a listához
	public void accept(Entity v)
	{
		System.out.println(">[:Field].accept(v)");
		AgensUsable ag = (AgensUsable)v;
		au.add(ag);
	}
	
	//mező érintés esetén hívódik meg
	public void touching(Virologus v)
	{
		System.out.println(">[:Field].touching(v)");
		//felsorolja a lehetséges cselekvéseket a felhasználónak
		Runnable.getInput();
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

