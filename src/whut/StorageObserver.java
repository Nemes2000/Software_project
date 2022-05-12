package whut;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StorageObserver implements Observer {
    private Storage storage;
    public void setStorage(Storage s){storage=s;}
    private ContainerSuper cs;

    @Override
	public void update(){
       ArrayList<Material> ms =  storage.getPacket().getMaterials();
       ArrayList<String> ss = new ArrayList<String>();
        int nukNum = 0;
        int aminoNum = 0;
       for(Material m : ms){
            if(m.equals("nukleotid")){
                aminoNum+= m.getValue();
            }else{
                nukNum+=m.getValue();
            }
            ss.add(m.toString());
       }
       draw(nukNum,aminoNum);
    }
    public void draw(int nukNum,int aminoNum){
        cs  = new ContainerSuper("Field stat:");
        Container container = new Container("Material:");
        String[] tmp1 = new String[2];
        String[] tmp2 = new String[2];
        tmp1[0]= tmp2[0]="collect";
        tmp1[1] = "amino";
        tmp2[1]= "nukleotid";
        container.addIcon(new Icon(tmp1,"amino"));
        container.addIcon(new Icon(tmp2,"nukleotid"));
        container.add(new JLabel(Integer.toString(nukNum)));
        container.add(new JLabel(Integer.toString(aminoNum)));
        cs.addContainer(container);
        cs.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(cs, BorderLayout.CENTER);
    }

}
