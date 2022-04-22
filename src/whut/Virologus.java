package whut;
import java.util.ArrayList;

public class Virologus extends AgensUsable {
	private ArrayList<Item> itemHave = new ArrayList<Item>();

	//megkï¿½rdezi a felhasznï¿½lï¿½t, hogy melyik virolï¿½gustï¿½l szeretne tï¿½rgyat lopni, ï¿½s megprï¿½bï¿½l lopni
	//ArrayList<Virologus> vs - a virolï¿½gusok listï¿½ja, amelybï¿½l vï¿½laszthat a felhasznï¿½lï¿½
	public void stealItem(Virologus v) {
		System.out.println(">[:Virologus].stealItem(vs)");
		v.stealItemAttempt(this);
		
	}
	
	//megkï¿½rdezi a felhasznï¿½lï¿½t, hogy melyik virolï¿½gustï¿½l szeretne tï¿½rgyat lopni, ï¿½s megprï¿½bï¿½l lopni
	//ArrayList<Virologus> vs - a virolï¿½gusok listï¿½ja, amelybï¿½l vï¿½laszthat a felhasznï¿½lï¿½
	public void stealMaterial(Virologus v) {
		System.out.println(">[:Virologus].stealItem(vs)");
		v.stealMaterialAttempt(this);
		
	}
	
	//megkï¿½rdezi a felhasznï¿½lï¿½tï¿½l, hogy melyik tï¿½rgyat akarja cseï¿½lni, ï¿½s azt adja vissza
	public Item getItem() {
		return itemHave.get(0);
	}
	public Item getItem(String s) {
		//itt az itemtõl típuslekérdezés kell de nincs jobb otletem
	}
	
	//ellenï¿½rzi, hogy lehet-e tï¿½le tï¿½rgyat lopni, ï¿½s ha igen, akkor vï¿½grehajtja a lopï¿½st
	//Virologus v - a virolï¿½gus, aki lopni prï¿½bï¿½l tï¿½le
	public void stealItemAttempt(Virologus v) {
		System.out.println(">[:Virologus].stealItemAttempt(v)");
		boolean canSteal = false;
		for(Agens a : agensOnMe) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			if (itemHave.size() >= 3) {
				Item mit = itemHave.get(0);
				Item mire = v.getItem();
				changeItem(mire, mit);
				v.changeItem(mit, mire);
				
			} else {
				Item mit = itemHave.get(0);
				removeItem(mit);
				v.addItem(mit);
			}
		}
		
	}
	
	//ellenï¿½rzi, hogy lehet-e tï¿½le anyagot lopni, ï¿½s ha igen, akkor vï¿½grehajtja a lopï¿½st
	//Virologus v - a virolï¿½gus, aki lopni prï¿½bï¿½l tï¿½le
	public void stealMaterialAttempt(Virologus v) {
		System.out.println(">[:Virologus].stealMaterialAttempt(v)");
		boolean canSteal = false;
		for(Agens a : agensOnMe) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			Packet p = v.getPacket();
			ArrayList<Material> ms = p.getMaterials();
			for (Material m : ms) {
				p.handleMaterialSeparate(m, p);
			}
		}
	}
	
	//lecserï¿½l egy tï¿½rgyat a nï¿½la lï¿½vï¿½ tï¿½rgyak kï¿½zï¿½l
	//Item mit - a tï¿½rgy, amit lecserï¿½l
	//Item mire - a tï¿½rgy, amire cserï¿½li
	public void changeItem(Item mit, Item mire) {
		System.out.println(">[:Virologus].changeItem(mit, mire)");
		this.removeItem(mit);
		this.addItem(mire);
		
	}
	
	//eltï¿½volï¿½t egy tï¿½rgyat a nï¿½la lï¿½vï¿½ tï¿½rgyak kï¿½zï¿½l
	//Item mit - a tï¿½rgy, amit elveszt
	public void removeItem(Item mit) {
		System.out.println(">[:Virologus].removeItem(mit)");
		itemHave.remove(mit);
		mit.lostEffect(this);
	}
	
	//hozzï¿½ad egy tï¿½rgyat a nï¿½la lï¿½vï¿½ tï¿½rgyakhoz
	//Item mit - a tï¿½rgy, amit megkap
	public void addItem(Item mit) {
		System.out.println(">[:Virologus].addItem(mit)");
		itemHave.add(mit);
		mit.pickUpEffect(this);
	}
	
	//felvesz egy tï¿½rgyat, ha nincs helye, akkor kicserï¿½li az egyiket
	//ArrayList<Item> is - a tï¿½rgyak listï¿½ja, amibï¿½l kivï¿½lasztja, hogy melyiket szeretnï¿½
	public void pickUpItem(ArrayList<Item> is) {
		System.out.println(">[:Virologus].pickUpItem(is)");
		Item mire = is.get(0);
		if (itemHave.size() >= 3) {
			Item mit = itemHave.get(0);
			changeItem(mit, mire);
			field.addItem(mit);
		} else {
			addItem(mire);
		}
		field.removeItem(mire);
		
	}
	
	//egy tï¿½rgyat otthagy ahol van
	public void leaveItem() {
		System.out.println(">[:Virologus].leaveItem()");
		Item mit = itemHave.get(0);
		field.addItem(mit);
		removeItem(mit);
	}
	
	
	//megtï¿½madjï¿½k az adott virolï¿½gust
	@Override
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:Virologus].uRAttacked()");
		//kï¿½ldï¿½tï¿½lk kitï¿½rli az ï¿½genst
		v.removeAgens(ag);
		//ellenï¿½rzi, hogy van-e vï¿½dve valami ï¿½ltal
		boolean isProtected = false;
		for(Agens a : agensOnMe){
			if(a.defendEffect()) {
				isProtected=true;
			}
		}
		//mivel virolï¿½gus, ezï¿½rt vï¿½gigmegy az ï¿½gensein kï¿½vï¿½l az itemein is, hogy azok valamelyike megvï¿½di-e
		for(Item it : itemHave){
			if(it.canCastEffect()) {
				isProtected=true;
			}
		}
		//mivel virolï¿½gus, ezï¿½rt vï¿½gigmegy az itemein, hogy valamelyik visszakeni-e
		if(!isProtected) {
			boolean fireBacked = false;
			for(Item it: itemHave){
				if(it.fireBackEffect(v, this, ag)) {
					fireBacked=true;
				}
			}
			//ha vissza sem keni, akkor hozzï¿½adja a rajt lï¿½vï¿½ ï¿½gensekhez
			if(!fireBacked) {
				addAgensOnMe(ag);
			}
		}
	}
	
	public void kill(Virologus v) {
		boolean killed = false;
		for (Item i : itemHave) {
			killed = i.killEffect(v);
			if (killed) break;
		}
	}
	
	public void die() {
		field.remove(this);
		game.removePlayer();
	}
	
	public void touch() {
		System.out.println(">[:Virologus].touch()");
		field.touching(this);
	}
	
	@Override
	public void setField(Field f) {
		field = f;
	}
	
	public void setItem(Item i) {
		itemHave.add(i);
	}
}
