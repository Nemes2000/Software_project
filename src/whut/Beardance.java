package whut;

public class Beardance extends Agens{
	@Override
	public boolean startTurnEffect(Virologus v) {
		infectAll(v.getField().getVirologusok(),v);
		v.move(v.getField().getNeighborhood().get(0));
		infectAll(v.getField().getVirologusok(),v);		
		return false;
	}
	@Override
	public void destroyEffect(Packet p) {
		p.handlePossiblyLostMaterial(p.getMaxMaterial());
	}
	@Override
	public void infectAll(ArrayList<Virologus> vs, Virologus v) {
		for(Virologus vc : vs) {
			Beardance b = new Beardance();
			v.addAgens(b);
			vc.uRAttacked(b,v);		
		}
	}
}
