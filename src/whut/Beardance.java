package whut;

import java.util.ArrayList;

public class Beardance extends Agens{
	
	//A mez�n amin �ll a param�ter�l kapott virol�gus megfert�z minden virol�gust, majd a param�ter�l kapott virol�gust egy random szomsz�dos mez�re mozgatja. 
	//Ezut�n az �j mez�n is megfert�z mindenkit. 
	//V�g�l hamissal t�r vissza, mivel a birtokos virol�gus m�st nem csin�lhat a k�r�ben.
	@Override
	public boolean startTurnEffect(AgensUsable v) {
		infectAll(v.getField().getVirologusok(),v);
		if(v.getField().getNeighbourhood().size() > 0)
			v.move(v.getField().getNeighbourhood().get(0));
		infectAll(v.getField().getVirologusok(),v);		
		return false;
	}
	
	//A param�ter�l kapott csomagnak lek�ri a maxim�lis �rt�k�t, majd a maxim�lis �rt�k�b�l levonja ezt az �rt�ket, ezzel null�ra �ll�tva.
	@Override
	public void destroyEffect(Packet p) {
		p.handlePossibleLostMaterial(p.getMaxMaterial());
	}
	
	//A param�ter�l kapott virol�guslist�b�l mindenkit megfert�z Beardance �genssel az al�bbi m�don: 
	//Csin�l egy Beardance �genst, amit odaad a param�ter�l kapott virol�gusnak. 
	//Ezut�n a lista elem virol�gust a visel� virol�gus nev�ben a csin�lt �genssel megt�madja.
	public void infectAll(ArrayList<AgensUsable> vs, AgensUsable a) {
		for(AgensUsable vc : vs) {
			if(!vc.equals(a)) {
				Beardance b = new Beardance();
				a.addAgens(b);
				Virologus v = (Virologus)vc;
				//vc.uRAttacked(b,v);
				a.useAgens(v, b);
			}
		}
	}
	
	public boolean Check(String s) {
		if(s.equals("Beardance"))
			return true;
		return false;
	}
	
	public String toString() {
		return "beardance";
	}
}
