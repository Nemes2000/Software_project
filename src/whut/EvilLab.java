package whut;

public class EvilLab extends Lab
{
	private boolean elhasznalt = false;
	
	public EvilLab()
	{
		super();
	}

	public void touching(Virologus v) //mező érintésekor
	{
		if(!elhasznalt) {
			elhasznalt = true;
			System.out.println(">[:EvilLab].touching(v)");
			//felajánlja a játékosnak a lehetséges cselekvéseket
			Beardance br = new Beardance();
			v.uRAttacked(br,null); //megtámadjad egy medvetáncal
			MyRunnable.getInputAfterTouch();
		}
		
	}
	
	public String toString() {
		return "evillab";
	}
	
}
