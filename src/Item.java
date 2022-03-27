
public class Item {
	//ezeket a f�ggv�nyeket overridolj�k(opcion�lisan) a Itemek. 
	//Alap�rtelmezetten olyan a visszat�r�si �rt�k, hogy ne legyen hat�sa(pl.: alapb�l nem v�d semmi, ez�rt a canCastEffect true)
	
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
