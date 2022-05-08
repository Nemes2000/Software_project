package whut;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Container extends JPanel {
    private ArrayList<Icon> icons = new ArrayList<Icon>();
    private String containerText = new String();

    public void addIcon(Icon i){
        icons.add(i);
    }

    public void draw()
    {
        this.removeAll();
        this.setLayout(new FlowLayout());

    }


}
