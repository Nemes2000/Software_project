package whut;

public class Entity {

	protected Field field;
	
	public Entity() {
		
	}
	
	public void step() {
		
		
	}
	
	public void move(Field cel) {
		System.out.println("[Entity].Move");
		cel.accept(this);
		field.remove(this);
	}
	
	public Field getField() {
		return field;
	}
	
	public void setField(Field f) {
		field = f;
	}
}
