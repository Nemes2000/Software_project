package whut;
import java.util.Random;

//Egy anyagtulajdonsagaiert felel
//int value - mennyi az anyag mennyisege
public abstract class Material {
	private int value;

	// Konstruktor, beallitja random az anyag erteket
	public Material()
	{
		Random rand = new Random();
		value = rand.nextInt(4) + 1;
	}

	// az anyag tipusut adja vissza, csak a leszarmazottak valositjak meg
	protected abstract String getType();

	// megmondja, hogy a parameterkent kapott anyag hasonlo tipusu-e mint ezen amyag
	// Material mat1 - a kapott anyag, ezt hasonlitja ossze
	public boolean isSame(Material mat1) {
		return mat1.getType() == this.getType();
	}

	// visszadaja az anyag mennyiseget
	public int getValue() {
		return value;
	}

	// beellitja az anyag mennyiseget
	// int ujValue - erre az ertekre lesz allitva az anyag mennyisege
	public void setValue(int ujValue) {
		System.out.println(">[:Material].setValue(ujValue) :" + ujValue);
		value = ujValue;
	}
}
