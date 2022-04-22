package whut;
import java.util.ArrayList;

public class Virologus extends AgensUsable {
	private ArrayList<Item> itemHave = new ArrayList<Item>();

	//megk�rdezi a felhaszn�l�t, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealItem(Virologus v) {
		System.out.println(">[:Virologus].stealItem(vs)");
		v.stealItemAttempt(this);
		
	}
	
	//megk�rdezi a felhaszn�l�t, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealMaterial(Virologus v) {
		System.out.println(">[:Virologus].stealItem(vs)");
		v.stealMaterialAttempt(this);
		
	}
	
	//megk�rdezi a felhaszn�l�t�l, hogy melyik t�rgyat akarja cse�lni, �s azt adja vissza
	public Item getItem() {
		return itemHave.get(0);
	}
	public Item getItem(String s) {
		//itt az itemt�l t�puslek�rdez�s kell de nincs jobb otletem
	}
	
	//ellen�rzi, hogy lehet-e t�le t�rgyat lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
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
	
	//ellen�rzi, hogy lehet-e t�le anyagot lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
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
	
	//lecser�l egy t�rgyat a n�la l�v� t�rgyak k�z�l
	//Item mit - a t�rgy, amit lecser�l
	//Item mire - a t�rgy, amire cser�li
	public void changeItem(Item mit, Item mire) {
		System.out.println(">[:Virologus].changeItem(mit, mire)");
		this.removeItem(mit);
		this.addItem(mire);
		
	}
	
	//elt�vol�t egy t�rgyat a n�la l�v� t�rgyak k�z�l
	//Item mit - a t�rgy, amit elveszt
	public void removeItem(Item mit) {
		System.out.println(">[:Virologus].removeItem(mit)");
		itemHave.remove(mit);
		mit.lostEffect(this);
	}
	
	//hozz�ad egy t�rgyat a n�la l�v� t�rgyakhoz
	//Item mit - a t�rgy, amit megkap
	public void addItem(Item mit) {
		System.out.println(">[:Virologus].addItem(mit)");
		itemHave.add(mit);
		mit.pickUpEffect(this);
	}
	
	//felvesz egy t�rgyat, ha nincs helye, akkor kicser�li az egyiket
	//ArrayList<Item> is - a t�rgyak list�ja, amib�l kiv�lasztja, hogy melyiket szeretn�
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
	
	//egy t�rgyat otthagy ahol van
	public void leaveItem() {
		System.out.println(">[:Virologus].leaveItem()");
		Item mit = itemHave.get(0);
		field.addItem(mit);
		removeItem(mit);
	}
	
	
	//megt�madj�k az adott virol�gust
	@Override
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:Virologus].uRAttacked()");
		//k�ld�t�lk kit�rli az �genst
		v.removeAgens(ag);
		//ellen�rzi, hogy van-e v�dve valami �ltal
		boolean isProtected = false;
		for(Agens a : agensOnMe){
			if(a.defendEffect()) {
				isProtected=true;
			}
		}
		//mivel virol�gus, ez�rt v�gigmegy az �gensein k�v�l az itemein is, hogy azok valamelyike megv�di-e
		for(Item it : itemHave){
			if(it.canCastEffect()) {
				isProtected=true;
			}
		}
		//mivel virol�gus, ez�rt v�gigmegy az itemein, hogy valamelyik visszakeni-e
		if(!isProtected) {
			boolean fireBacked = false;
			for(Item it: itemHave){
				if(it.fireBackEffect(v, this, ag)) {
					fireBacked=true;
				}
			}
			//ha vissza sem keni, akkor hozz�adja a rajt l�v� �gensekhez
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
