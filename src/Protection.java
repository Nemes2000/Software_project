
public class Protection extends Agens{
	
	private int effectTime;
	
	//be�ll�tja a kezdeti hat�sid�t
	public Protection() {
		effectTime = 3;
	}
	
	public boolean startTurnEffect(Virologus vir) {
		//cs�kkenti a hat�sid�t, �s ha lej�rt, akkor kit�rli a virol�gust�l
		if(--effectTime < 0) {
			vir.removeAgensOnMe(this);
		}
		
		
		return true;
	}
	
	//ha megt�madj�k akkor megv�di mindenk�pp a visel�j�t
	@Override
	public boolean defendEffect() {
		return true;
	}
	
}
