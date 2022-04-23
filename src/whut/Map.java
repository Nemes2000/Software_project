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
	
}
