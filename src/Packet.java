import java.util.List;

public class Packet {
	private List<Material> materials;
	private int maxPerMaterial;
	
	public Packet(){
		
	}
	
	public boolean decreaseMaterial(List<Material> mats) {
		return true;
	}
	
	public void changeMaxMaterial(int value) {
		maxPerMaterial += value;
		System.out.println("Az anyag tarolas nott:"+value+"-val");
	}
	
	public void handlePossibleLostMaterial(int value){
		
	}
	
	public void handleMaterialSeparate(Material mat, Packet pac) {
		
	}
	
}
