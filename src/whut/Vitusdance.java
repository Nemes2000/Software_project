package whut;
import java.util.ArrayList;
import java.util.Random;

public class Vitusdance extends Agens{
	
	
	//A k�r elej�n h�v�dik meg, a param�ter�l kapott virol�gust 3-szor egy random szomsz�dos mez�re mozgatja, majd kit�rli mag�t. 
	//Mindig igazzal t�r vissza, mert a hat�s ut�n m�g mozoghat.
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		//k�r elej�n h�romszor random l�pteti
		Field all;
		ArrayList<Field> osszesSzomszed = new ArrayList<Field>();
		int randomSzomsz;
		Random rand = new Random();
		for(int i=0;i<3;i++) {
			all = au.getField();
			osszesSzomszed = all.getNeighbourhood();
			randomSzomsz = rand.nextInt(osszesSzomszed.size());
			au.move(osszesSzomszed.get(randomSzomsz));
		}
		//kit�rli a virol�guson l�v� �gensek k�z�l
		au.removeAgensOnMe(this);
		//visszat�r igazzal, mert m�g tud ut�na l�pni
		return true;
	}
	
	public boolean Check(String s) {
		if(s.equals("vitusdance"))
			return true;
		return false;
	}
	
	public String toString() {
		return "vitusdance";
	}
	
}
