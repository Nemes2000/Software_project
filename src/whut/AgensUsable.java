package whut;
import java.util.ArrayList;

public class AgensUsable extends Entity{
	
	protected ArrayList<Agens> agens = new ArrayList<Agens>();
	protected ArrayList<Agens> agensOnMe = new ArrayList<Agens>();
	protected ArrayList<GeneticCode> geneticCode = new ArrayList<GeneticCode>();
	protected Packet materialPacket = new Packet();
	
	
	//list�khoz sim�n hozz�adja �s elveszi
	public void addAgens(Agens a) {
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
		return materialPacket;
	}
	

	
	//ha egy �gens is leb�n�t�, akkor false-ot add vissza
	public boolean roundDesc() {
		//minden startTurneffect lefut, akkor is, ha m�r volt stunnol�
		for(int i = 0; i < agensOnMe.size(); i++){
			if(!agensOnMe.get(i).startTurnEffect(this)) {
				MyRunnable.getGame().myNotify();
				 return false;
			}
		}
		return true;
	}
	
	//megt�madjuk ezt az agensusable-t
	public void uRAttacked(Agens ag, Virologus v) {
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
		for (GeneticCode gc2 : geneticCode){
			if (gc2.Check(gc.toString().substring(0, gc.toString().length()-4)))
				return;
		}
		geneticCode.add(gc);
	}
	
	//megk�rdezi a felhaszn�l�t melyik genetik k�dot szeretn� �genss� alak�tani �s azt megcsin�lja
	public void createAgens(String mit) {
		boolean created = false;
		int i = 0;
		while(!created && i < geneticCode.size()) {
			if(geneticCode.get(i).Check(mit)) {
				geneticCode.get(i).createAgens(this);
				created = true;
				geneticCode.remove(i);
				switch(mit) {
				case "protection":
					geneticCode.add(new ProtectionCode());
					break;
				case "vitusdance":
					geneticCode.add(new VitusdanceCode());
					break;
				case "stun":
					geneticCode.add(new StunCode());
					break;
				case "forget":
					geneticCode.add(new ForgetCode());
					break;
				}
			}
			i++;
		}
		
		if(!created)
			MyRunnable.log("Genetic code for " + mit + " not learned yet!");
	}
	//elfelejt minden genetikk�dot
	public void forgetAll() {
		geneticCode.removeAll(geneticCode);
	}
	
	//elvileg ez �sszevonja a kapott packet-et a saj�tj�val?
	public void increaseMaterial(Packet p, Material m) {
		p.handleMaterialSeparate(m, this.materialPacket);
	}
	
	//ennek kene egy parameter, hogy melyik agenst hasznalja
	public void useAgens(Virologus v, Agens ag) {
		if (!MyRunnable.getGame().getMegy()) return;
		MyRunnable.getGame().BearAll();
		agens.remove(ag);
		v.uRAttacked(ag, (Virologus)this);
		MyRunnable.getGame().BearAll();
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
