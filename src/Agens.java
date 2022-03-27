
public class Agens {
	
	//ezeket a függvényeket overridolják(opcionálisan) az ágensek. Alapértelmezetten olyan a visszatérési érték, hogy ne legyen hatása(pl.: nem lehet csak úgy lopni, ezért a canStealEffect false)
	
	public boolean canStealEffect() {
		return false;
	}
	public boolean startTurnEffect(Virologus vir) {
		return true;
	}
	public boolean defendEffect() {
		return false;
	}
}
