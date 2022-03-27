
public class Stun extends Agens{

	private int effectTime;
	
	//beállítja a kezdõ hatásértéket
	public Stun() {
		effectTime = 3;
	}
	
	@Override
	public boolean startTurnEffect(Virologus vir) {
		//csökkenti a hatásidõt, és ha lejárt, akkor kitörli a virológustól
		if(--effectTime < 0) {
			vir.removeAgensOnMe(this);
			//ha lejárt akkor már tud mozogni
			return true;
		}
		
		//ha nem járt le még akkor nem tud mozogni
		return false;
	}
	
	//stunnolt, tehát lehet lopni tõle
	@Override
	public boolean canStealEffect() {
		return true;
	}
}
