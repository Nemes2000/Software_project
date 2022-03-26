import java.util.ArrayList;

public class Virologus extends AgensUsable {
	private ArrayList<Item> itemHave;

	//megk�rdezi a felhaszn�l�t, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealItem(ArrayList<Virologus> vs) {
		System.out.println(">[:Virologus].stealItem(vs)");
		Virologus v = vs.get(1);
		v.stealItemAttempt(this);
		
	}
	
	//megk�rdezi a felhaszn�l�t, hogy melyik virol�gust�l szeretne t�rgyat lopni, �s megpr�b�l lopni
	//ArrayList<Virologus> vs - a virol�gusok list�ja, amelyb�l v�laszthat a felhaszn�l�
	public void stealMaterial(ArrayList<Virologus> vs) {
		System.out.println(">[:Virologus].stealItem(vs)");
		Virologus v = vs.get(1);
		v.stealMaterialAttempt(this);
		
	}
	
	//megk�rdezi a felhaszn�l�t�l, hogy melyik t�rgyat akarja cse�lni, �s azt adja vissza
	public Item getItem() {
		return itemHave.get(1);
	}
	
	//ellen�rzi, hogy lehet-e t�le t�rgyat lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
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
	
	//ellen�rzi, hogy lehet-e t�le anyagot lopni, �s ha igen, akkor v�grehajtja a lop�st
	//Virologus v - a virol�gus, aki lopni pr�b�l t�le
	public void stealMaterialAttempt(Virologus v) {
		
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
		mit.lostEffect(this);
	}
	
	//hozz�ad egy t�rgyat a n�la l�v� t�rgyakhoz
	//Item mit - a t�rgy, amit megkap
	public void addItem(Item mit) {
		System.out.println(">[:Virologus].addItem(mit)");
		mit.pickUpEffect(this);
	}
	
	//m�g nincs k�sz
	public void pickUpItem(ArrayList<Item> is) {
		System.out.println(">[:Virologus].pickUpItem(is)");
		if (itemHave.size() >= 3) {
			changeItem(itemHave.get(1), is.get(1));
		}
		
	}
	
	//m�g nincs k�sz
	public void leaveItem() {
		System.out.println(">[:Virologus].leaveItem()");
	}
}
