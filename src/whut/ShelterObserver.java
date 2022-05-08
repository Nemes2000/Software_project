package whut;

import java.util.ArrayList;

public class ShelterObserver implements Observer{

    private ContainerSuper cs;

    private Shelter shelter;
    public void setShelter(Shelter s){shelter = s;}


    public void update(){
        ArrayList<Item> items = shelter.getItems();
        ArrayList<String> strings = new ArrayList<String>();
        for(Item i : items){
            strings.add(i.toString());
        }
        draw(strings);
    }
    public void draw(ArrayList<String> ss){
        cs  = new ContainerSuper("Field stat:");
        Container container = new Container("Items:");
        for(String s: ss){
            String[] command = new String[2];
            command[0] = "pickup";
            command[1] = s;
            container.addIcon(new Icon(command,s));
        }
        cs.addContainer(container);
        cs.draw();
    }


}
