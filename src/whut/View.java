package whut;

import java.util.ArrayList;

public class View {
	ArrayList<Observer> observer = new ArrayList<>();
	
	public void myNotify() {
		for(Observer os : observer)
			os.update();
	}
	
	public void attach(Observer os) {
		observer.add(os);
	}
	
	public void detach(Observer os) {
		observer.remove(os);
	}
}
