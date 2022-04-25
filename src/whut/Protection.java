package whut;

public class Protection extends Agens{
	
	private int effectTime;
	
	//be�ll�tja a kezdeti hat�sid�t
	public Protection() {
		effectTime = 3;
	}
	
	public boolean startTurnEffect(AgensUsable au) {
		//cs�kkenti a hat�sid�t, �s ha lej�rt, akkor kit�rli a virol�gust�l
		if(--effectTime < 0) {
			au.removeAgensOnMe(this);
		}
		
		//m�g tud mozogni ezert igazzal ter vissza
		return true;
	}
	
	//ha megt�madj�k akkor megv�di mindenk�pp a visel�j�t
	@Override
	public boolean defendEffect() {
		return true;
	}
	
	public boolean Check(String s) {
		if(s.equals("protection"))
			return true;
		return false;
	}
	
	public String toString() {
		return "protection";
	}
	
}
