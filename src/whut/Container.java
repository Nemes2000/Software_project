package whut;

import javax.swing.*;
import java.util.ArrayList;

public class Container extends JPanel {
    private ArrayList<Icon> icons = new ArrayList<Icon>();

    public void addIcon(Icon i){
        icons.add(i);
    }


}
