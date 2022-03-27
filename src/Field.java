import java.util.ArrayList;

public class Field
{
	private ArrayList<? super Virologus> entity; // A mezőn található entity-k
	private ArrayList<Field> neighbor; //szomszédos mezők
	
	public Field()
	{
		//létrehozza a virológust
	}
	
	//visszaadja a virológusok listáját
	public ArrayList<? super Virologus> getVirologusok()
	{
		System.out.println(">[:Field].getVirologusok()");
		return entity;
		
	}
	
	//törli a virológust a listából
	public void remove(Virologus v)
	{
		System.out.println(">[:Field].remove(v)");
		entity.remove(v);
	}
	
	//hozzáadja a virológust a listához
	public void accept(Virologus v)
	{
		System.out.println(">[:Field].accept(v)");
		entity.add(v);
	}
	
	//mező érintés esetén hívódik meg
	public void touching(Virologus v)
	{
		System.out.println(">[:Field].touching(v)");
		//felsorolja a lehetséges cselekvéseket a felhasználónak
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

