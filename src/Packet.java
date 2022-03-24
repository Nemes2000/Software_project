import java.util.ArrayList;
import java.util.List;

public class Packet {
	private ArrayList<Material> materials;
	private int maxPerMaterial;
	
	public Packet(){
		
	}
	
	public boolean decreaseMaterial(ArrayList<Material> mats) 
	{	
		ArrayList<Material> decreas = materials;
		for(int i = 0; i<mats.size();i++)
			for(int j = 0; j<decreas.size();i++)
				if(mats.get(i).isSame(decreas.get(j)))
				{
					if(mats.get(i).getValue()-decreas.get(j).getValue() < 0) 
					{
						mats.get(i).setValue(mats.get(i).getValue()-decreas.get(j).getValue());
						decreas.remove(j);
					}
					else
					{
						decreas.get(j).setValue(decreas.get(j).getValue()-mats.get(i).getValue());
						mats.remove(i);
						j=0;
					}
				}
		if(mats.size()==0) {
			System.out.println("Sikeresen levonta a nyersanyagokat.");
			materials = decreas;
			return true;
		}
		
		System.out.println("Sikertelenul vonta le a nyersanyagokat.");
		return false;
	}
	
	public void changeMaxMaterial(int value) 
	{
		System.out.println("Az anyag tarolas nott:"+value+"-val");
		maxPerMaterial += value;
	}
	
	public void handlePossibleLostMaterial(int value)
	{
		int matsMaterialNDb = 0;
		int matsMaterialADb = 0;
		for(Material m : materials) {
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		if(matsMaterialADb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi aminosav: "+(matsMaterialADb-maxPerMaterial-value));
			Aminosav a = new Aminosav();
			a.setValue(matsMaterialADb-maxPerMaterial-value);
			ArrayList<Material> m = new ArrayList<Material>();
			m.add(a);
			this.decreaseMaterial(m);
		}
		else
			System.out.println("Nem veszett el aminosav");
		
		if(matsMaterialNDb > maxPerMaterial-value)
		{
			System.out.println("Elveszett ennyi nukleotid: "+(matsMaterialNDb-maxPerMaterial-value));
			Nukleotid a = new Nukleotid();
			a.setValue(matsMaterialADb-maxPerMaterial-value);
			ArrayList<Material> m = new ArrayList<Material>();
			m.add(a);
			this.decreaseMaterial(m);
		}
		else
			System.out.println("Nem veszett el nukleotid");

		
		this.changeMaxMaterial(-value);
	}
	
	public void handleMaterialSeparate(Material mat, Packet pac) 
	{
		//lekérdezi anyagonként hogy mennyi van
		int matsMaterialNDb = 0;
		int matsMaterialADb = 0;
		for(Material m : materials) {
			if(m.isSame(new Nukleotid()))
				matsMaterialNDb += m.getValue();
			else
				matsMaterialADb += m.getValue();
		}
		
		if(mat.isSame(new Nukleotid())) 
		{
			//ezt fogja kivenni abbol a zsebbol amelyiktol felveszi az anyagot
			ArrayList<Material> matList = new ArrayList<>();
			
			if(matsMaterialNDb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Sikeresen fevette az Nukleotidot, de nem az egészet.");
				Nukleotid n = new Nukleotid();
				n.setValue(maxPerMaterial-matsMaterialNDb);
				materials.add(n);
				
				//a csokkentett értéket vonjuk csak le a tobbi marad a másik zsebben
				matList.add(n);
			}
			else
			{
				System.out.println("Sikeresen felvette az Nukleotidot.");
				materials.add(mat);
				
				//az egészet kivesszuk a zsebbol
				matList.add(mat);
			}
			
			pac.decreaseMaterial(matList);
		}
		else
		{
			//ezt fogja kivenni abbol a zsebbol amelyiktol felveszi az anyagot
			ArrayList<Material> matList = new ArrayList<>();
			
			if(matsMaterialADb + mat.getValue() > maxPerMaterial) 
			{
				System.out.println("Sikeresen fevette az Aminosavat, de nem az egészet.");
				Aminosav n = new Aminosav();
				n.setValue(maxPerMaterial-matsMaterialADb);
				materials.add(n);
				
				//a csokkentett értéket vonjuk csak le a tobbi marad a másik zsebben
				matList.add(n);
			}
			else
			{
				System.out.println("Sikeresen felvette az Aminosavat.");
				materials.add(mat);
				
				//az egészet kivesszuk a zsebbol
				matList.add(mat);
			}
			
			pac.decreaseMaterial(matList);
		}
			
	}
	
}
