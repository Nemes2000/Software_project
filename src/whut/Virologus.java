package whut;
import java.util.ArrayList;

public class Virologus extends AgensUsable {
	private ArrayList<Item> itemHave = new ArrayList<Item>();

	public Virologus() {
		VirologusObserver virologusObs=new VirologusObserver(this);
		this.attach(virologusObs);
	
		
	}
	
	public int getItemNumber() {
		return itemHave.size();
	}
	
	
	//megkerdezi a felhasznalot, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealItem(Virologus v,Item item) {
		v.stealItemAttempt(this,item);
		
	}
	
	//megk�rdezi a felhaszn�l�t, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealMaterial(Virologus v, Material mit) {
		v.stealMaterialAttempt(this,mit);
		
	}
	
	//megk�rdezi a felhaszn�l�t�l, hogy melyik t�rgyat akarja cse�lni, �s azt adja vissza
	public Item getItem(String getThis) {
		for(Item i : itemHave) {
			if(i.Check(getThis)) {
				return i;
			}
		}
		return null;
	}

	
	//ellen�rzi, hogy lehet-e t�le t�rgyat lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
	public void stealItemAttempt(Virologus v,Item mit) {
		boolean canSteal = false;
		for(Agens a : agensOnMe) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			if (v.getItemNumber() == 3)	
				removeItem(mit);
			else {
				removeItem(mit);
				v.addItem(mit);
				MyRunnable.log(mit.toString()+" was stolen");
			}
		} else
			MyRunnable.log("The item was not stolen");
		MyRunnable.getGame().myNotify();
		
	}
	
	//ellen�rzi, hogy lehet-e t�le anyagot lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
	public void stealMaterialAttempt(Virologus v,Material mit) {
		boolean canSteal = false;
		for(Agens a : agensOnMe) {
			if (a.canStealEffect())
				canSteal = true;
		}
		if (canSteal) {
			Packet p = v.getPacket();		
			p.handleMaterialSeparate(mit, p);
			ArrayList<Material> temp = new ArrayList<Material>();
			temp.add(mit);
			getPacket().decreaseMaterial(temp);
			MyRunnable.log(mit.toString()+"was stolen");
		} else
			MyRunnable.log("This material was not stolen");
		MyRunnable.getGame().myNotify();
	}
	
	//lecser�l egy t�rgyat a n�la l�v� t�rgyak k�z�l
	//Item mit - a t�rgy, amit lecser�l
	//Item mire - a t�rgy, amire cser�li
	public void changeItem(Item mit, Item mire) {
		this.removeItem(mit);
		this.addItem(mire);
		
	}
	
	//elt�vol�t egy t�rgyat a n�la l�v� t�rgyak k�z�l
	//Item mit - a t�rgy, amit elveszt
	public void removeItem(Item mit) {
		itemHave.remove(mit);
		mit.lostEffect(this);
	}
	
	//hozz�ad egy t�rgyat a n�la l�v� t�rgyakhoz
	//Item mit - a t�rgy, amit megkap
	public void addItem(Item mit) {
		itemHave.add(mit);
		mit.pickUpEffect(this);
	}
	
	//felvesz egy t�rgyat, ha nincs helye, akkor kicser�li az egyiket
	//ArrayList<Item> is - a t�rgyak list�ja, amib�l kiv�lasztja, hogy melyiket szeretn�
	public void pickUpItem(Item mire) {
		if (itemHave.size() >= 3) {
			Item mit = itemHave.get(0);
			changeItem(mit, mire);
			field.addItem(mit);
		} else {
			addItem(mire);
		}
		field.removeItem(mire);
		MyRunnable.getGame().myNotify();
		
	}
	
	//egy t�rgyat otthagy ahol van
	public void leaveItem(Item mit) {
		field.addItem(mit);
		removeItem(mit);
		MyRunnable.getGame().myNotify();
	}
	
	
	//megt�madj�k az adott virol�gust
	@Override
	public void uRAttacked(Agens ag, Virologus v) {
		MyRunnable.log(ag.toString() + " was used against v" + MyRunnable.getVirologusSzam(this));
		
		if (v == this) {
			addAgensOnMe(ag);
			MyRunnable.log("v" + MyRunnable.getVirologusSzam(this)+" is now under " + ag.toString() + " effect");
		} else {
		
			//ellen�rzi, hogy van-e v�dve valami �ltal
			boolean isProtected = false;
			for(Agens a : agensOnMe)
				if(a.defendEffect())
					isProtected=true;
				
			//mivel virol�gus, ez�rt v�gigmegy az �gensein k�v�l az itemein is, hogy azok valamelyike megv�di-e
			for(Item it : itemHave)
				if(!it.canCastEffect())
					isProtected=true;
		
			if(isProtected)
				MyRunnable.log("Unsuccessfull attack!");
		
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
				MyRunnable.log("v" + MyRunnable.getVirologusSzam(this)+" is now under " + ag.toString() + " effect");
			}
			}
		}
		MyRunnable.getGame().myNotify();
	}
	
	public void kill(Virologus v) {
		boolean killed = false;
		for (Item i : itemHave) {
			killed = i.killEffect(v);
			if (killed) { 
				MyRunnable.log("An enemy has been slain!");
				break;
			}
		}
		if (!killed) {
			MyRunnable.log("You need an axe");
		}
		MyRunnable.getGame().myNotify();
	}
	
	public void die() {
		field.remove(this);
		MyRunnable.getGame().removePlayer(this);
	}
	
	public void touch() {
		field.touching(this);
		MyRunnable.getGame().myNotify();
	}
	
	@Override
	public void setField(Field f) {
		field = f;
	}
	
	public void setItem(Item i) {
		itemHave.add(i);
	}
	
	
	@Override
	public void step() {
		MyRunnable.setCurrentVirologus(this);
		if(roundDesc()) {
			//MyRunnable.getInputFirstAct();
		}
		MyRunnable.getGame().endGame(geneticCode);
		MyRunnable.getGame().myNotify();
	}
	
	public ArrayList<Item> getItemHave(){
		return itemHave;
	}
}
