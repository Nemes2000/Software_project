
public class Forget extends Agens{

	@Override
	public boolean startTurnEffect(Virologus v) {
		System.out.println(">[:Forget].startTurnEffect()");
		//k�r elej�n elfelejtteti minden megtanult k�dj�t
		v.forgetAll();
		//kiot�rli a gazd�ja list�j�b�l mag�t
		v.removeAgensOnMe(this);
		//visszat�r igazzal, mert tud m�g mozogni
		return true;
	}
}
