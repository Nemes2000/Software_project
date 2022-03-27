
public class Protection extends Agens{
	
	private int effectTime;
	
	//beállítja a kezdeti hatásidõt
	public Protection() {
		effectTime = 3;
	}
	
	public boolean startTurnEffect(Virologus vir) {
		//csökkenti a hatásidõt, és ha lejárt, akkor kitörli a virológustól
		if(--effectTime < 0) {
			vir.removeAgensOnMe(this);
		}
		
		
		return true;
	}
	
	//ha megtámadják akkor megvédi mindenképp a viselõjét
	@Override
	public boolean defendEffect() {
		return true;
	}
	
}
