
public class Forget extends Agens{

	@Override
	public boolean startTurnEffect(AgensUsable au) {
		System.out.println(">[:Forget].startTurnEffect()");
		//k�r elej�n elfelejtteti minden megtanult k�dj�t
		au.forgetAll();
		//kiot�rli a gazd�ja list�j�b�l mag�t
		au.removeAgensOnMe(this);
		//visszat�r igazzal, mert tud m�g mozogni
		return true;
	}
}