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
		System.out.println(">[:Virologus].stealItemAttempt(v)");
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
		System.out.println(">[:Virologus].stealMaterialAttempt(v)");
		boolean canSteal = false;
		for(Agens a : agensOnMe) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			Packet p = v.getPacket();
			ArrayList<Material> ms = packet.getMaterials();
			for (Material m : ms) {
				packet.handleMaterialSeparate(m, p);
			}
		}
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
	
	//felvesz egy tárgyat, ha nincs helye, akkor kicseréli az egyiket
	//ArrayList<Item> is - a tárgyak listája, amibõl kiválasztja, hogy melyiket szeretné
	public void pickUpItem(ArrayList<Item> is) {
		System.out.println(">[:Virologus].pickUpItem(is)");
		Item mire = is.get(1);
		if (itemHave.size() >= 3) {
			Item mit = itemHave.get(1);
			changeItem(mit, mire);
			field.addItem(mit);
		} else {
			addItem(mire);
		}
		field.removeItem(mire);
		
	}
	
	//egy tárgyat otthagy ahol van
	public void leaveItem() {
		System.out.println(">[:Virologus].leaveItem()");
		Item mit = itemHave.get(1);
		field.addItem(mit);
		removeItem(mit);
	}
	
	
	//megtámadják az adott virológust
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:Virologus].uRAttacked()");
		//küldõtõlk kitörli az ágenst
		v.removeAgens(ag);
		//ellenõrzi, hogy van-e védve valami által
		boolean isProtected = false;
		for(Agens a : agensOnMe){
			if(a.defendEffect()) {
				isProtected=true;
			}
		}
		//mivel virológus, ezért végigmegy az ágensein kívül az itemein is, hogy azok valamelyike megvédi-e
		for(Item it : itemHave){
			if(it.canCastEffect()) {
				isProtected=true;
			}
		}
		//mivel virológus, ezért végigmegy az itemein, hogy valamelyik visszakeni-e
		if(!isProtected) {
			boolean fireBacked = false;
			for(Item it: itemHave){
				if(it.fireBackEffect(v,ag)) {
					fireBacked=true;
				}
			}
			//ha vissza sem keni, akkor hozzáadja a rajt lévõ ágensekhez
			if(!fireBacked) {
				addAgensOnMe(ag);
			}
			
			
		}
		
	}
	
	public void touch() {
		System.out.println(">[:Virologus].touch()");
		field.touching(this);
	}
	
	public void setField(Field f) {
		field = f;
	}
}
