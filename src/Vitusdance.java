
public class Vitusdance extends Agens{
	
	
	
	@Override
	public boolean startTurnEffect(Virologus v) {
		//kör elején háromszor random lépteti
		for(int i=0;i<3;i++) {
			v.move();
		}
		//kitörli a virológuson lévõ ágensek közül
		v.removeAgensOnMe(this);
		//visszatér igazzal, mert még tud utána lépni
		return true;
	}
	
}
