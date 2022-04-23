package whut;

public class Stun extends Agens{

	private int effectTime;
	
	//be�ll�tja a kezd� hat�s�rt�ket
	public Stun() {
		effectTime = 3;
	}
	
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		System.out.println(">[:Stun].startTurnEffect()");
		//cs�kkenti a hat�sid�t, �s ha lej�rt, akkor kit�rli a virol�gust�l
		if(--effectTime < 0) {
			au.removeAgensOnMe(this);
			//ha lej�rt akkor m�r tud mozogni
			return true;
		}
		
		//ha nem j�rt le m�g akkor nem tud mozogni
		return false;
	}
	
	//stunnolt, teh�t lehet lopni t�le
	@Override
	public boolean canStealEffect() {
		System.out.println(">[:Stun].canStealEffect()");
		return true;
	}
	
	public boolean Check(String s) {
		if(s.equals("Stun"))
			return true;
		return false;
	}
}
