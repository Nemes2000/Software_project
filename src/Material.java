import java.util.Random;


//Egy anyagtulajdon�gai�rt felel
//int value - mennyi az anyag mennyis�ge
public abstract class Material
{	
	private int value;
	
	//Konstruktor, be�ll�tja random az anyag �rt�k�t
	public Material()
	{
		Random rand = new Random();
		value = 1 + rand.nextInt(4);
	}
	
	//az anyag tipus�t adja vissza, csak a lesz�rmazottak val�s�tj�k meg
	abstract protected String getType();
	
	//megmondja, hogy a param�terk�nt kapott anyag hasonl� t�pus�-e mint ezen amyag
	//Material mat1 - a kapott anyag, ezt hasonl�tja �ssze
	public boolean isSame(Material mat1) 
	{
		return mat1.getType() == this.getType();
	}
	
	//visszadaja az anyag mennyis�g�t
	public int getValue()
	{
		return value;
	}
	
	//be�ll�tja az anyag mennyis�g�t
	//int ujValue - erre az �rt�kre lesz �ll�tva az anyag mennyis�ge
	public void setValue(int ujValue) 
	{
		value = ujValue;
	}
}
