package whut;

import javax.swing.*;
import java.util.ArrayList;

public class VirologusObserver {
    private ContainerSuper cs;
    Virologus v;
    public void setVirologus(Virologus vir){v=vir;}

    public void updateLeft(){
        ArrayList<Item> items = v.getItemHave();
        ArrayList<String> is = new ArrayList<String>();
        for(Item i : items){
            is.add(i.toString());
        }
        ArrayList<Agens> agenses = v.getAgensHave();
        ArrayList<String> as = new ArrayList<String>();
        for(Agens a : agenses){
            as.add(a.toString());
        }
        ArrayList<GeneticCode> genetics = v.getGeneticCodeHave();
        ArrayList<String> gs = new ArrayList<String>();
        for(GeneticCode g : genetics){
            gs.add(g.toString());
        }
        ArrayList<Material> ms =  v.getPacket().getMaterials();
        ArrayList<String> ss = new ArrayList<String>();
        int nukNum = 0;
        int aminoNum = 0;
        for(Material m : ms){
            if(m.equals("nukleotid")){
                aminoNum+= m.getValue();
            }else{
                nukNum+=m.getValue();
            }

        }
        ArrayList<Agens> agensesOn = v.getAgensOnMe();
        ArrayList<String> aos = new ArrayList<String>();
        for(Agens a : agensesOn){
            as.add(a.toString());
        }


    }


    public void drawLeft(ArrayList<String> is,ArrayList<String> as,ArrayList<String> gs,ArrayList<String> ss,ArrayList<String> aos,int nukNum, int aminoNum){

        cs = new ContainerSuper("V"+MyRunnable.getVirologusSzam(v)+"player in row");
        Virologus selectedVir;
        //selectedVir = ...
        Container c1 = new Container("Items:");
        for(String s : is){
            String[] command = new String[2];
            command[0] = "leaveitem";
            command[1] = s;
            c1.addIcon(new Icon(command,s));
        }
        cs.addContainer(c1);
        Container c2 = new Container("Agens:");
        for(String s : as){
            String[] command = new String[3];
            command[0] = "useagens";
            command[1] = s;
            //command[2] = v+selecetedVir.getNum()
            c2.addIcon(new Icon(command,s));
        }
        cs.addContainer(c2);

        Container c3 = new Container("GeneticCode:");
        for(String s : gs){
            String[] command = new String[2];
            command[0] = "learn";
            command[1] = s;//MyRunnable-ben learn utan varjon genetikkodnevet ne agenst!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            c3.addIcon(new Icon(command,s));
        }
        cs.addContainer(c3);

        Container c4 = new Container("Material:");
        String[] tmp1 = new String[1];
        String[] tmp2 = new String[1];
        tmp1[0]=tmp2[0]="idle"; //kell idle parancs ami nem csinál semmit, vagy disableelni kell ezt a buttont
        c4.addIcon(new Icon(tmp1,"amino"));
        c4.addIcon(new Icon(tmp2,"nukleotid"));
        c4.add(new JLabel(Integer.toString(nukNum)));
        c4.add(new JLabel(Integer.toString(aminoNum)));
        cs.addContainer(c4);

        Container c5 = new Container("UnderEffect:");
        for(String s : aos){
            String[] command = new String[1];
            command[0] = "idle";
            c5.addIcon(new Icon(command,s));
        }
        cs.addContainer(c5);

        cs.draw();
    }




    public void updateRight(){
        ArrayList<Material> ms =  v.getPacket().getMaterials();
        ArrayList<String> ss = new ArrayList<String>();
        int nukNum = 0;
        int aminoNum = 0;
        for(Material m : ms){
            if(m.equals("nukleotid")){
                aminoNum+= m.getValue();
            }else{
                nukNum+=m.getValue();
            }
            ss.add(m.toString());//ez mashol kimaradt

        }
        ArrayList<Item> items = v.getItemHave();
        ArrayList<String> is = new ArrayList<String>();
        for(Item i : items){
            is.add(i.toString());
        }
    }
    public void drawRight(ArrayList<String> ss, ArrayList<String> is,int nukNum, int aminoNum){

    }

}
