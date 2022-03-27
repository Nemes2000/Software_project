
public class Vitusdance extends Agens{
	
	
	
	@Override
	public boolean startTurnEffect(AgensUsable au) {
		System.out.println(">[:Vitusdance].startTurnEffect()");
		//kör elején háromszor random lépteti
		for(int i=0;i<3;i++) {
			au.move();
		}
		//kitörli a virológuson lévõ ágensek közül
		au.removeAgensOnMe(this);
		//visszatér igazzal, mert még tud utána lépni
		return true;
	}
	
}
