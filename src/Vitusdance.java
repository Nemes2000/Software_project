
public class Vitusdance extends Agens{
	
	
	
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		System.out.println(">[:Vitusdance].startTurnEffect()");
		//kör elejéromszor random lépteti
		Field f = au.getField();
		
		for(int i=0;i<3;i++) {
			au.move(f.getNeighbourhood().size());
		}
		//kit�rli a virol�guson l�v� �gensek k�z�l
		au.removeAgensOnMe(this);
		//visszat�r igazzal, mert m�g tud ut�na l�pni
		return true;
	}
	
}
