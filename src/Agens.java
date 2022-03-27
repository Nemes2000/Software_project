
public class Agens {
	
	//ezeket a függvényeket overridolják(opcionálisan) az ágensek. Alapértelmezetten olyan a visszatérési érték, hogy ne legyen hatása(pl.: nem lehet csak úgy lopni, ezért a canStealEffect false)
	
	public boolean canStealEffect() {
		System.out.println(">[:Agens].canStealEffect()");
		return false;
	}
	public boolean startTurnEffect(AgensUsable au) {
		System.out.println(">[:Agens].startTurnEffect()");
		return true;
	}
	public boolean defendEffect() {
		System.out.println(">[:Agens].defendEffect()");
		return false;
	}
}
