package whut;
//egy konkret anyagtipusert felel
public class Aminosav extends Material {
	// Konstruktor, amit az osevel egyenlo
	public Aminosav() {
		super();
	}

	// megvalositott fuggveny, mely az osben nincs implementalva
	// visszaadja a tipusat az anyagnak
	@Override
	protected String getType() {
		return "Aminosav";
	}

}
