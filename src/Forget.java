
public class Forget extends Agens{

	@Override
	public boolean startTurnEffect(Virologus v) {
		System.out.println(">[:Forget].startTurnEffect()");
		//kör elején elfelejtteti minden megtanult kódját
		v.forgetAll();
		//kiotörli a gazdája listájából magát
		v.removeAgensOnMe(this);
		//visszatér igazzal, mert tud még mozogni
		return true;
	}
}
