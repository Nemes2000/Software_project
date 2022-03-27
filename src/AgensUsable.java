import java.util.ArrayList;

public abstract class AgensUsable {
	
	private ArrayList<Agens> agens;
	protected ArrayList<Agens> agensOnMe;
	protected Packet packet;
	
	public void addAgens(Agens a)
	{
		agens.add(a);
	}
	public void addAgensOnMe(Agens ag) {
		agensOnMe.add(ag);
	}
	
	public Packet getPacket() {
		return packet;
	}
	

	
	//ha egy �gens is leb�n�t�, akkor false-ot add vissza
	public boolean roundDesc() {
		boolean canStep = true;
		//minden startTurneffect lefut, akkor is, ha m�r volt stunnol�
		foreach(Agens ag: agensOnMe){
			if(!ag.startTurnEffect) {
				canStep=false;
			}
		}
		return canStep;
	}
	
	@Override
	public void uRAttacked(Agens ag, Virologus v) {
		System.out.println(">[:Virologus].uRAttacked()");
		//k�ld�t�lk kit�rli az �genst
		v.removeAgens(ag);
		//ellen�rzi, hogy van-e v�dve valami �ltal
		boolean isProtected = false;
		foreach(Agens ag: agensOnMe){
			if(ag.defendEffect()) {
				isProtected=true;
			}
		}
		addAgensOnMe(ag);
		
	}
	

}
