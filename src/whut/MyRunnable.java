package whut;

import java.util.ArrayList;
import java.util.Scanner;

public class MyRunnable {

	private static ArrayList<String> logFile;
	public static void log(String s) {
		System.out.println(s);
		logFile.add(s);
	}
	
	public static void Main(String args[]) {
		logFile = new ArrayList<String>();
		game = new Game();
		start();
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
				Item it = game.getEntityAt(number).getItem(input[2]);
				if(it!=null) {
					currentVirologus.stealItem(game.getEntityAt(number),it);
				}
				
			}
		}catch(NumberFormatException ex) {
			
		}
	}
	
	
	
	public static void getInput() {
		String[] readed;
		boolean megy = true;
		while(megy) {
			readed = read();
			switch(readed[0]) {
			case "info":						
				break;
			case "touch":
				break;
			case "stealitem":
				if (readed.length == 3) {
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
