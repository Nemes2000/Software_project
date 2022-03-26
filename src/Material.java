import java.util.Random;


//Egy anyagtulajdonágaiért felel
//int value - mennyi az anyag mennyisége
public abstract class Material
{	
	private int value;
	
	//Konstruktor, beállítja random az anyag értékét
	public Material()
	{
		Random rand = new Random();
		value = rand.nextInt(5);
	}
	
	//az anyag tipusát adja vissza, csak a leszármazottak valósítják meg
	abstract protected String getType();
	
	//megmondja, hogy a paraméterként kapott anyag hasonló típusú-e mint ezen amyag
	//Material mat1 - a kapott anyag, ezt hasonlítja össze
	public boolean isSame(Material mat1) 
	{
		return mat1.getType() == this.getType();
	}
	
	//visszadaja az anyag mennyiségét
	public int getValue()
	{
		return value;
	}
	
	//beállítja az anyag mennyiségét
	//int ujValue - erre az értékre lesz állítva az anyag mennyisége
	public void setValue(int ujValue) 
	{
		value = ujValue;
	}
}
