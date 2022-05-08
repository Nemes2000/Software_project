package whut;

public class LabObserver implements Observer{

    private Lab lab;
    public void setLab(Lab l){lab = l;}

    private ContainerSuper cs;



    public void update(){
        GeneticCode g = lab.codeHere();
        draw(g.toString());
    }

    public void draw(String name){
        cs  = new ContainerSuper("Field stat:");
        Container container = new Container("Genetic Code:");
        //container-be berakni az icont
        cs.addContainer(container);
        cs.draw();
    }


}
