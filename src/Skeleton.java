
/*Skeleton osztály a program futása során felmerülő különböző szituációkat készít elő és indít el
 *Az események több osztály több függvényéből épűlnek fel
 *Ezek közötti kapcsolatot vizsgálja  
 */
import java.util.Scanner;

public class Skeleton {
	// Kódduplikáció miatt jött létre
	// Egy menü, amiben a felhasználó által választott ágenssel
	// tér vissza
	public Agens initAgens() {
		System.out.println("Válassz egy ágenst:");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		Scanner myInput = new Scanner(System.in);
		// a felhasználó választ egy ágenst
		while (true) {
			int beolvasott = myInput.nextInt();
			switch (beolvasott) {
			case 1:
				return new Stun();
			case 2:
				return new Vitusdance();
			case 3:
				return new Protection();
			case 4:
				return new Forget();
			}
		}
	}
	
	// Kódduplikáció miatt jött létre
		// Egy menü, amiben a felhasználó által választott genetikus kóddal
		// tér vissza
	public GeneticCode initGeneticCode() {
		System.out.println("Válassz egy ágenst:");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		Scanner myInput = new Scanner(System.in);
		// a felhasználó választ egy ágenst
		while (true) {
			int beolvasott = myInput.nextInt();
			switch (beolvasott) {
			case 1:
				return new StunCode();
			case 2:
				return new VitusdanceCode();
			case 3:
				return new ProtectionCode();
			case 4:
				return new ForgetCode();
			}
		}
	}

	// egy entitás egy mezőről egy másik mezőre való léptetését prezentálja
	public void move() {
		Field hely = new Field();
		Field cel = new Field();
		Entity entity = new Entity();
		entity.setField(hely); //az entitást a helyére helyezzük
		entity.move(cel);  //az entitás a cél mezőre lépteti
		//A Game step() függvénye kezeli a későbbiekben.
	}

	// egy virológus megtanul egy genetikus kódot
	public void geneticCodeLearn() {
		Virologus virologus = new Virologus();
		System.out.println("Milyen genetikus kód legyen a laborban?");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		GeneticCode gc;
		Scanner myInput = new Scanner(System.in);
		// a felhasználó választ egy genetikus kódot
		int beolvasott = myInput.nextInt();
		switch (beolvasott) {
		case 1:
			gc = new StunCode();
			virologus.learnGeneticCode(gc); //a virológus megtanul egy genetikus kódot
			break;
		case 2:
			gc = new VitusdanceCode();
			virologus.learnGeneticCode(gc);
			break;
		case 3:
			gc = new ProtectionCode();
			virologus.learnGeneticCode(gc);
			break;
		case 4:
			gc = new ForgetCode();
			virologus.learnGeneticCode(gc);
			break;
		}
	}

	// ágens létrehozása, a felhasználó válassza ki a kívánt ágenst
	// a kiválasztott ágens genetikus kódját is inicializáljuk
	public void agensCreate() {
		Virologus virologus = new Virologus();
		// genetikus kód inicializálás, felhasználó választ
		GeneticCode gc = initGeneticCode();
		Aminosav aminoCost = new Aminosav();
		aminoCost.setValue(5);
		//virológus zsebének tartalmának megtöltése
		Aminosav amino = new Aminosav();
		amino.setValue(10);
		Nukleotid nukleo = new Nukleotid();
		nukleo.setValue(10);
		Packet packet = virologus.getPacket();
		packet.handleMaterialSeparate(amino, packet);
		packet.handleMaterialSeparate(nukleo, packet);
		System.out.println("Init vége!");
		virologus.learnGeneticCode(gc);
		// genetikus kód legyártása
		gc.createAgens(virologus); // későbbiekben a Game step() függvénye kezeli.
	}

	// ágens használata magán
	public void agensUseOnMe() {
		Virologus virologus = new Virologus();
		System.out.println("Valassz egy agenst amit magadra kennel");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		Scanner myInput = new Scanner(System.in);
		int beolvasott = myInput.nextInt();
		switch (beolvasott) {
		case 1:
			virologus.uRAttacked(new Stun(), virologus);  //a kiválasztott ágenst magán használja
			break;
		case 2:
			virologus.uRAttacked(new Vitusdance(), virologus);
			break;
		case 3:
			virologus.uRAttacked(new Protection(), virologus);
			break;
		case 4:
			virologus.uRAttacked(new Forget(), virologus);
			break;
		}
	}

	// ágens használata ellenségen
	public void agensUseEnemy() {
		Virologus virologus = new Virologus();
		Virologus ellenseg = new Virologus();
		System.out.println("Valassz egy agenst amit magadra kennel");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		Scanner myInput = new Scanner(System.in);
		int beolvasott = myInput.nextInt();
		//a virológusunknak inicializáljuk az összes ágenst, hogy bármelyiket tudja használni
		virologus.addAgensOnMe(new Vitusdance());
		virologus.addAgensOnMe(new Protection());
		virologus.addAgensOnMe(new Stun());
		virologus.addAgensOnMe(new Forget());
		System.out.println("Init vége!");
		switch (beolvasott) {
		case 1:
			virologus.uRAttacked(new Stun(), ellenseg); //az ellenségen használja az ágenst
			break;
		case 2:
			virologus.uRAttacked(new Vitusdance(), ellenseg);
			break;
		case 3:
			virologus.uRAttacked(new Protection(), ellenseg);
			break;
		case 4:
			virologus.uRAttacked(new Forget(), ellenseg);
			break;
		}
	}

	// anyag gyűjtése Storage mezőről
	public void materialCollect() {
		Virologus virologus = new Virologus();
		Storage storage = new Storage();
		virologus.setField(storage); //virológus elhelyezése a Storagen
		System.out.println("Init vége!");
		Packet p = virologus.getPacket();
		virologus.increaseMaterial(p); //virológus felveszi az anyagokat
	}

	// tárgy felvétele Shelter mezőről
	public void itemPickUp() {
		Virologus virologus = new Virologus();
		Shelter shelter = new Shelter();
		Item i = new Item();
		virologus.setField(shelter); //virológust óvóhelyre helyezünk
		shelter.addItem(i);	//óvóhelyhez tárgyat adunk hozzá
		System.out.println("Init vége!");
		virologus.pickUpItem(shelter.getItems()); //virológus felveszi a tárgyat
	}

	// tárgy eldobása
	public void itemDrop() {
		Virologus virologus = new Virologus();
		Field f = new Shelter();
		virologus.setField(f); //egy virológust a mezőre helyezünk
		Item item = new Item();
		virologus.setItem(item);
		virologus.leaveItem(); //a virológus eldobja a tárgyát
	}

	// anyag lopása
	public void stealMaterial() {
		Virologus virologus = new Virologus();
		Virologus ellenseg = new Virologus(); //ettől a virológustól fogunk lopni
		ellenseg.addAgensOnMe(new Stun()); //ahoz hogy lopni tudjunk Stunnolva kell legyen
		ellenseg.getPacket().addMaterial(new Nukleotid()); //adunk neki anyagot amit ellopunk
		
		System.out.println("Init vége!");
		ellenseg.stealMaterialAttempt(virologus); //anyag ellopása
	}

	// tárgy ellopása
	public void stealItem() {
		Virologus virologus = new Virologus();
		Virologus ellenseg = new Virologus(); //ettől a virológustól fogunk lopni
		ellenseg.addAgensOnMe(new Stun()); //ahoz hogy lopni tudjunk Stunnolva kell legyen
		ellenseg.addItem(new Item());//adunk neki tárgyat amit ellopunk
		System.out.println("Init vége!");

		ellenseg.stealItemAttempt(virologus); //tárgy ellopása
	}

	// a menupontok kiirasa
	public void menuPresent() {
		System.out.println("1 - Mozgas");
		System.out.println("2 - Genetikus kod tanulasa");
		System.out.println("3 - Agens keszites");
		System.out.println("4 - Agens hasznalata magan");
		System.out.println("5 - Agens hasznalata mason");
		System.out.println("6 - Anyag gyujtes");
		System.out.println("7 - Tárgy felvetel");
		System.out.println("8 - Targy lerakas");
		System.out.println("9 - Lopas anyag");
		System.out.println("10 - Lopas targy");
		System.out.println("0 - Exit");
	}

	// megakasztja a program futását, amíg egy 0-át nem kap.
	// így a felhasználó tanulmányozhatja a tesztesetet
	public void caseMenuPresent() {
		System.out.println("0 - Back");
		int beolvasott  = 1; // 1-re inicializáltuk, hogy lépjen be a ciklusba
		Scanner in = new Scanner(System.in);
		while (beolvasott != 0) {
			beolvasott = in.nextInt();
		}
	}
	
	//ezt a függvényt használjuk főmenünek, ami kezeli az eseteket
	public void start() {
		boolean megy = true;
		int beolvasott;  //menüpont választó
		// ha esetleg hibás adatot kapna
		 try (Scanner in = new Scanner(System.in)) {
	    // Egy végtelen ciklus a menü vezérléshez
			while (megy) {
				menuPresent();
				beolvasott = in.nextInt(); //beolvassa a felhasználó által adott adatot

				// menüpont kiválasztása
				switch (beolvasott) {
				case 1:
					move();
					caseMenuPresent(); // a program 0 inputig ebben az állapotban marad
					break;
				case 2:
					geneticCodeLearn();
					caseMenuPresent();
					break;
				case 3:
					agensCreate();
					caseMenuPresent();
					break;
				case 4:
					agensUseOnMe();
					caseMenuPresent();
					break;
				case 5:
					agensUseEnemy();
					caseMenuPresent();
					break;
				case 6:
					materialCollect();
					caseMenuPresent();
					break;
				case 7:
					itemPickUp();
					caseMenuPresent();
					break;
				case 8:
					itemDrop();
					caseMenuPresent();
					break;
				case 9:
					stealMaterial();
					caseMenuPresent();
					break;
				case 10:
					stealItem();
					caseMenuPresent();
					break;
				case 0:
					megy = false; //program leállítása
					break;
				default:
					break;
				}
			}
		}catch(Exception e) {System.out.println("Input fail!");}
	}
}
