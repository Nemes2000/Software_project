
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

	// egy virológus egy mezőről egy másik mezőre való léptetését prezentálja
	public void move() {
		Field hely = new Field();
		Field cel = new Field();
		Virologus virologus = new Virologus();
		virologus.setField(hely);
		virologus.move(cel);

		/*
		 * Game game= new Game(); game.addEntity(virulogus); game.step();
		 * 
		 * kell Skeletonba a Game? létrehozott virologus hozzádása az összes entiti
		 * listájához itt igy kene de itt a felhasznalo kell valasszon ami eléggé
		 * funkcionalitás már(vagyis a step()- függvény meg kell legyen írva konzolos
		 * választással.)
		 */
	}

	// egy virológus letapogatja a genetikus kódot a Laborról amin áll
	public void geneticCodeLearn() {
		Virologus virologus = new Virologus();
		Lab lab = new Lab();
		virologus.setField(lab);
		// Ez az egész rész kihagyható ha generálódik a Labornak egy genetikus kód a
		// konstruktorában.
		System.out.println("Milyen genetikus kód legyen a laborban?");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		GeneticCode gc;
		Scanner myInput = new Scanner(System.in);
		// a felhasználó választ egy ágenst
		int beolvasott = myInput.nextInt();
		switch (beolvasott) {
		case 1:
			gc = new StunCode();
			break;
		case 2:
			gc = new VitusdanceCode();
			break;
		case 3:
			gc = new ProtectionCode();
			break;
		case 4:
			gc = new ForgetCode();
			break;
		}
		lab.setGeneticCode(gc); //
		lab.touching(virologus); // ezt a Game touch() függvénye fogja a későbbiekben kezelni
	}

	// ágens létrehozása, a felhasználó válassza ki a kívánt ágenst
	// a kiválasztott ágens genetikus kódja feltételezzük ismert a virológusnak
	public void agensCreate() {
		Virologus virologus = new Virologus();
		// genetikus kód inicializálás, felhasználó választ
		GeneticCode gc = initGeneticCode();
		Aminosav aminoCost = new Aminosav();
		aminoCost.setValue(5);
		gc.setCost(aminoCost); // GenetikusKód generál egy random receptet, de nem állít értéket Aminonak meg
								// Nukleotidnak.
		// zseb tartalmának megtöltése
		Aminosav amino = new Aminosav();
		amino.setValue(10);
		Nukleotid nukleo = new Nukleotid();
		nukleo.setValue(10);
		Packet packet = virologus.getPacket();
		packet.handleMaterialSeparate(amino, packet);
		packet.handleMaterialSeparate(nukleo, packet);
		// genetikus kód legyártása
		virologus.learnGeneticCode(gc);
		gc.createAgens(virologus); // későbbiekben a Game step() függvénye kezeli.
	}

	// agens hasznalata magan
	public void agensUseOnMe() {
		Virologus virologus = new Virologus();
		System.out.println("Valassz egy agenst amit magadra kennel");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		Scanner myInput = new Scanner( System.in );
		int beolvasott = myInput.nextInt();
		switch(beolvasott) {
			case 1:
				virologus.uRAttacked(new Stun(), virologus);
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

	// agens kenese ellensegen
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
		switch (beolvasott) {
		case 1:
			virologus.uRAttacked(new Stun(), ellenseg);
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

	// anyag gyujtese eset
	public void materialCollect() {
		Virologus virologus = new Virologus();
		Storage storage = new Storage();
		virologus.setField(storage);
		virologus.touch();
	}

	// targy felvetel eset
	public void itemPickUp() {
		Virologus virologus = new Virologus();
		Shelter shelter = new Shelter();
		virologus.setField(shelter);
		shelter.setItem(new Item());
		virologus.touch();
	}

	// targy eldobasa eset
	public void itemDrop() {
		Virologus virologus = new Virologus();
		Item item = new Item();
		virologus.setItem(item);
		virologus.leaveItem(item);
	}

	// anyag lopasa eset
	public void stealMaterial() {
		Virologus virologus = new Virologus();
		Virologus ellenseg = new Virologus();
		ellenseg.stealMaterialAttempt(virologus);
	}

	// targy lopasa eset
	public void stealItem() {
		Virologus virologus = new Virologus();
		Virologus ellenseg = new Virologus();
		ellenseg.stealItemAttempt(ellenseg);
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
		System.out.println("9 - Lopas targy");
	}

	// megakasztja a program futasat amig egy 0at nem kap.
	// igy a felhasznalo tanulmanyozhatja a tesztesetet
	public void caseMenuPresent() {
		System.out.println("0-Exit");
		int beolvasott = 1; // 1re inicializáltuk, hogy lépjen be a ciklusba
		Scanner myInput = new Scanner(System.in);
		while (beolvasott != 0) {
			beolvasott = myInput.nextInt();
		}
	}

	public void start() {
		boolean megy = true;
		int beolvasott;
		try (Scanner myInput = new Scanner(System.in)) { // ha esetleg hibas adatot kapna
			// Egy végtelen ciklus a menü vezérléshez
			while (megy) {
				menuPresent();
				beolvasott = myInput.nextInt();

				// menüpont kiválasztása
				switch (beolvasott) {
				case 1:
					move();
					caseMenuPresent(); // a program 0 inputig ebben az allapotban marad
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
					stealMaterial();
					caseMenuPresent();
					break;
				case 7:
					stealItem();
					caseMenuPresent();
					break;
				case 8:
					break;
				case 9:
					caseMenuPresent();
					break;
				case 0:
					megy = false;
					caseMenuPresent();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Menüpont kiválasztási hiba");
		}
	}
}
