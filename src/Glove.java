
public class Glove extends Item {
public boolean fireBackEffect(Virologus tamado, Agens a) {
	System.out.println(">[:Item].fireBackEffect(tamado, a)");
	if (tamado != null) {
		tamado.uRAttacked(a, null);
	}
	return true;
}
}
