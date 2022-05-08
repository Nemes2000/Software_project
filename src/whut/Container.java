package whut;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Container extends JPanel {
    private ArrayList<Icon> icons = new ArrayList<Icon>();




    private String containerText = new String();

    public Container(String cT){containerText = cT;}


    public void addIcon(Icon i)
    {
        icons.add(i);
    }

    public void draw()
    {
        this.removeAll();
        this.setLayout(new FlowLayout());
        this.add(new JLabel(containerText));
        //utolso, max 5 icont felrakja
        for(int i = icons.size()-1;i>=0&&i>icons.size()-6;i--){
            this.add(icons.get(i));
        }

    }


}
