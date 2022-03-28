package whut;

public class Protection extends Agens{
	
	private int effectTime;
	
	//be�ll�tja a kezdeti hat�sid�t
	public Protection() {
		effectTime = 3;
	}
	
	public boolean startTurnEffect(AgensUsable au) {
		System.out.println(">[:Protection].startTurnEffect()");
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
		System.out.println(">[:Protection].defendEffect()");
		return true;
	}
	
}
