package whut;

import java.util.ArrayList;

public class Beardance extends Agens{
	
	//A mezõn amin áll a paraméterül kapott virológus megfertõz minden virológust, majd a paraméterül kapott virológust egy random szomszédos mezõre mozgatja. 
	//Ezután az új mezõn is megfertõz mindenkit. 
	//Végül hamissal tér vissza, mivel a birtokos virológus mást nem csinálhat a körében.
	@Override
	public boolean startTurnEffect(AgensUsable v) {
		infectAll(v.getField().getVirologusok(),v);
		if(v.getField().getNeighbourhood().size() > 0)
			v.move(v.getField().getNeighbourhood().get(0));
		infectAll(v.getField().getVirologusok(),v);		
		return false;
	}
	
	//A paraméterül kapott csomagnak lekéri a maximális értékét, majd a maximális értékébõl levonja ezt az értéket, ezzel nullára állítva.
	@Override
	public void destroyEffect(Packet p) {
		p.handlePossibleLostMaterial(p.getMaxMaterial());
	}
	
	//A paraméterül kapott virológuslistából mindenkit megfertõz Beardance ágenssel az alábbi módon: 
	//Csinál egy Beardance ágenst, amit odaad a paraméterül kapott virológusnak. 
	//Ezután a lista elem virológust a viselõ virológus nevében a csinált ágenssel megtámadja.
	public void infectAll(ArrayList<AgensUsable> vs, AgensUsable a) {
		for(AgensUsable vc : vs) {
			if(!vc.equals(a)) {
				Beardance b = new Beardance();
				a.addAgens(b);
				Virologus v = (Virologus)vc;
				//vc.uRAttacked(b,v);
				a.useAgens(v, b);
			}
		}
	}
	
	public boolean Check(String s) {
		if(s.equals("Beardance"))
			return true;
		return false;
	}
	
	public String toString() {
		return "beardance";
	}
}
