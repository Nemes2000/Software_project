import java.util.ArrayList;

public abstract class AgensUsable {
	
	private ArrayList<Agens> agens;
	protected ArrayList<Agens> agensOnMe;
	protected Packet packet;
	
	public void addAgens(Agens a)
	{
		agens.add(a);
	}
	public void addAgensOnMe(Agens ag) {
		agensOnMe.add(ag);
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
		addAgensOnMe(ag);
		
	}
	

}
