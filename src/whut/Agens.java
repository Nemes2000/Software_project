package whut;

import java.io.Serializable;

public class Agens implements Serializable{
	
	//ezeket a f�ggv�nyeket overridolj�k(opcion�lisan) az �gensek. Alap�rtelmezetten olyan a visszat�r�si �rt�k, hogy ne legyen hat�sa(pl.: nem lehet csak �gy lopni, ez�rt a canStealEffect false)
	
	public boolean canStealEffect() {
		return false;
	}
	public boolean startTurnEffect(AgensUsable v) {
		return true;
	}
	public boolean defendEffect() {
		return false;
	}
	public void destroyEffect(Packet p) {
		return;
	}

	public boolean Check(String s) {
		return false;
	}
}
