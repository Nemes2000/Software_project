package whut;
import java.util.ArrayList;

public class Map
{
	private ArrayList<Field> Fields;
	
	public Map()
	{
		Fields = new ArrayList<Field>();
	}
	
	public ArrayList<Field> getFields(){
		return Fields;
	}
	
	public void addField(Field f) {
		Fields.add(f);
	}
	
	public Field getField(int index) {
		return Fields.get(index);
	}
	
}
