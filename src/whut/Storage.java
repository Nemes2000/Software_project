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
		System.out.println(">[:Storage].touching(v)");
		//felajánlja a játékosnak a lehetséges cselekvéseket
		MyRunnable.getInputAfterTouch();
	}
	
	public void setPacket(Packet p) {
		packet = p;
	}
	
	@Override
	public Packet getPacket() {
		return packet;
	}
}


