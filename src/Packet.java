import java.util.ArrayList;


//Egy zseb, ami zabben l�v� anyagok kezel�s��rt felel
//ArrayList<Material> materials - a t�rolt anyagok list�ja
//int maxPerMaterial - a maximum t�rolhat� anyagmennyis�g az egyes anyagt�pusokb�l
public class Packet 
{
	private ArrayList<Material> materials = new ArrayList<Material>();
	private int maxPerMaterial;
	
	//Konstruktor, alapb�l csak a zseb m�ret�t �llitja be
	public Packet()
	{
		maxPerMaterial = 6;
	}
	
	//T�rli a list�ban kapott anyagmennyis�get ezen zsebb�l
	// ArrayList<Material> mats - ez az a lista amiket ki kell venni ebb�l a zsebb�l
	//visszat�r�si �rt�ke azt mondja meg, hogy siker�lt-e levonni ezen zsebb�l a list�ban kapott anyagmennyis�geket
	public boolean decreaseMaterial(ArrayList<Material> mats) 
	{	
		System.out.println(">[:Packet].decreaseMaterial(mats)");
		//le m�soljuk a zseb tartalm�t, hogy ha nem tudn�nk levonni akkor semmiss� tudjuk tenni a m�veletet
		/*
		ArrayList<Material> decreas = materials;
		
		
		//v�gig megy�nk a kapott list�n
		for(int i = 0; i<mats.size();i++) 
		{
			//v�gig megyunk ezen zseb anyagtartalm�n
			for(int j = 0; j<decreas.size();i++)
			{
				//ha azonos anyagr�l van sz� akkor cs�kkentj�k a tartalm�t
				if(mats.get(i).isSame(decreas.get(j)))
				{
					//ha ezen zseb �ppen soron l�v� anyag�nak mennyis�gb�l kevesebb van mint amennyi kellene
					if(decreas.get(j).getValue() - mats.get(i).getValue() < 0) 
					{
						mats.get(i).setValue(mats.get(i).getValue()-decreas.get(j).getValue());
						decreas.remove(j);
					}
					
					//ha elegend� van
					else if(decreas.get(j).getValue() - mats.get(i).getValue() > 0)
					{
						decreas.get(j).setValue(decreas.get(j).getValue()-mats.get(i).getValue());
						
						//t�r�ljuk a kapott list�b�l az anyagot mert le lett vonva
						mats.remove(i);
						
						//mivel az i �rt�ke nem v�ltozott de az eddigi elemet t�r�lt�k, 
						//�gy most az i hely�re egy �j anyag ker�lt (r�vid�lt a lista)
						//�s most arra is az eg�sz "this" zsebett �jra kell n�zn�nk 
						j=0;
					}
				}
			}
		}
		
		//megn�zz�k hogy a legv�g�n maradt-e anyag a kapott list�ban
		if(mats.size()==0) {
			System.out.println("Sikeresen levonta a nyersanyagokat.");
			materials = decreas;
			return true;
		}
		else
		{
			System.out.println("Sikertelenul vonta le a nyersanyagokat.");
			return false;
		}
		 */
		return true;
	}
	
	//megv�ltoztatjuk a maximum t�rolhat� anyagmennyis�get anyagonk�nt
	//int value - az �rt�k amivel megv�ltoztatjuk a maximum t�rolhat� anyagmennyis�get
	public void changeMaxMaterial(int value) 
	{
		System.out.println(">[:Packet].changeMaxMaterial(value)");
		System.out.println("Az anyag tarolas nott:"+value+"-val");
		maxPerMaterial += value;
	}
	
	//lekezeli azt az esetet, hogy ha a zseb m�ret�nek cs�kkent�sekor anyagot is kellene kidobnunk a zsebb�l
	//int value - az �rt�k amivel cs�kken a zseb max t�rol�si kapac�t�sa
	public void handlePossibleLostMaterial(int value)
	{
		System.out.println(">[:Packet].handlePossibleLostMaterial(value)");
		
		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van
		int matsMaterialADb = 0;  //mennyi aminosavunk van
		
		//v�gig megy�nk a zseb tartalm�n �s megsz�moljuk mib�l mennyink van
		for(Material m : materials) {
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		//ha t�bb aminosavunk van mint amennyi lehetne a zseb m�ret�nek lecs�kkent�se ut�n
		if(matsMaterialADb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi aminosav: "+(matsMaterialADb-maxPerMaterial-value));
			Aminosav a = new Aminosav();
			
			//az ujonnan l�trehozott aminosav �rt�k�t be�ll�tjuk arra 
			//amennyivel kellene cs�kkenteni a zseb aminosav k�szlet�t
			a.setValue(matsMaterialADb-maxPerMaterial-value);
			
			//a f�ggv�ny param�terez�se miatt bele kell rakni egy list�ba ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();  
			m.add(a);
			
			//levonjuk ezen zsebb�l a t�lcsordul�st
			this.decreaseMaterial(m);
		}
		//ha pedig nincs t�lcsordul�s akkor j�k vagyunk
		else
			System.out.println("Nem veszett el aminosav");
		
		
		//ha t�bb nukleotidunk van mint amennyi lehetne a zseb m�ret�nek lecs�kkent�se ut�n
		if(matsMaterialNDb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi nukleotid: "+(matsMaterialNDb-maxPerMaterial-value));
			Nukleotid a = new Nukleotid();
			
			//az ujonnan l�trehozott aminosav �rt�k�t be�ll�tjuk arra 
			//amennyivel kellene cs�kkenteni a zseb aminosav k�szlet�t
			a.setValue(matsMaterialADb-maxPerMaterial-value);
			
			//a f�ggv�ny param�terez�se miatt bele kell rakni egy list�ba ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();
			m.add(a);
			
			//levonjuk ezen zsebb�l a t�lcsordul�st
			this.decreaseMaterial(m);
		}
		//ha pedig nincs t�lcsordul�s akkor j�k vagyunk
		else
			System.out.println("Nem veszett el nukleotid");

		//�t�l�tjuk a zseb m�ret�t
		this.changeMaxMaterial(-value);
	}
	
	//Mindig megh�v�dik amikor felvesz�nk egy anyagot
	//abban az esetben ha egy anyagnak a m�rete nagyobb lenne mint amennyit fel tudn�nk venni azt le is kezeli
	//Material mat - azon anyag amit fel akar venni a j�t�kos
	//Packet pac - azon j�t�kos zsebe aki fel akarja venni az anyagot
	public void handleMaterialSeparate(Material mat, Packet pac) 
	{
		System.out.println(">[:Packet].handleMaterialSeparate(mat, pac)");
		/*
		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van
		int matsMaterialADb = 0;  //mennyi aminosavunk van
		
		//v�gig megy�nk a kapott zseb tartalm�n �s megsz�moljuk mib�l mennyink van
		for(Material m : pac.getMaterials()) 
		{
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		//ebbe az anyagba fog felez�dni a kapott anyag ha kell
		Material m = null;
		
		//amennyiben nukleotidr�l van sz�
		if(mat.isSame(new Nukleotid())) 
		{
			//�s nem tudjuk az eg�sz anyagot felvenni, mert kicsi a zseb m�rete
			if(matsMaterialNDb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Fevette a Nukleotidot, de nem az eg�szet.");
				
				//megfelezz�k az anyagot
				m = new Nukleotid();
				
				//azon �rt�kre �ll�tjuk be amit maxim�lisan fel tud venni
				m.setValue(maxPerMaterial-matsMaterialNDb);
				
				//ezen anyagot hozz�adjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag �rt�k�t pedig lecs�kkentj�k
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialNDb));
			}
			//ha feltudja venni az eg�sz anyagot
			else
			{
				System.out.println("Felvette az Nukleotidot.");
				
				//hozz�adjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
		//amennyiben aminosavr�l van sz�
		else
		{	
			//�s nem tudjuk az eg�sz anyagot felvenni, mert kicsi a zseb m�rete
			if(matsMaterialADb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Sikeresen fevette az Aminosavat, de nem az eg�szet.");
				m = new Aminosav();
				
				//azon �rt�kre �ll�tjuk be amit maxim�lisan fel tud venni
				m.setValue(maxPerMaterial-matsMaterialADb);
				
				//ezen anyagot hozz�adjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag �rt�k�t pedig lecs�kkentj�k
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialADb));
			}
			//ha feltudja venni az eg�sz anyagot
			else
			{
				System.out.println("Sikeresen felvette az Aminosavat.");
				
				//hozz�adjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
			
		//ha kett� lett v�lasztva az anyag
		if(m != null) 
		{
			//kelleni fog majd az anyag kiv�tel�hez, a decreasMateral() f�ggv�ny param�tere miatt
			ArrayList<Material> material = new ArrayList<Material>();
			
			//a megfelezett anyagot hozz�adjuk
			material.add(m);
			
			// a megfelezett anyagot levonjuk ezen zsebb�l
			this.decreaseMaterial(material);
		}	
	*/
	}
	
	//vissza adja a zseb anyaglist�j�t
	public ArrayList<Material> getMaterials() 
	{
		//System.out.println(">[:Packet].getMaterials()");
		return materials;
	}
	
	//A param�terk�nt kapott anyagot hozz�adja a zsebhez
	//MAterial mat - azon anyag amit a zsebhez adunk
	public void addMaterial(Material mat)
	{
		System.out.println(">[:Packet].addMatarial");
		this.materials.add(mat);
	}
	
}
