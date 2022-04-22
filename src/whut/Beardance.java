package whut;

public class Beardance extends Agens{
	public boolean startTurnEffect(Virologus v) {
		infectAll(v.getField().getVirologusok(),v);
		v.move(v.getField().getNeighborhood().);
		infectAll(v.getField().getVirologusok(),v);		
		return false;
	}
	public void destroyEffect(Packet p) {
		p.handlePossiblyLostMaterial(p.getMaxMaterial());
	}
	public void infectAll(ArrayList<Virologus> vs, Virologus v) {
		for(Virologus vc : vs) {
			Beardance b = new Beardance();
			v.addAgens(b);
			vc.uRAttacked(b,v);
			
		}
	}
}
