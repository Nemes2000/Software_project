package whut;

//Egy balta t�rgy�rt felel, a balt�val virol�gusokat lehet �lni, de egy haszn�lat ut�n kicsorbul, t�bbet nem lehet haszn�lni.
public class Axe extends Item{
	private boolean used = false;
	
	//A param�terk�nt kapott virol�gust meg�li (megh�vja rajta a die() f�ggv�nyt). Igazzal t�r vissza.
	public boolean killEffect(Virologus v) {
		if (used) return false;
		v.die();
		used = true;
		return true;
	}

	@Override
	public boolean Check(String s) {
		if(s.equals("axe")) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "axe";
	}
}
