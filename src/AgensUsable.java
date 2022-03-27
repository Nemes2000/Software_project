import java.util.ArrayList;

public abstract class AgensUsable extends Entity{
	
	private ArrayList<Agens> agens;
	protected ArrayList<Agens> agensOnMe;
	protected ArrayList<GeneticCode> geneticCode;
	protected Packet packet;
	
	
	//listákhoz simán hozzáadja és elveszi
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
		return packet;
	}
	

	
	//ha egy ágens is lebénító, akkor false-ot add vissza
	public boolean roundDesc() {
		System.out.println(">[:AgensUsable].roundDesc()");
		boolean canStep = true;
		//minden startTurneffect lefut, akkor is, ha már volt stunnoló
		for(Agens a: agensOnMe){
			if(!a.startTurnEffect()) {
				canStep=false;
			}
		}
		return canStep;
	}
	
	//megtámadják ezt az agensusable-t
	@Override
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:AgensUsable].uRAttacked()");
		//küldõtõlk kitörli az ágenst
		v.removeAgens(ag);
		//ellenõrzi, hogy van-e védve valami által
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
	
	//megtanul egy genetikkódot
	public void learnGeneticCode(GeneticCode gc) {
		System.out.println(">[:AgensUsable].learnGeneticCode()");
		geneticCode.add(gc);
	}
	
	//megkérdezi a felhasználót melyik genetik kódot szeretné ágenssé alakítani és azt megcsinálja
	public void createAgens() {
		System.out.println(">[:AgensUsable].createAgens()");
		geneticCode.get(1).createAgens(this);
	}
	//elfelejt minden genetikkódot
	public void forgetAll() {
		System.out.println(">[:AgensUsable].forgetAll()");
		geneticCode.removeAll();
	}
	
	//elvileg ez összevonja a kapott packet-et a sajátjával?
	public void increaseMaterial(Packet p) {
		System.out.println(">[:AgensUsable].increaseMaterial()");
		
	}

}
