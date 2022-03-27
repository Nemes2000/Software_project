//egy konkr�t anyagt�pus�rt felel
public class Aminosav extends Material {
	// Konstruktor, amit az �s�vel egyenl�
	public Aminosav() {
		super();
	}

	// megval�s�tott f�ggv�ny, mely az �sben nincs implement�lva
	// visszaadja a t�pus�t az anyagnak
	@Override
	protected String getType() {
		return "Aminosav";
	}

}
