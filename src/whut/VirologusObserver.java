package whut;

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

        }
        ArrayList<Item> items = v.getItemHave();
        ArrayList<String> is = new ArrayList<String>();
        for(Item i : items){
            is.add(i.toString());
        }
    }
    public void drawRight(){

    }

}
