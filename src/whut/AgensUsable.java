package whut;
import java.util.ArrayList;

public abstract class AgensUsable extends Entity{
	
	private ArrayList<Agens> agens = new ArrayList<Agens>();
	protected ArrayList<Agens> agensOnMe = new ArrayList<Agens>();
	protected ArrayList<GeneticCode> geneticCode = new ArrayList<GeneticCode>();
	protected Packet packet = new Packet();
	
	
	//list�khoz sim�n hozz�adja �s elveszi
	public void addAgens(Agens a)
	{
		System.out.println("Uj felhasznalhato agens");
		agens.add(a);
	}
	public void addAgensOnMe(Agens ag) {
		System.out.println("Uj agens kerult ra");
		agensOnMe.add(ag);
	}
	public void removeAgens(Agens ag) {
		System.out.println("Egy felhasznalhato agens kitorlodott");
		agens.remove(ag);
	}
	public void removeAgensOnMe(Agens ag) {
		System.out.println("Egy rajta levo agens kitorlodott");
		agensOnMe.remove(ag);
	}
	
	public Packet getPacket() {
		System.out.println(">[:AgensUsable].getPacket()");
		return packet;
	}
	

	
	//ha egy �gens is leb�n�t�, akkor false-ot add vissza
	public boolean roundDesc() {
		System.out.println(">[:AgensUsable].roundDesc()");
		boolean canStep = true;
		//minden startTurneffect lefut, akkor is, ha m�r volt stunnol�
		for(Agens a: agensOnMe){
			if(!a.startTurnEffect(this)) {
				canStep=false;
			}
		}
		return canStep;
	}
	
	//megt�madj�k ezt az agensusable-t
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:AgensUsable].uRAttacked()");
		//k�ld�t�lk kit�rli az �genst
		v.removeAgens(ag);
		//ellen�rzi, hogy van-e v�dve valami �ltal
		boolean isProtected = false;
		for(Agens a: agensOnMe){
			if(a.defendEffect()) {
				isProtected=true;
			}
		}
		if(!isProtected) {
			addAgensOnMe(ag);
		}
		
		
	}
	
	//megtanul egy genetikk�dot
	public void learnGeneticCode(GeneticCode gc) {
		System.out.println(">[:AgensUsable].learnGeneticCode()");
		geneticCode.add(gc);
	}
	
	//megk�rdezi a felhaszn�l�t melyik genetik k�dot szeretn� �genss� alak�tani �s azt megcsin�lja
	public void createAgens() {
		System.out.println(">[:AgensUsable].createAgens()");
		geneticCode.get(1).createAgens(this);
	}
	//elfelejt minden genetikk�dot
	public void forgetAll() {
		System.out.println(">[:AgensUsable].forgetAll()");
		//geneticCode.removeAll();
	}
	
	//elvileg ez �sszevonja a kapott packet-et a saj�tj�val?
	public void increaseMaterial(Packet p) {
		System.out.println(">[:AgensUsable].increaseMaterial()");
		
	}

}
