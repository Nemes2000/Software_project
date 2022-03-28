
public class Glove extends Item {
	
	//rákeni az ágest a paraméterként kapott virológusra úgy, hogy az már ne tudja visszakenni
	//Virológus tamado - akire visszakeni az ágenst
	//Agens a - az ágens, amit visszaken
	public boolean fireBackEffect(Virologus tamado, Agens a) {
		System.out.println(">[:Item].fireBackEffect(tamado, a)");
		if (tamado != null) {
			tamado.uRAttacked(a, null);
		}
		return true;
	}
}
