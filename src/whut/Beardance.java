package whut;

import java.util.ArrayList;

public class Beardance extends Agens{
	@Override
	public boolean startTurnEffect(AgensUsable v) {
		infectAll(v.getField().getVirologusok(),v);
		v.move(v.getField().getNeighbourhood().get(0));
		infectAll(v.getField().getVirologusok(),v);		
		return false;
	}
	@Override
	public void destroyEffect(Packet p) {
		p.handlePossibleLostMaterial(p.getMaxMaterial());
	}
	
	public void infectAll(ArrayList<AgensUsable> vs, AgensUsable a) {
		for(AgensUsable vc : vs) {
			Beardance b = new Beardance();
			a.addAgens(b);
			Virologus v = (Virologus)a;
			vc.uRAttacked(b,v);		
		}
	}
	
	public boolean Check(String s) {
		if(s.equals("Beardance"))
			return true;
		return false;
	}
}
