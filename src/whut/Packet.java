package whut;
import java.util.ArrayList;


//Egy zseb, ami zabben l�v� anyagok kezel�s��rt felel
//ArrayList<Material> materials - a t�rolt anyagok list�ja
//int maxPerMaterial - a maximum t�rolhat� anyagmennyis�g az egyes anyagt�pusokb�l
public class Packet 
{
	private ArrayList<Material> materials = new ArrayList<Material>();
	private int maxPerMaterial;
	
	//Konstruktor, alapbol csak a zseb meretet allitja be
	public Packet()
	{
		maxPerMaterial = 6;
	}
	
	//Torli a listaban kapott anyagmennyiseget ezen zsebbol
	// ArrayList<Material> mats - ez az a lista amiket ki kell venni ebbol a zsebbol
	//visszateresi erteke azt mondja meg, hogy sikerult-e levonni ezen zsebbol a listaban kapott anyagmennyisegeket
	public boolean decreaseMaterial(ArrayList<Material> mats) 
	{	
		System.out.println(">[:Packet].decreaseMaterial(mats)");
		//le masoljuk a zseb tartalmat, hogy ha nem tudnank levonni akkor semmisse tudjuk tenni a muveletet
		ArrayList<Material> decreas = materials;
		
		//addig megyunk amig az egyik lista ki nem urul
		//ha a mats urul ki akkor meg van a kello anyag mennyiseg
		//ha a decreas urul ki akkor pedig nincs elegendo anyagunk
		while((mats.size() > 0) && (decreas.size() > 0)) {

			int i = 0;
			//vegig megyünk a kapott anyag listan
			while(i < mats.size()) {
				int j = 0;
				//vegig megyunk a sajat listank masolatan
				while(j < decreas.size()) {
					//ha azonos tipusu anyagot talaltunk akkor tehetunk valamit
					if(mats.get(i).isSame(decreas.get(j)))
					{
						//ha ezen zseb eppen soron levo anyaganak mennyisegbol kevesebb van mint amennyi kellene
						if(decreas.get(j).getValue() - mats.get(i).getValue() <= 0) 
						{
							mats.get(i).setValue(mats.get(i).getValue()-decreas.get(j).getValue());
							
							//toroljuk a zsebunkben levo anyagot mert elfogyott
							decreas.remove(j--);
						}
						
						//ha elegendo van
						else
						{
							decreas.get(j).setValue(decreas.get(j).getValue()-mats.get(i).getValue());
							
							//toroljuk a kapott listabol az anyagot mert le lett vonva
							mats.get(i).setValue(0);
						}
					}
					j++;
				}
				//ha le tudtuk vonni ezen zsebbol a kapott listabeli anyagot, akkor a kapott lista anyagat is toroljuk a levonando anyagok kozul
				if(mats.get(i).getValue() == 0) {
					mats.remove(i);
					System.out.print(mats.size());
				}
				else
					i++;
			}
		}
		
		//megnezzuk hogy a legvegen maradt-e anyag a kapott listaban
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
	
	//megvaltoztatjuk a maximum tarolhato anyagmennyiseget anyagonkont
	//int value - az ertek amivel megvaltoztatjuk a maximum tarolhato anyagmennyiseget
	public void changeMaxMaterial(int value) 
	{
		System.out.println(">[:Packet].changeMaxMaterial(value)");
		System.out.println("Az anyag tarolas nott:"+value+"-val");
		maxPerMaterial += value;
	}
	
	//lekezeli azt az esetet, hogy ha a zseb meretenek csokkentesekor anyagot is kellene kidobnunk a zsebbol
	//int value - az ertek amivel csokken a zseb max tarolasi kapacitasa
	public void handlePossibleLostMaterial(int value)
	{
		System.out.println(">[:Packet].handlePossibleLostMaterial(value)");
		
		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van
		int matsMaterialADb = 0;  //mennyi aminosavunk van
		
		//vegig megyunk a zseb tartalman es megszamoljuk mibol mennyink van
		for(Material m : materials) {
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		//ha tobb aminosavunk van mint amennyi lehetne a zseb meretenek lecsokkentese utan
		if(matsMaterialADb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi aminosav: "+(matsMaterialADb-maxPerMaterial+value));
			Aminosav a = new Aminosav();
			
			//az ujonnan letrehozott aminosav erteket beallitjuk arra 
			//amennyivel kellene csokkenteni a zseb aminosav keszletet
			a.setValue(matsMaterialADb-maxPerMaterial+value);
			
			//a fuggv�ny parameterezese miatt bele kell rakni egy listaba ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();  
			m.add(a);
			
			//levonjuk ezen zsebbol a tulcsordulast
			this.decreaseMaterial(m);
		}
		//ha pedig nincs tulcsordulas akkor jok vagyunk
		else
			System.out.println("Nem veszett el aminosav");
		
		
		//ha tobb nukleotidunk van mint amennyi lehetne a zseb meretenek lecsokkentese utan
		if(matsMaterialNDb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi nukleotid: "+(matsMaterialNDb-maxPerMaterial+value));
			Nukleotid a = new Nukleotid();
			
			//az ujonnan letrehozott aminosav erteket beallitjuk arra 
			//amennyivel kellene csokkenteni a zseb aminosav keszletet
			a.setValue(matsMaterialNDb-maxPerMaterial+value);
			
			//a fuggveny parameterezese miatt bele kell rakni egy listaba ezen anyagot
			ArrayList<Material> m = new ArrayList<Material>();
			m.add(a);
			
			//levonjuk ezen zsebbol a tulcsordulast
			this.decreaseMaterial(m);
		}
		//ha pedig nincs tulcsordulas akkor jok vagyunk
		else
			System.out.println("Nem veszett el nukleotid");

		//atalitjuk a zseb meretet
		this.changeMaxMaterial(-value);
	}
	
	//Mindig meghivodik amikor felveszunk egy anyagot
	//abban az esetben ha egy anyagnak a merete nagyobb lenne mint amennyit fel tudnunk venni azt le is kezeli
	//Material mat - azon anyag amit fel akar venni a jatekos
	//Packet pac - azon jatekos zsebe aki fel akarja venni az anyagot
	public void handleMaterialSeparate(Material mat, Packet pac) 
	{
		System.out.println(">[:Packet].handleMaterialSeparate(mat, pac)");
		
		int matsMaterialNDb = 0;  //mennyi nukleotidsavunk van a pac-ban
		int matsMaterialADb = 0;  //mennyi aminosavunk van a pac-ban
		int thismatsMaterialNDb = 0;  //mennyi nukleotidsavunk van a this-ben
		int thismatsMaterialADb = 0;  //mennyi aminosavunk van a this-ben
		
		//vegig megyenk a kapott zseb tartalman es megszamoljuk mibol mennyink van
		for(Material m : pac.getMaterials()) 
		{
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		for(Material m : this.getMaterials()) 
		{
			if(m.isSame(new Nukleotid()))
				thismatsMaterialNDb += m.getValue();
			else
				thismatsMaterialADb += m.getValue();
		}
		
		//ebbe az anyagba fog felezodni a kapott anyag ha kell
		Material m = null;
		
		
		//ha olyan mennyiseget adna meg amennyi nincs is a zsebben
		if(mat.isSame(new Nukleotid()) && mat.getValue() > thismatsMaterialNDb) {
			System.out.println("nincs ennyi anyag");
			return;
		}
		if(mat.isSame(new Aminosav()) && mat.getValue() > thismatsMaterialADb) {
			System.out.println("nincs ennyi anyag");
			return;
		}
		
		
		//amennyiben nukleotidrol van szo
		if(mat.isSame(new Nukleotid())) 
		{
			//es nem tudjuk az egesz anyagot felvenni, mert kicsi a zseb merete
			if(matsMaterialNDb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Fevette a Nukleotidot, de nem az egeszet.");
				
				//megfelezzuk az anyagot
				m = new Nukleotid();
				
				//azon ertekre allitjuk be amit maximalisan fel tud venni
				m.setValue(maxPerMaterial - matsMaterialNDb);
				
				//ezen anyagot hozzaadjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag erteket pedig lecsokkentjuk
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialNDb));
			}
			//ha feltudja venni az egesz anyagot
			else
			{
				System.out.println("Felvette az Nukleotidot.");
				
				//hozzaadjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
		//amennyiben aminosavrol van szo
		else
		{	
			//es nem tudjuk az egesz anyagot felvenni, mert kicsi a zseb merete
			if(matsMaterialADb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Sikeresen fevette az Aminosavat, de nem az eg�szet.");
				m = new Aminosav();
				
				//azon ertekre allitjuk be amit maximalisan fel tud venni
				m.setValue(maxPerMaterial-matsMaterialADb);
				
				//ezen anyagot hozzaadjuk a kapott zsebhez
				pac.addMaterial(m); 
				
				//az anyag erteket pedig lecsokkentjuk
				mat.setValue(mat.getValue()-(maxPerMaterial-matsMaterialADb));
			}
			//ha feltudja venni az egesz anyagot
			else
			{
				System.out.println("Sikeresen felvette az Aminosavat.");
				
				//hozzaadjuk a kapott zsebhez az anyagot
				pac.addMaterial(mat);
			}
		}
			
	
		//kelleni fog majd az anyag kiv�tel�hez, a decreasMateral() fuggveny parametere miatt
		ArrayList<Material> material = new ArrayList<Material>();
		
		//a megfelezett anyagot hozzaadjuk
		material.add(mat);
		
		// a megfelezett anyagot levonjuk ezen zsebbol
		this.decreaseMaterial(material);	
	
	}
	
	//vissza adja a zseb anyaglistajat
	public ArrayList<Material> getMaterials() 
	{
		//System.out.println(">[:Packet].getMaterials()");
		return materials;
	}
	public Material getMaterial(String s) {
		for(Material m: materials) {
			if(m.Check(s)) {
				return m;
			}
		}
		return null;
	}
	
	//A parameterkent kapott anyagot hozzaadja a zsebhez
	//MAterial mat - azon anyag amit a zsebhez adunk
	public void addMaterial(Material mat)
	{
		System.out.println(">[:Packet].addMatarial");
		this.materials.add(mat);
	}
	
	public int getMaxMaterial() {
		return maxPerMaterial;
	}

}
