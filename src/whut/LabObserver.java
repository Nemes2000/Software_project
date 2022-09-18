package whut;

import java.awt.BorderLayout;
import java.io.Serializable;

import javax.swing.JFrame;

public class LabObserver implements Observer, Serializable{

    private Lab lab;
    public void setLab(Lab l){lab = l;}
    
    public LabObserver(Lab l) {
    	lab=l;
    }

    private ContainerSuper cs;

    @Override
	public void update(){
        GeneticCode g = lab.codeHere();

        draw(g.toString());
    }

    public void draw(String name){
        cs  = new ContainerSuper("Field stat:");
        Container container = new Container("Genetic Code:");
        String[] tmp = new String[1];
        tmp[0]= "learn";
        container.addIcon(new Icon(tmp,name));
        cs.addContainer(container);
        cs.draw();
        JFrame frame = MyRunnable.getFrame();
        frame.add(cs, BorderLayout.CENTER);
    }

}
