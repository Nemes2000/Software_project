import java.util.ArrayList;

public abstract class AgensUsable {
	
	private ArrayList<Agens> agens;
	protected ArrayList<Agens> agensOnMe;
	protected ArrayList<GeneticCode> geneticCode;
	protected Packet packet;
	
	
	//listákhoz simán hozzáadja és elveszi
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
	

	
	//ha egy ágens is lebénító, akkor false-ot add vissza
	public boolean roundDesc() {
		boolean canStep = true;
		//minden startTurneffect lefut, akkor is, ha már volt stunnoló
		foreach(Agens ag: agensOnMe){
			if(!ag.startTurnEffect) {
				canStep=false;
			}
		}
		return canStep;
	}
	
	//megtámadják ezt az agensusable-t
	@Override
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:Virologus].uRAttacked()");
		//küldõtõlk kitörli az ágenst
		v.removeAgens(ag);
		//ellenõrzi, hogy van-e védve valami által
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
	
	//megtanul egy genetikkódot
	public void learnGeneticCode(GeneticCode gc) {
		geneticCode.add(gc);
	}
	
	//megkérdezi a felhasználót melyik genetik kódot szeretné ágenssé alakítani és azt megcsinálja
	public void createAgens() {
		geneticCode.get(1).createAgens(this);
	}
	//elfelejt minden genetikkódot
	public void forgetAll() {
		geneticCode.removeAll();
	}
	
	//elvileg ez összevonja a kapott packet-et a sajátjával?
	public void increaseMaterial(Packet p) {
		
	}

}
