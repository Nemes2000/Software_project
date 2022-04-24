package whut;
import java.util.ArrayList;

public class AgensUsable extends Entity{
	
	private ArrayList<Agens> agens = new ArrayList<Agens>();
	protected ArrayList<Agens> agensOnMe = new ArrayList<Agens>();
	protected ArrayList<GeneticCode> geneticCode = new ArrayList<GeneticCode>();
	protected Packet materialPacket = new Packet();
	
	
	//list�khoz sim�n hozz�adja �s elveszi
	public void addAgens(Agens a)
	{
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
		return materialPacket;
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
	
	//megt�madjuk ezt az agensusable-t
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
	public void createAgens(String mit) {
		System.out.println(">[:AgensUsable].createAgens()");
		boolean created = false;
		int i = 0;
		while(!created && i < geneticCode.size()) {
			if(geneticCode.get(i).Check(mit)) {
				geneticCode.get(i).createAgens(this);
				created = true;
			}
			i++;
		}
		
		if(!created)
			MyRunnable.log("Genetic code for " + mit + " not learned yet!");
	}
	//elfelejt minden genetikk�dot
	public void forgetAll() {
		System.out.println(">[:AgensUsable].forgetAll()");
		geneticCode.removeAll(geneticCode);
	}
	
	//elvileg ez �sszevonja a kapott packet-et a saj�tj�val?
	public void increaseMaterial(Packet p, Material m) {
		System.out.println(">[:AgensUsable].increaseMaterial()");
		p.handleMaterialSeparate(m, this.materialPacket);
		
	}
	
	//ennek kene egy parameter, hogy melyik agenst hasznalja
	public void useAgens(Virologus v, Agens ag) {
		agens.remove(ag);
		v.uRAttacked(ag, v);
	}
	public void destroyMaterial(Packet p) {
		for(Agens a : agensOnMe) {
			a.destroyEffect(p);
		}
	}

	
	public ArrayList<GeneticCode> getGeneticCodeHave(){
		return geneticCode;
	}

	public Agens getAgens(String s) {
		for(Agens ag : agens) {
			if(ag.Check(s))return ag;
		}
		return null;
	}

	public ArrayList<Agens> getAgensHave(){
		return agens;
	}
	
	public ArrayList<Agens> getAgensOnMe(){
		return agensOnMe;
	}
}
