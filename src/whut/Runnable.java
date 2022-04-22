package whut;
import java.util.ArrayList;
import java.util.Scanner;

public static class Runnable {
	enum Actions{
		ATTACK;
		CREATEAGENS;
		STEALITEM;
	}
	
	private Virologus currentVirologus;
	public void setCurrentVirologus(Virologus v) {
		currentVirologus=v;
	}
	
	private Game game;
	
	public String[] read() {
		Scanner in = new Scanner(System.in);
		String read= in.nextLine();
		String[] readed = read.split(" ");
		return readed;
	}
	
	public void stealitem(String[] input) {
		
	}
	
	
	
	public void getInput(ArrayList<Actions> as) {
		String[] readed
		boolean megy = true;
		while(megy) {
			readed = read();
			switch(readed[0]) {
			case "info":						
				break;
			case "touch":
				break;
			case "stealitem":
				if (readed.length == 3 && as.contains(STEALITEM)) {
					stealitem(readed);     //prototipus
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "stealmaterial":
				if (readed.length == 3) {
					
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "kill":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "useagens":
				if (readed.length == 3) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "move":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "learn":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "collect":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "interact":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "pickup":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "leave":
				if (readed.length == 2) {
					
				}
				else System.out.print("Hibás paraméterezés");
				break;
			}
			
			
		}
		
		
	}
	
	//init commands
	public void start() {
		String[] readed;
		boolean megy = true;
		while(megy) {
			readed = read();
			switch(readed[0]) {				//readed tömb 0.eleme maga a parancs
			
			
			case "createfield":
				if (readed.length == 1 || readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "setneighbour":
				if (readed.length == 3) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "add":
				if (readed.length == 3) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			
			case "load":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "save":
				if (readed.length == 2) {
					
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "create":
				if (readed.length == 2) {
				}
				else System.out.print("Hibás paraméterezés");
				break;
			
			case "startgame":
				if (readed.length == 1) {
					game.run();
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "placevirologus":
				if (readed.length == 2) {
					
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "finishturn":
				if (readed.length == 1) {
					
				}
				else System.out.print("Hibás paraméterezés");
				break;
			case "newtest":
				break;
			case "close":
				megy=false;
				break;
			}
			
		}
	}
}
