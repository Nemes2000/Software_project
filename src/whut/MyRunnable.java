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
	private static Scanner scanner;
	public static Game getGame() {return game;}
	
	public static String[] read() {
		if(!testfromFile) {
			Scanner in = new Scanner(System.in);
			String read= in.nextLine();
			String[] readed = read.split(" ");
			return readed;
		} else {
			if(scanner.hasNextLine()) {
				String read= scanner.nextLine();
				String[] readed = read.split(" ");
				return readed;
			} else {
				scanner.close();
				testfromFile = false;
				return null;
			}
			
		}
		
	}
	
	public static void stealitem(String[] input) {
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			if(input[1].charAt(0)=='v') {
				Virologus v = (Virologus)(game.getEntityAt(number-1));
				if(v==null) {
					log("Bad parameter!");
					return;
				}
				Item it = v.getItem(input[2]);
				if(it!=null && currentVirologus.getField().getNeighbourhood().contains(v)) {
					currentVirologus.stealItem(v, it);
				} else
					log("This item cant be found at v"+ getVirologusSzam(v));
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
				if(v==null) {
					log("Bad parameter!");
					return;
				}
				Material mat = v.getPacket().getMaterial(input[2]);
				if(mat != null && currentVirologus.getField().getNeighbourhood().contains(v)) {
					currentVirologus.stealMaterial(v, mat);
				} else
					log("This materila cant be found at v"+ getVirologusSzam(v));
				
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
			log("Bad parameter!");
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
						log("v"+getVirologusSzam(currentVirologus)+" moved");
					}
			}
			if(!moved)
				log("Bad parameter!");
		} catch(NumberFormatException e) {
			log("Bad parameter!");
		}
}
	
	public static int getVirologusSzam(Virologus v) {
		for(int i = 0; i < game.getEntitySize(); i++)
			if(game.getEntityAt(i) == v)
				return i+1;
		return -1;
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
	
	public static void learn() {
		if(null != currentVirologus.getField().codeHere()) {
			currentVirologus.learnGeneticCode(currentVirologus.getField().codeHere());
			log("v"+getVirologusSzam(currentVirologus)+" learned "+currentVirologus.getField().codeHere().toString());
		}
		else
			log("Bad parameter!");
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
					log("Bad parameter!");
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
				log(input[1]+" is now connected to "+input[2]+ "!");
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
				log("A Virologus has been added to "+input[1]+"!");
			}
			
		} catch (NumberFormatException ex) {
			
		}
	}
	
	public static void getInfo() {
		String kimenet = "Anygok: ";
		for(Material mat : currentVirologus.getPacket().getMaterials())
			kimenet.concat(mat.getType()+", ");
		
		log(kimenet);

		kimenet = "Itemek: ";
		for(Item it : currentVirologus.getItemHave()) {
			if(it.Check("Axe"))
				kimenet.concat("Axe, ");
			else if(it.Check("Cloak"))
				kimenet.concat("Cloak, ");
			else if(it.Check("Glove"))
				kimenet.concat("Glove, ");
			else
				kimenet.concat("Sack, ");
		}
		log(kimenet);
		
		kimenet = "Genetik kodok: ";
		for(GeneticCode gc : currentVirologus.getGeneticCodeHave()) {
			if(gc.Check("ProtectionCode"))
				kimenet.concat("ProtectionCode, ");
			else if(gc.Check("ForgetCode"))
				kimenet.concat("ForgetCode, ");
			else if(gc.Check("StunCode"))
				kimenet.concat("StunCode, ");
			else 
				kimenet.concat("VitusdanceCode, ");
		}
		log(kimenet);
		
		kimenet ="Agensek: ";
		for(Agens a : currentVirologus.getAgensHave()) {
			if(a.Check("Protection"))
				kimenet.concat("Protection, ");
			else if(a.Check("Forget"))
				kimenet.concat("Forget, ");
			else if(a.Check("Stun"))
				kimenet.concat("Stun, ");
			else 
				kimenet.concat("Vitusdance, ");
		}
		log(kimenet);
		
		kimenet = "Hato agensek: ";
		for(Agens a : currentVirologus.getAgensOnMe()) {
			if(a.Check("Protection"))
				kimenet.concat("Protection, ");
			else if(a.Check("Forget"))
				kimenet.concat("Forget, ");
			else if(a.Check("Stun"))
				kimenet.concat("Stun, ");
			else 
				kimenet.concat("Vitusdance, ");
		}
		log(kimenet);
		
	}
	
	public static void pickup(String[] input)
	{
		String sub = input[1];
		Item it = currentVirologus.getField().getItem(sub);
		if(it!=null) {
			currentVirologus.pickUpItem(it);
			log(input[1] + " is added to v"+ getVirologusSzam(currentVirologus) +"'s inventory!" );
		}
	}
	
	public static void collect(String[] input)
	{
		String sub = input[1].substring(1);
		try {
			int number = Integer.parseInt(sub);
			boolean can = false;
			Material mm;
			if(input[1] == "amino")
				mm = new Aminosav();
			else
				mm = new Nukleotid();
			if(currentVirologus.getField().getPacket() != null) {
				for(Material m : currentVirologus.getField().getPacket().getMaterials())
					if(m.isSame(mm)) {
						can = true;
						currentVirologus.increaseMaterial(currentVirologus.getField().getPacket(), m);
					}
			} else
				log("Cant collect "+input[1]);
				
			if(can)
				log("v" + getVirologusSzam(currentVirologus) + " collected "+ input[1]);
		}
		catch (NumberFormatException ex) {
			
		}
			
	}
	
	private static void addSomething(String[] readed) {
		int szam = Character.getNumericValue(readed[2].charAt(1)) - 1;  //virologus jelzo utani azonosito(hanyadik virologusra hasznaljuk a dolgot);
		//az add utani parancs alapjan folytatodik
		switch(readed[1]) {
		case "cloak":
			if(readed[2].charAt(0) == 'f') {   
				game.getMap().getField(szam).addItem(new Cloak());
				log("A cloak has been added to "+readed[2]);
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
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Stun());
			}
		break;
		case "vitusdance":
			if(readed[2].charAt(0) == 'f') {
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Vitusdance());
			}
		break;
		case "protection":
			if(readed[2].charAt(0) == 'f') {
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Protection());
			}
		break;
		case "forget":
			if(readed[2].charAt(0) == 'f') {
				log("Nem lehet fieldhez adni agenst!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).addAgens(new Forget());
			}
		break;
		case "aminosav":					
			if(readed[2].charAt(0) == 'f') { 
				if(game.getMap().getField(szam).getPacket() != null)
					game.getMap().getField(szam).getPacket().addMaterial(new Aminosav());  //fieldnek egy addMaterialt kell letrehozni.
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(new Aminosav());
			}
		break;
		case "nukleotid":
			if(readed[2].charAt(0) == 'f') {   
				if(game.getMap().getField(szam).getPacket() != null)
					game.getMap().getField(szam).getPacket().addMaterial(new Nukleotid());  //fieldnek egy addMaterialt kell letrehozni.
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(new Nukleotid());
			}
		break;
		case "packet":
			Nukleotid n = new Nukleotid();
			Aminosav a = new Aminosav();
			int max = ((Virologus)game.getEntityAt(szam)).getPacket().getMaxMaterial();
			n.setValue(max);
			a.setValue(max);
			if(readed[2].charAt(0) == 'f') {   
				if(game.getMap().getField(szam).getPacket() != null) {
					game.getMap().getField(szam).getPacket().addMaterial(a);
					game.getMap().getField(szam).getPacket().addMaterial(n);
				}
				else
					log("Bad parameter!");
			}else if(readed[2].charAt(0) == 'v') {
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(a);
				((Virologus)game.getEntityAt(szam)).getPacket().addMaterial(n);
			}
			break;
		default:
			log("Bad parameter!");
			break;
		}
	}
	
	public static void leave(String[] input)
	{
		String sub = input[1];
		Item it = currentVirologus.getItem(sub);
		if(it!= null)
		{
			currentVirologus.leaveItem(it);
			log(input[1] + " has been removed from v"+ getVirologusSzam(currentVirologus) );
		}
		else
			MyRunnable.log("Bad parameter!");
	}
	
	private static int steps; 
	private static boolean tartKor;
	public static void getInputFirstAct() {
		String[] readed;
		steps = 2;
		tartKor = true;
		while(tartKor) {
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
					if(steps > 0)
						currentVirologus.getField().touching(currentVirologus);
					///kirni a dolgokat-------------------------
					break;
				case "move":
					if(steps > 0) {
						if (readed.length == 2) {
							moveTo(readed[1]);
						}
						else log("Bad parameter!");
					}
					break;
				case "create":
					if(steps > 0) {
						int elozo = currentVirologus.getAgensHave().size() ;
						if (readed.length == 2)
							currentVirologus.createAgens(readed[1]);
						else 
							log("Bad parameter!");
						if(currentVirologus.getAgensHave().size() == elozo)
							log("");
						else
							log("v"+getVirologusSzam(currentVirologus)+" created a "+readed[1]);
					}
					break;
				case "finishturn" : 
					steps = 0;
					tartKor = false;
					log("player in row: v" + getVirologusSzam(currentVirologus));
					getInfo();
					ArrayList<Field> n = currentVirologus.getField().getNeighbourhood();
					String kimenet = "Player can move to: ";
					for(Field f : n)
						for(int i = 0; i < game.getMap().getSize(); i++)
							if(f == game.getMap().getField(i))
								kimenet.concat("f"+(i+1)+", ");
					log(kimenet);
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
					if (readed.length == 1) {
						learn();
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
					tartKor = false;
					log("player in row: v" + getVirologusSzam(currentVirologus));
					getInfo();
					ArrayList<Field> n = currentVirologus.getField().getNeighbourhood();
					String kimenet = "Player can move to: ";
					for(Field f : n)
						for(int i = 0; i < game.getMap().getSize(); i++)
							if(f == game.getMap().getField(i))
								kimenet.concat("f"+(i+1)+", ");
					log(kimenet);
						
					break;
				default : 
					log("Bad parameter!");
					break;
			}
			justinfo--;
		}
	}
	
	private static boolean testfromFile = false;;
	
	//init commands
	public static void start() {
		game = new Game();
		String[] readed;
		boolean megy = true;
		while(megy) {
			readed = read();
			switch(readed[0]) {
			case "test" :
				if(readed.length == 2) {
					testfromFile = true;
					scanner = new Scanner(readed[1]);
				}
				break;
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
					log("player in row: v" + getVirologusSzam(currentVirologus));
					getInfo();
					ArrayList<Field> n = currentVirologus.getField().getNeighbourhood();
					String kimenet = "Player can move to: ";
					for(Field f : n)
						for(int i = 0; i < game.getMap().getSize(); i++)
							if(f == game.getMap().getField(i))
								kimenet.concat("f"+(i+1)+", ");
					log(kimenet);
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
