package whut;

public class Axe extends Item{
	private boolean used = false;
	
	public boolean killEffect(Virologus v) {
		if (used) return false;
		v.die();
		used = true;
		return true;
	}

}
