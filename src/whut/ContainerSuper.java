package whut;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContainerSuper extends JPanel {
    private ArrayList<Container> containers = new ArrayList<Container>();
    private String containerSuperText = new String();

    public ContainerSuper(String cST){containerSuperText = cST;}

    public void addContainer(Container container){
        containers.add(container);
    }

    public void draw(){
        this.removeAll();
        this.setLayout(new FlowLayout());
        this.add(new JLabel(containerSuperText));
        for(Container c : containers){
            this.add(c);
            c.draw();
        }
    }

}
