
public class Sack extends Item{
	private int increase;
	public void pickUpEffect(Virologus v) {
		System.out.println(">[:Sack].pickUpEffect(v)");
		Packet p = v.getPacket();
		p.changeMaxMaterial(increase);
	}
	
	public void lostEffect(Virologus v) {
		System.out.println(">[:Sack].lostEffect(v)");
		Packet p = v.getPacket();
		p.handlePossibleLostMaterial(increase);
	}
}
