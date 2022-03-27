
public class Protection extends Agens{
	
	private int effectTime;
	
	//beállítja a kezdeti hatásidõt
	public Protection() {
		effectTime = 3;
	}
	
	public boolean startTurnEffect(Virologus vir) {
		System.out.println(">[:Protection].startTurnEffect()");
		//csökkenti a hatásidõt, és ha lejárt, akkor kitörli a virológustól
		if(--effectTime < 0) {
			vir.removeAgensOnMe(this);
		}
		
		//még tud mozogni ezert igazzal ter vissza
		return true;
	}
	
	//ha megtámadják akkor megvédi mindenképp a viselõjét
	@Override
	public boolean defendEffect() {
		System.out.println(">[:Protection].defendEffect()");
		return true;
	}
	
}
