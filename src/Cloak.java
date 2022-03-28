
public class Cloak extends Item{
	//visszaadja, hogy sikerült-e kivédeni az ágenskenést
	public boolean canCastEffect() {
		System.out.println(">[:Cloak].canCastEffect()");
		return false;
	}
}
