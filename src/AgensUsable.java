import java.util.ArrayList;

public class AgensUsable {
	
	private ArrayList<Agens> agens;
	private Packet packet;
	
	public void addAgens(Agens a)
	{
		agens.add(a);
	}
	
	public Packet getPacket() {
		return packet;
	}

}
