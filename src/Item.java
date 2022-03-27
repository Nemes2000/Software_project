
public class Item {
	//ezeket a függvényeket overridolják(opcionálisan) a Itemek. 
	//Alapértelmezetten olyan a visszatérési érték, hogy ne legyen hatása(pl.: alapból nem véd semmi, ezért a canCastEffect true)
	
	public boolean canCastEffect() {
	System.out.println(">[:Item].canCastEffect()");
	return true;
	}

	public void pickUpEffect(Virologus v) {
		System.out.println(">[:Item].pickUpEffect(v)");
	}

	public void lostEffect(Virologus v) {
		System.out.println(">[:Item].lostEffect(v)");
	}

	public boolean fireBackEffect(Virologus tamado, Agens a) {
		System.out.println(">[:Item].fireBackEffect(tamado, a)");
		return false;
	}
}
