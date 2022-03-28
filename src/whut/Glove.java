package whut;

public class Glove extends Item {
	
	//r�keni az �gest a param�terk�nt kapott virol�gusra �gy, hogy az m�r ne tudja visszakenni
	//Virol�gus tamado - akire visszakeni az �genst
	//Agens a - az �gens, amit visszaken
	public boolean fireBackEffect(Virologus tamado, Agens a) {
		System.out.println(">[:Item].fireBackEffect(tamado, a)");
		if (tamado != null) {
			tamado.uRAttacked(a, null);
		}
		return true;
	}
}
