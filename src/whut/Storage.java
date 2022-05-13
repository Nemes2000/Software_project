package whut;


public class Storage extends Field
{
	private Packet packet;
	
	public Storage()
	{
		super();
		packet = new Packet();
	}
	
	
	@Override
	public void touching(Virologus v) //mező érintésekor
	{

	}
	
	public void setPacket(Packet p) {
		packet = p;
		MyRunnable.getGame().myNotify();
	}
	
	@Override
	public Packet getPacket() {
		return packet;
	}
	
	@Override
	public String toString() {
		return "storage";
	}
}


