package whut;

public class Axe extends Item{
	private boolean used = false;
	
	public boolean killEffect(Virologus v) {
		if (used) return false;
		v.die();
		used = true;
		return true;
	}

	@Override
	public boolean Check(String s) {
		if(s.equals("axe")) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "axe";
	}
}
