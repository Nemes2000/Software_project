import java.util.ArrayList;

public class AgensUsable {
	
	private ArrayList<Agens> agens;
	protected ArrayList<Agens> agensOnMe;
	protected Packet packet;
	
	public void addAgens(Agens a)
	{
		agens.add(a);
	}
	
	public Packet getPacket() {
		return packet;
	}
	
	public void uRAttacked(Agens ag, Virologus v) {
		
	}
	
	public boolean roundDesc() {
		int a =5;
	}

}
