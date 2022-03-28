package whut;

public class Cloak extends Item{
	//visszaadja, hogy siker�lt-e kiv�deni az �gensken�st
	public boolean canCastEffect() {
		System.out.println(">[:Cloak].canCastEffect()");
		return false;
	}
}
