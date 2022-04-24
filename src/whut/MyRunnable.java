package whut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			log("Bad parameter!");
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
			log("Bad parameter!");
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
			System.out.print("Bad parameter!");
		}
	}
	

	public static void moveTo(String hova) {
		try {
			boolean moved = false;
			String ch = hova.substring(1);
			int melyik = Integer.parseInt(ch);
			Field f = game.getMap().getFields().get(melyik-1);
			if(hova.charAt(0) == 'f') {
				ArrayList<Field> list = currentVirologus.getField().getNeighbourhood();
				for(Field ff : list)
					if(ff == f) {
						currentVirologus.move(f);
						moved = true;
						for(int i = 0; i < game.getEntitySize(); i++)
							if(game.getEntityAt(i) == currentVirologus) {
								log("v"+(i+1)+" moved");
								break;
							}
					}
			}
			if(!moved)
				log("Bad parameter!");
		} catch(NumberFormatException e) {
			log("Bad parameter!");
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
		} catch (NumberFormatException ex) {
			
		}
	}
	
	public static void createField(String[] input) {
		if (input.length == 1) {
			game.getMap().addField(new Field());
		}
		else {
			switch(input[1]) {
				case "shelter":
					game.getMap().addField(new Shelter());
					break;
				case "storage":
					game.getMap().addField(new Storage());
					break;
				case "lab":
					game.getMap().addField(new Lab());
					break;
				case "evillab":
					game.getMap().addField(new EvilLab());
					break;
				default:
					System.out.print("Bad parameter!");
			}
		}
	}
	
	public static void setNeighbour(String[] input) {
		String sub = input[1].substring(1);
		String sub2 = input[2].substring(1);
		try {
			int number = Integer.parseInt(sub);
			int number2 = Integer.parseInt(sub2);
			if(input[1].charAt(0)=='f') {
				Field f1 = game.getMap().getField(number-1);
				Field f2 = game.getMap().getField(number2-1);
				f1.setNeighbour(f2);
				f2.setNeighbour(f1);
				System.out.print(input[1]+" is now connected to "+input[2]+ "!");
			}
		} catch (NumberFormatException ex) {
			
		}
	}
	
	public static void placeVir(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='f') {
				Virologus v = new Virologus();
				game.addPlayer(v);
				game.getMap().getField(number-1).accept(v);
				System.out.print("A Virologus has been added to "+input[1]+"!");
			}
			
		} catch (NumberFormatException ex) {
			
		}
	}
	
	public static void getInfo() {
		System.out.print("Anygok: ");
		for(Material mat : currentVirologus.getPacket().getMaterials()) {
			System.out.print(mat.getType()+", ");
		}
		System.out.println();
		
		System.out.print("Itemek: ");
		for(Item it : currentVirologus.getItemHave()) {
			if(it.Check("Axe"))
				System.out.print("Axe, ");
			else if(it.Check("Cloak"))
				System.out.print("Cloak, ");
			else if(it.Check("Glove"))
				System.out.print("Glove, ");
			else
				System.out.print("Sack, ");
		}
		System.out.println();
		
		System.out.print("Genetik kodok: ");
		for(GeneticCode gc : currentVirologus.getGeneticCodeHave()) {
			if(gc.Check("ProtectionCode"))
				System.out.print("ProtectionCode, ");
			else if(gc.Check("ForgetCode"))
				System.out.print("ForgetCode, ");
			else if(gc.Check("StunCode"))
				System.out.print("StunCode, ");
			else 
				System.out.print("VitusdanceCode, ");
		}
		System.out.println();
		
		System.out.print("Agensek: ");
		for(Agens a : currentVirologus.getAgensHave()) {
			if(a.Check("Protection"))
				System.out.print("Protection, ");
			else if(a.Check("Forget"))
				System.out.print("Forget, ");
			else if(a.Check("Stun"))
				System.out.print("Stun, ");
			else 
				System.out.print("Vitusdance, ");
		}
		System.out.println();
		
		System.out.print("Hato agensek: ");
		for(Agens a : currentVirologus.getAgensOnMe()) {
			if(a.Check("Protection"))
				System.out.print("Protection, ");
			else if(a.Check("Forget"))
				System.out.print("Forget, ");
			else if(a.Check("Stun"))
				System.out.print("Stun, ");
			else 
				System.out.print("Vitusdance, ");
		}
		System.out.println();
		
	}
	
	public static void pickup(String[] input)
	{
		String sub = input[1];
		Item it = currentVirologus.getField().getItem(sub);
		if(it!=null)
		{
			currentVirologus.pickUpItem(currentVirologus.getField().getItems());
		}
	}
	
	public static void collect(String[] input)
	{
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='a' || input[1].charAt(0)=='n') { //egyszerűség kedvéért a szám legyen egyedi, pl a1,n2,a3,a4,n5 stb.
				Material m = currentVirologus.getField().getPacket().getMaterials().get(number-1);
				if(m!= null)
				{
					currentVirologus.increaseMaterial(currentVirologus.getField().getPacket());
				}
			}
		}
		catch (NumberFormatException ex) {
			
		}
			
	}
	
	private static void addSomething(String[] readed) {
		int szam = Character.getNumericValue(readed[2].charAt(1));  //virologus jelzo utani azonosito(hanyadik virologusra hasznaljuk a dolgot);
		//az add utani parancs alapjan folytatodik
		switch(readed[1]) {
		case "cloak":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Cloak());
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Cloak());
			}
		break;
		case "glove":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Glove());
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Glove());
			}
		break;
		case "sack":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Sack());
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Sack());
			}
		break;
		case "axe":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Axe());
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addItem(new Axe());
			}
		break;
		case "stuncode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new StunCode()); //setGeneticCodeot a Fieldbe is berakni ott nem csinal semmit
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new StunCode());
			}
		break;
		case "vitusdancecode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new VitusdanceCode());
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new VitusdanceCode());
			}
		break;
		case "forgetcode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new ForgetCode());
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new ForgetCode());
			}
		break;
		case "protectioncode":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).setGeneticCode(new ProtectionCode());
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).learnGeneticCode(new ProtectionCode());
			}
		break;
		
		case "stun":
			if(readed[2].charAt(0) == 'f') {
				System.out.print("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Stun());
			}
		break;
		case "vitusdance":
			if(readed[2].charAt(0) == 'f') {
				System.out.print("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Vitusdance());
			}
		break;
		case "protection":
			if(readed[2].charAt(0) == 'f') {
				System.out.print("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Protection());
			}
		break;
		case "forget":
			if(readed[2].charAt(0) == 'f') {
				System.out.print("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Forget());
			}
		break;
		case "aminosav":
			Packet uja = new Packet();			
			uja.addMaterial(new Aminosav());						
			if(readed[2].charAt(0) == 'f') { 
				if(game.getMap().getField(szam).getPacket() != null)
					game.getMap().getField(szam).getPacket().addMaterial(new Aminosav());  //fieldnek egy addMaterialt kell letrehozni.
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).increaseMaterial(uja);
			}
		break;
		case "nukleotid":
			Packet ujn = new Packet();			
			ujn.addMaterial(new Aminosav());
			if(readed[2].charAt(0) == 'f') {   
				if(game.getMap().getField(szam).getPacket() != null)
					game.getMap().getField(szam).getPacket().addMaterial(new Aminosav());  //fieldnek egy addMaterialt kell letrehozni.
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).increaseMaterial(ujn);
			}
		break;
		}
	}
	
	public static void leave(String[] input)
	{
		String sub = input[1];
		Item it = currentVirologus.getItem(sub);
		if(it!= null)
		{
			currentVirologus.leaveItem(); //legelsőt dobja, lehet nem kéne akkor paraméter a commandhoz
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
						moveTo(readed[1]);
					}
					else System.out.print("Bad parameter!");
					break;
				case "create":
					if (readed.length == 2)
						currentVirologus.createAgens(readed[1]);
					else 
						log("Bad parameter!");
					break;
				case "finishturn" : 
					steps = 0;
					break;
				default : 
					log("Bad parameter!");
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
					else 
						log("Bad parameter!");
					break;
				case "stealmaterial":
					if (readed.length == 3) {
						stealmaterial(readed);
					}
					else 
						log("Bad parameter!");
				case "kill":
					if (readed.length == 2) {
						kill(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "useagens":
					if (readed.length == 3) {
						useagens(readed);
					}
					else 
						log("Bad parameter!");
					break;
				
				case "learn":
					if (readed.length == 2) {
						learn(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "collect":
					if (readed.length == 2) {
						collect(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "pickup":
					if (readed.length == 2) {
						pickup(readed);
					}
					else
						log("Bad parameter!");
					break;
				case "leave":
					if (readed.length == 2) {
						leave(readed);
					}
					else 
						log("Bad parameter!");
					break;
				case "finishturn" : 
					justinfo = 0;
					steps = 0;
					break;
				default : 
					log("Bad parameter!");
					break;
			}
			justinfo--;
		}
	}
	
	//init commands
	public static void start() {
		game = new Game();
		String[] readed;
		boolean megy = true;
		while(megy) {
			readed = read();
			switch(readed[0]) {				//readed tömb 0.eleme maga a parancs		
			case "createfield":
				if (readed.length == 1 || readed.length == 2) {
					createField(readed);
					log("A field has been created!");
				}
				else 
					log("Bad parameter!");
				break;
			case "setneighbour":
				if (readed.length == 3) {
					setNeighbour(readed);
				}
				else 
					log("Bad parameter!");
				break;
			case "add":
				if (readed.length == 3) {
					addSomething(readed);
				}
				else 
					log("Bad parameter!");
				break;
			
			case "load":
				if (readed.length == 2) {
					ObjectInputStream in;
					try {
						in = new ObjectInputStream(new FileInputStream(readed[1]));
						game = (Game)in.readObject();
						in.close();
					} catch(FileNotFoundException fe) {
						log("Bad parameter!");
					} catch (IOException e) {
						log("Bad parameter!");
					} catch (ClassNotFoundException e) {
						log("Bad file!");
					}
				}
				else 
					log("Bad parameter!");
				break;
			case "save":
				if (readed.length == 2) {
					ObjectOutputStream out;
					try {
						out = new ObjectOutputStream(new FileOutputStream(readed[1] + ".txt"));
						out.writeObject(game);
					} catch(Exception e) {
						log("Bad parameter!");
					}
				}
				else 
					log("Bad parameter!");
				break;
			
			case "startgame":
				if (readed.length == 1) {
					log("Game started!");
					game.run();
				}
				else 
					log("Bad parameter!");
				break;
			case "placevirologus":
				if (readed.length == 2) {
					placeVir(readed);
				}
				else 
					log("Bad parameter!");
				break;
			case "newtest":
				start();
				megy = false;
				break;
			case "close":
				megy=false;
				break;
			default : 
				log("Bad parameter!");
				break;
			}
		}
	}
}
