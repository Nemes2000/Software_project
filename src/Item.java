
public class Item {
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
