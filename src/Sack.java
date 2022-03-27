
public class Sack extends Item{
	private int increase;
	
	//megnöveli a virológus anyag tárolókapacitását
	//Virologus v - a virológus, akin kifejti hatását
	public void pickUpEffect(Virologus v) {
		System.out.println(">[:Sack].pickUpEffect(v)");
		Packet p = v.getPacket();
		p.changeMaxMaterial(increase);
	}
	
	//csökkenti a virológus anyag tárolókapacitását
	//Virologus v - a virológus, akin kifejti hatását
	public void lostEffect(Virologus v) {
		System.out.println(">[:Sack].lostEffect(v)");
		Packet p = v.getPacket();
		p.handlePossibleLostMaterial(increase);
	}
}
