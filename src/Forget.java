
public class Forget extends Agens{

	@Override
	public boolean startTurnEffect(AgensUsable au) {
		System.out.println(">[:Forget].startTurnEffect()");
		//kör elején elfelejtteti minden megtanult kódját
		au.forgetAll();
		//kiotörli a gazdája listájából magát
		au.removeAgensOnMe(this);
		//visszatér igazzal, mert tud még mozogni
		return true;
	}
}
