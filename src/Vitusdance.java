
public class Vitusdance extends Agens{
	
	
	
	@Override
	public boolean startTurnEffect(Virologus v) {
		//k�r elej�n h�romszor random l�pteti
		for(int i=0;i<3;i++) {
			v.move();
		}
		//kit�rli a virol�guson l�v� �gensek k�z�l
		v.removeAgensOnMe(this);
		//visszat�r igazzal, mert m�g tud ut�na l�pni
		return true;
	}
	
}
