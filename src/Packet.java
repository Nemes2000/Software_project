import java.util.ArrayList;
import java.util.List;


//Egy zseb, ami zabben lévõ anyagok kezeléséért felel
//ArrayList<Material> materials - a tárolt anyagok listája
//int maxPerMaterial - a maximum tárolható anyagmennyiség az egyes anyagtípusokból
public class Packet 
{
	private ArrayList<Material> materials;
	private int maxPerMaterial;
	
	//Konstruktor, alapból csak a zseb méretét állitja be
	public Packet()
	{
		maxPerMaterial = 6;
	}
	
	//Törli a listában kapott anyagmennyiséget ezen zsebbõl
	// ArrayList<Material> mats - ez az a lista amiket ki kell venni ebbõl a zsebbõl
	//visszatérési értéke azt mondja meg, hogy sikerült-e levonni ezen zsebbõl a listában kapott anyagmennyiségeket
	public boolean decreaseMaterial(ArrayList<Material> mats) 
	{	
		System.out.println(">[:Packet].decreaseMaterial(mats)");
		//le másoljuk a zseb tartalmát, hogy ha nem tudnánk levonni akkor semmissé tudjuk tenni a mûveletet
		ArrayList<Material> decreas = materials;
		
		//végig megyünk a kapott listán
		for(int i = 0; i<mats.size();i++) 
		{
			//végig megyunk ezen zseb anyagtartalmán
			for(int j = 0; j<decreas.size();i++)
			{
				//ha azonos anyagról van szó akkor csökkentjük a tartalmát
				if(mats.get(i).isSame(decreas.get(j)))
				{
					//ha ezen zseb éppen soron lévõ anyagának mennyiségbõl kevesebb van mint amennyi kellene
					if(decreas.get(j).getValue() - mats.get(i).getValue() < 0) 
					{
						mats.get(i).setValue(mats.get(i).getValue()-decreas.get(j).getValue());
						decreas.remove(j);
					}
					
					//ha elegendõ van
					else if(decreas.get(j).getValue() - mats.get(i).getValue() > 0)
					{
						decreas.get(j).setValue(decreas.get(j).getValue()-mats.get(i).getValue());
						
						//töröljuk a kapott listábõl az anyagot mert le lett vonva
						mats.remove(i);
						
						//mivel az i értéke nem változott de az eddigi elemet töröltük, 
						//így most az i helyére egy új anyag került (rövidült a lista)
						//és most arra is az egész "this" zsebett újra kell néznünk 
						j=0;
					}
				}
			}
		}
		
		//megnézzük hogy a legvégén maradt-e anyag a kapott listában
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
	}
	
	//megváltoztatjuk a maximum tárolható anyagmennyiséget anyagonként
	//int value - az érték amivel megváltoztatjuk a maximum tárolható anyagmennyiséget
	public void changeMaxMaterial(int value) 
	{
		System.out.println(">[:Packet].changeMaxMaterial(value)");
		System.out.println("Az anyag tarolas nott:"+value+"-val");
		maxPerMaterial += value;
	}
	
	//lekezeli azt az esetet, hogy ha a zseb méretének csökkentésekor anyagot is kellene kidobnunk a zsebbõl
	//int value - az érték amivel csökken a zseb max tárolási kapacítása
	public void handlePossibleLostMaterial(int value)
	{
		System.out.println(">[:Packet].handlePossibleLostMaterial(value)");
		
		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van
		int matsMaterialADb = 0;  //mennyi aminosavunk van
		
		//végig megyünk a zseb tartalmán és megszámoljuk mibõl mennyink van
		for(Material m : materials) {
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		//ha több aminosavunk van mint amennyi lehetne a zseb méretének lecsökkentése után
		if(matsMaterialADb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi aminosav: "+(matsMaterialADb-maxPerMaterial-value));
			Aminosav a = new Aminosav();
			
			//az ujonnan létrehozott aminosav értékét beállítjuk arra 
			//amennyivel kellene csökkenteni a zseb aminosav készletét
			a.setValue(matsMaterialADb-maxPerMaterial-value);
			
			//a függvény paraméterezése miatt bele kell rakni egy listába ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();  
			m.add(a);
			
			//levonjuk ezen zsebbõl a túlcsordulást
			this.decreaseMaterial(m);
		}
		//ha pedig nincs túlcsordulás akkor jók vagyunk
		else
			System.out.println("Nem veszett el aminosav");
		
		
		//ha több nukleotidunk van mint amennyi lehetne a zseb méretének lecsökkentése után
		if(matsMaterialNDb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi nukleotid: "+(matsMaterialNDb-maxPerMaterial-value));
			Nukleotid a = new Nukleotid();
			
			//az ujonnan létrehozott aminosav értékét beállítjuk arra 
			//amennyivel kellene csökkenteni a zseb aminosav készletét
			a.setValue(matsMaterialADb-maxPerMaterial-value);
			
			//a függvény paraméterezése miatt bele kell rakni egy listába ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();
			m.add(a);
			
			//levonjuk ezen zsebbõl a túlcsordulást
			this.decreaseMaterial(m);
		}
		//ha pedig nincs túlcsordulás akkor jók vagyunk
		else
			System.out.println("Nem veszett el nukleotid");

		//átálítjuk a zseb méretét
		this.changeMaxMaterial(-value);
	}
	
	//Mindig meghívódik amikor felveszünk egy anyagot
	//abban az esetben ha egy anyagnak a mérete nagyobb lenne mint amennyit fel tudnánk venni azt le is kezeli
	//Material mat - azon anyag amit fel akar venni a játékos
	//Packet pac - azon játékos zsebe aki fel akarja venni az anyagot
	public void handleMaterialSeparate(Material mat, Packet pac) 
	{
		System.out.println(">[:Packet].handleMaterialSeparate(mat, pac)");

		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van
		int matsMaterialADb = 0;  //mennyi aminosavunk van
		
		//végig megyünk a kapott zseb tartalmán és megszámoljuk mibõl mennyink van
		for(Material m : pac.getMaterials()) 
		{
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		//ebbe az anyagba fog felezõdni a kapott anyag ha kell
		Material m = null;
		
		//amennyiben nukleotidról van szó
		if(mat.isSame(new Nukleotid())) 
		{
			//és nem tudjuk az egész anyagot felvenni, mert kicsi a zseb mérete
			if(matsMaterialNDb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Fevette a Nukleotidot, de nem az egészet.");
				
				//megfelezzük az anyagot
				m = new Nukleotid();
				
				//azon értékre állítjuk be amit maximálisan fel tud venni
				m.setValue(maxPerMaterial-matsMaterialNDb);
				
				//ezen anyagot hozzáadjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag értékét pedig lecsökkentjük
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialNDb));
			}
			//ha feltudja venni az egész anyagot
			else
			{
				System.out.println("Felvette az Nukleotidot.");
				
				//hozzáadjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
		//amennyiben aminosavról van szó
		else
		{	
			//és nem tudjuk az egész anyagot felvenni, mert kicsi a zseb mérete
			if(matsMaterialADb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Sikeresen fevette az Aminosavat, de nem az egészet.");
				m = new Aminosav();
				
				//azon értékre állítjuk be amit maximálisan fel tud venni
				m.setValue(maxPerMaterial-matsMaterialADb);
				
				//ezen anyagot hozzáadjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag értékét pedig lecsökkentjük
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialADb));
			}
			//ha feltudja venni az egész anyagot
			else
			{
				System.out.println("Sikeresen felvette az Aminosavat.");
				
				//hozzáadjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
			
		//ha ketté lett választva az anyag
		if(m != null) 
		{
			//kelleni fog majd az anyag kivételéhez, a decreasMateral() függvény paramétere miatt
			ArrayList<Material> material = new ArrayList<Material>();
			
			//a megfelezett anyagot hozzáadjuk
			material.add(m);
			
			// a megfelezett anyagot levonjuk ezen zsebbõl
			this.decreaseMaterial(material);
		}	
	
	}
	
	//vissza adja a zseb anyaglistáját
	public ArrayList<Material> getMaterials() 
	{
		//System.out.println(">[:Packet].getMaterials()");
		return materials;
	}
	
	//A paraméterként kapott anyagot hozzáadja a zsebhez
	//MAterial mat - azon anyag amit a zsebhez adunk
	public void addMaterial(Material mat)
	{
		this.materials.add(mat);
	}
	
}
