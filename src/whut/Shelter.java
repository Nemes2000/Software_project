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
	
	//tÃ¶lri a tÃ¡rgyat a shelterrÅ‘l
	@Override
	public void removeItem(Item i)
	{
		items.remove(i);
	}
	
	//hozzÃ¡adja a tÃ¡rgyat a shelterhez
	@Override
	public void addItem(Item i)
	{
		items.add(i);
	}
	
	
	//A paraméterként megadott virológus megérinti a mezõt. 
	//Megjelentíti a játékosnak a lehetséges cselekvéseket, majd a kiválasztott cselekvés alapján indítja el a megfelelõ folyamatot a virológusnál. 
	//Lehetséges cselekvések: tárgylopás, anyaglopás, ágenshasználat, tárgyfelvétel, tárgy lerakás.
	@Override
	public void touching(Virologus v) //mezÅ‘ Ã©rintÃ©sekor
	{

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


