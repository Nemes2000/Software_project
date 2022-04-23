package whut;

import java.util.ArrayList;
import java.util.Scanner;

public class MyRunnable {

	private static ArrayList<String> logFile;
	public static void log(String s) {
		System.out.println(s);
		logFile.add(s);
	}
	
	public static void main(String args[]) {
		logFile = new ArrayList<String>();
		game = new Game();
		start();
	
		//tesztek ide__________________________________________
		
	}
	
	private static Virologus currentVirologus;
	public static void setCurrentVirologus(Virologus v) {
		currentVirologus=v;
	}
	
	private static Game game;
	public static Game getGame() {return game;}
	
	public static String[] read() {
		Scanner in = new Scanner(System.in);
		String read= in.nextLine();
		String[] readed = read.split(" ");
		return readed;
	}
	
	public static void stealitem(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				if(v==null)return;
				Item it = v.getItem(input[2]);
				if(it!=null && currentVirologus.getField().getNeighbourhood().contains(v)) {
					currentVirologus.stealItem(v, it);
				}
				
			}
		}catch(NumberFormatException ex) {
			
		}
	}
	
	public static void stealmaterial(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				if(v==null)return;
				Material mat = v.getPacket().getMaterial(input[2]);
				if(mat != null && currentVirologus.getField().getNeighbourhood().contains(v)) {
					currentVirologus.stealMaterial(v, mat);
				}
				
			}
		}catch(NumberFormatException ex) {
			
		}
	}
	
	public static void useagens(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				Agens a = currentVirologus.getAgens(input[2]);
				if(v!=null && a != null && currentVirologus.getField().getNeighbourhood().contains(v)) {
					currentVirologus.useAgens(v,a);
				}
				
			}	
		}catch(NumberFormatException ex) {
			
		}
	}
	
	public static void kill(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				if(v!=null&&currentVirologus.getField().getNeighbourhood().contains(v)) {
					currentVirologus.kill(v);
				}
			}
			
		}catch(NumberFormatException ex) {
			
		}
	}
	
	public static void learn(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='g') {
				GeneticCode g = game.getGeneticCodeAt(number-1);
				if(g!=null&&currentVirologus.getField().codeHere(g)) {
					currentVirologus.learnGeneticCode(g);
				}
			}
			
			
			
		}catch(NumberFormatException ex){
			
		}
	}
	
	
	private static int steps; 
	
	public static void getInputFirstAct() {
		String[] readed;
		steps = 2;
		while(steps > 0) {
			readed = read();
			switch(readed[0]) {
				case "newtest" :
					start();
					break;
				case "info":
					steps++;
					getInfo();
					break;
				case "touch":
					currentVirologus.getField().touching(currentVirologus);
					break;
				case "move":
					if (readed.length == 2) {
					//fuggveny meg van
					}
					else System.out.print("Hibás paraméterezés");
					break;
				case "create":
					if (readed.length == 2) {
					//agens ==>> meg van
					}
					else System.out.print("Hibás paraméterezés");
					break;
				case "finishturn" : 
					steps = 0;
					break;
			}
			steps--;
		}
	}
	
	public static void getInputAfterTouch() {
		String[] readed;
		int justinfo = 1;
		while(justinfo > 0) {
			readed = read();
			switch(readed[0]) {
				case "newtest" :
					start();
					break;
				case "info":
					justinfo++;
					getInfo();
					break;
				case "stealitem":
					if (readed.length == 3) {
						stealitem(readed);     //prototipus
					}
					else System.out.print("Hibás paraméterezés");
					break;
				case "stealmaterial":
					if (readed.length == 3) {
						stealmaterial(readed);
					}
					else System.out.print("Hibás paraméterezés");
					break;
				case "kill":
					if (readed.length == 2) {
						kill(readed);
					}
					else System.out.print("Hibás paraméterezés");
					break;
				case "useagens":
					if (readed.length == 3) {
						useagens(readed);
					}
					else System.out.print("Hibás paraméterezés");
					break;
				
				case "learn":
					if (readed.length == 2) {
						learn(readed);
					}
					else System.out.print("Hibás paraméterezés");
					break;
				case "collect":
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
				case "finishturn" : 
					justinfo = 0;
					steps = 0;
					break;
			}
			justinfo--;
		}
	}
	
	//init commands
	public static void start() {
		String[] readed;
		boolean megy = true;
		while(megy) {
			readed = read();
			switch(readed[0]) {				//readed tömb 0.eleme maga a parancs		
			case "createfield":
				if (readed.length == 1 || readed.length == 2) {
					
					log("A field has been created!");
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
					switch(readed[1]) {
						case "agens" : 
							break;
						case "material" : 
							break;
						case "geneticcode" : 
							break;
						case "item" : 
							break;
					}
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
