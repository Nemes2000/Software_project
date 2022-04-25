package whut;

public class Glove extends Item {
	private int usedTime = 0;
	//r�keni az �gest a param�terk�nt kapott virol�gusra �gy, hogy az m�r ne tudja visszakenni
	//Virol�gus tamado - akire visszakeni az �genst
	//Agens a - az �gens, amit visszaken
	@Override
	public boolean fireBackEffect(Virologus tamado, Virologus hasznalo, Agens a) {
		System.out.println(">[:Item].fireBackEffect(tamado, a)");
		if (tamado != null) {
			tamado.uRAttacked(a, null);
		}
		usedTime++;
		if (usedTime >= 3) {
			hasznalo.removeItem(this);
		}
		return true;
	}
	
	public boolean Check(String it) {
		if(it.equals("glove"))
			return true;
		return false;
	}
	
	public String toString() {
		return "glove";
	}
}
