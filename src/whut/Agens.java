package whut;

public class Agens {
	
	//ezeket a f�ggv�nyeket overridolj�k(opcion�lisan) az �gensek. Alap�rtelmezetten olyan a visszat�r�si �rt�k, hogy ne legyen hat�sa(pl.: nem lehet csak �gy lopni, ez�rt a canStealEffect false)
	
	public boolean canStealEffect() {
		System.out.println(">[:Agens].canStealEffect()");
		return false;
	}
	public boolean startTurnEffect(AgensUsable v) {
		System.out.println(">[:Agens].startTurnEffect()");
		return true;
	}
	public boolean defendEffect() {
		System.out.println(">[:Agens].defendEffect()");
		return false;
	}
	public void destroyEffect(Packet p) {
		
		return;
	}
}
