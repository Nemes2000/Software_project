import java.util.ArrayList;

public class Virologus extends AgensUsable {
	private ArrayList<Item> itemHave;

	//megkérdezi a felhasználót, hogy melyik virológustól szeretne tárgyat lopni, és megpróbál lopni
	//ArrayList<Virologus> vs - a virológusok listája, amelybõl választhat a felhasználó
	public void stealItem(ArrayList<Virologus> vs) {
		System.out.println(">[:Virologus].stealItem(vs)");
		Virologus v = vs.get(1);
		v.stealItemAttempt(this);
		
	}
	
	//megkérdezi a felhasználót, hogy melyik virológustól szeretne tárgyat lopni, és megpróbál lopni
	//ArrayList<Virologus> vs - a virológusok listája, amelybõl választhat a felhasználó
	public void stealMaterial(ArrayList<Virologus> vs) {
		System.out.println(">[:Virologus].stealItem(vs)");
		Virologus v = vs.get(1);
		v.stealMaterialAttempt(this);
		
	}
	
	//megkérdezi a felhasználótól, hogy melyik tárgyat akarja cseélni, és azt adja vissza
	public Item getItem() {
		return itemHave.get(1);
	}
	
	//ellenõrzi, hogy lehet-e tõle tárgyat lopni, és ha igen, akkor végrehajtja a lopást
	//Virologus v - a virológus, aki lopni próbál tõle
	public void stealItemAttempt(Virologus v) {
		boolean canSteal = false;
		for(Agens a : agensOnMe) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			if (itemHave.size() >= 3) {
				Item mit = itemHave.get(1);
				Item mire = v.getItem();
				changeItem(mire, mit);
				v.changeItem(mit, mire);
				
			} else {
				Item mit = itemHave.get(1);
				removeItem(mit);
				v.addItem(mit);
			}
		}
		
	}
	
	//ellenõrzi, hogy lehet-e tõle anyagot lopni, és ha igen, akkor végrehajtja a lopást
	//Virologus v - a virológus, aki lopni próbál tõle
	public void stealMaterialAttempt(Virologus v) {
		
	}
	
	//lecserél egy tárgyat a nála lévõ tárgyak közül
	//Item mit - a tárgy, amit lecserél
	//Item mire - a tárgy, amire cseréli
	public void changeItem(Item mit, Item mire) {
		System.out.println(">[:Virologus].changeItem(mit, mire)");
		this.removeItem(mit);
		this.addItem(mire);
		
	}
	
	//eltávolít egy tárgyat a nála lévõ tárgyak közül
	//Item mit - a tárgy, amit elveszt
	public void removeItem(Item mit) {
		System.out.println(">[:Virologus].removeItem(mit)");
		mit.lostEffect(this);
	}
	
	//hozzáad egy tárgyat a nála lévõ tárgyakhoz
	//Item mit - a tárgy, amit megkap
	public void addItem(Item mit) {
		System.out.println(">[:Virologus].addItem(mit)");
		mit.pickUpEffect(this);
	}
	
	//még nincs kész
	public void pickUpItem(ArrayList<Item> is) {
		System.out.println(">[:Virologus].pickUpItem(is)");
		if (itemHave.size() >= 3) {
			changeItem(itemHave.get(1), is.get(1));
		}
		
	}
	
	//még nincs kész
	public void leaveItem() {
		System.out.println(">[:Virologus].leaveItem()");
	}
}
