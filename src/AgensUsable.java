import java.util.ArrayList;

public abstract class AgensUsable {
	
	private ArrayList<Agens> agens;
	protected ArrayList<Agens> agensOnMe;
	protected ArrayList<GeneticCode> geneticCode;
	protected Packet packet;
	
	
	//list�khoz sim�n hozz�adja �s elveszi
	public void addAgens(Agens a)
	{
		agens.add(a);
	}
	public void addAgensOnMe(Agens ag) {
		agensOnMe.add(ag);
	}
	public void removeAgens(Agens ag) {
		agens.remove(ag);
	}
	public void removeAgensOnMe(Agens ag) {
		agensOnMe.remove(ag);
	}
	
	public Packet getPacket() {
		return packet;
	}
	

	
	//ha egy �gens is leb�n�t�, akkor false-ot add vissza
	public boolean roundDesc() {
		boolean canStep = true;
		//minden startTurneffect lefut, akkor is, ha m�r volt stunnol�
		foreach(Agens ag: agensOnMe){
			if(!ag.startTurnEffect) {
				canStep=false;
			}
		}
		return canStep;
	}
	
	//megt�madj�k ezt az agensusable-t
	@Override
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:Virologus].uRAttacked()");
		//k�ld�t�lk kit�rli az �genst
		v.removeAgens(ag);
		//ellen�rzi, hogy van-e v�dve valami �ltal
		boolean isProtected = false;
		foreach(Agens ag: agensOnMe){
			if(ag.defendEffect()) {
				isProtected=true;
			}
		}
		if(!isProtected) {
			addAgensOnMe(ag);
		}
		
		
	}
	
	//megtanul egy genetikk�dot
	public void learnGeneticCode(GeneticCode gc) {
		geneticCode.add(gc);
	}
	
	//megk�rdezi a felhaszn�l�t melyik genetik k�dot szeretn� �genss� alak�tani �s azt megcsin�lja
	public void createAgens() {
		geneticCode.get(1).createAgens(this);
	}
	//elfelejt minden genetikk�dot
	public void forgetAll() {
		geneticCode.removeAll();
	}
	
	//elvileg ez �sszevonja a kapott packet-et a saj�tj�val?
	public void increaseMaterial(Packet p) {
		
	}

}
