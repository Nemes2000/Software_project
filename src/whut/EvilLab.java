package whut;

import java.util.ArrayList;

public class EvilLab
{
	
	public EvilLab()
	{
		super();
	}

	public void touching(Virologus v) //mező érintésekor
	{
		System.out.println(">[:EvilLab].touching(v)");
		//felajánlja a játékosnak a lehetséges cselekvéseket
		Beardance br = new Beardance();
		v.uRAttacked(br,null); //megtámadjad egy medvetáncal
		MyRunnable.getInputAfterTouch();
	}
	
}
