package whut;


//A fert�z� laborat�rium mez� nyilv�ntart�s��rt felel. 
//Kezeli a mez�n t�rt�n� interakci�k megval�sul�s�t. Kezeli a virol�gusok megfert�z�s�t.
public class EvilLab extends Lab
{
	//private boolean elhasznalt = false;
	
	public EvilLab()
	{
		
		super(new StunCode());
	}
 
	//L�trehoz egy medvet�nc �genst, �s megt�madja vele a virol�gust, ezzel �megfert�zve� �t.
	public void touching(Virologus v) //mező érintésekor
	{
		//if(!elhasznalt) {
			//elhasznalt = true;
			//felajánlja a játékosnak a lehetséges cselekvéseket
			Beardance br = new Beardance();
			v.uRAttacked(br,null); //megtámadjad egy medvetáncal

		//}
		
	}
	
	public String toString() {
		return "evillab";
	}
	
}
