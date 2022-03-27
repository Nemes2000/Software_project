
/*Skeleton osztĂˇly a program futĂˇsa sorĂˇn felmerĂĽlĹ‘ kĂĽlĂ¶nbĂ¶zĹ‘ szituĂˇciĂłkat kĂ©szĂ­t elĹ‘ Ă©s indĂ­t el
 *Az esemĂ©nyek tĂ¶bb osztĂˇly tĂ¶bb fĂĽggvĂ©nyĂ©bĹ‘l Ă©pĹ±lnek fel
 *Ezek kĂ¶zĂ¶tti kapcsolatot vizsgĂˇlja  
 */
import java.util.Scanner;

public class Skeleton {
	// egy virolĂłgus egy mezĹ‘rĹ‘l egy mĂˇsik mezĹ‘re valĂł lĂ©ptetĂ©sĂ©t prezentĂˇlja
	public void move() {
		Field hely = new Field();
		Field cel = new Field();
		Virologus virologus = new Virologus();
		virologus.setField(hely);

		/*
		 * kell Skeletonba a Game? Game game= new Game(); lĂ©trehozott virologus
		 * hozzĂˇdĂˇsa az Ă¶sszes entitt listĂˇjĂˇhoz game.addEntity(virulogus); //itt igy
		 * kene de itt a felhasznalo kell valasszon ami elĂ©ggĂ© funkcionalitĂˇs mĂˇr(vagyis
		 * a step()- fĂĽggvĂ©ny meg kell legyen Ă­rva konzolos vĂˇlasztĂˇssal.) game.step();
		 */

		virologus.move(cel);
	}

	// egy virologus egy laboron all es megtanulja a rajta levo genetikus kodot
	public void geneticCodeLearn() {
		Virologus virologus = new Virologus();
		Lab lab = new Lab();
		virologus.setField(lab);
		/*
		 * System.out.println("Valassz egy genetikus kodot amit meg szeretnel tanulni");
		 * System.out.println("1.Kabitas"); System.out.println("2.Vitustanc");
		 * System.out.println("3.Vedelem"); System.out.println("4.Felejtes"); Scanner
		 * myInput = new Scanner( System.in ); int beolvasott = myInput.nextInt();
		 * switch(beolvasott) { case 1: lab.setGeneticCode(new StunCode()); break; case
		 * 2: lab.setGeneticCode(new VitusdanceCode()); break; case 3:
		 * lab.setGeneticCode(new ProtectionCode()); break; case 4:
		 * lab.setGeneticCode(new StunCode()); break; }
		 */
		lab.setGeneticCode(new GeneticCode());
		virologus.touch();
	}

	// agens letrehozasa
	public void agensCreate() {
		Virologus virologus = new Virologus();
		Packet p = new Packet();
		Aminosav amino = new Aminosav();
		amino.setValue(10);
		Nukleotid nukle = new Nukleotid();
		nukle.setValue(10);
		p.addMaterial(amino);
		p.addMaterial(nukle);

		System.out.println("VĂˇlassz egy Ăˇgenst amit el szeretnĂ©l keszĂ­teni:");
		System.out.println("1.Kabitas");
		System.out.println("2.Vitustanc");
		System.out.println("3.Vedelem");
		System.out.println("4.Felejtes");
		Scanner myInput = new Scanner(System.in);
		int beolvasott = myInput.nextInt();
		switch (beolvasott) {
		case 1:
			StunCode codeStun = new StunCode();
			virologus.learnGeneticCode(codeStun);
			addAgens(codeStun.createAgens(virologus));
			break;
		case 2:
			VitusdanceCode codeVitus = new VitusdanceCode();
			virologus.learnGeneticCode(codeVitus);
			addAgens(codeVitus.createAgens(virologus));
			break;
		case 3:
			ProtectionCode codeProt = new ProtectionCode();
			virologus.learnGeneticCode(codeProt);
			addAgens(codeProt.createAgens(virologus));
			break;
		case 4:
			ForgetCode codeForget = new ForgetCode();
			virologus.learnGeneticCode(codeForget);
			addAgens(codeForget.createAgens(virologus));
			break;
		// NEM JĂ“, Ă�T KELL GONDOLNI.
		}
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
		virologus.addItem(item);
		virologus.leaveItem();
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
		System.out.println("7 - TĂˇrgy felvetel");
		System.out.println("8 - Targy lerakas");
		System.out.println("9 - Lopas anyag");
		System.out.println("9 - Lopas targy");
	}

	// megakasztja a program futasat amig egy 0at nem kap.
	// igy a felhasznalo tanulmanyozhatja a tesztesetet
	public void caseMenuPresent() {
		System.out.println("0-Exit");
		Scanner myInput = new Scanner(System.in);
		int beolvasott;
		while (beolvasott != 0) {
			beolvasott = myInput.nextInt();
		}
	}

	public void start() {
		boolean megy = true;
		int beolvasott;
		try (Scanner myInput = new Scanner(System.in)) { // ha esetleg hibas adatot kapna
			// Egy vĂ©gtelen ciklus a menĂĽ vezĂ©rlĂ©shez
			while (megy) {
				menuPresent();
				beolvasott = myInput.nextInt();

				// menĂĽpont kivĂˇlasztĂˇsa
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
					caseMegy = caseMenuPresent();
					break;
				case 8:
					break;
				case 9:
					caseMegy = caseMenuPresent();
					break;
				case 0:
					megy = false;
					caseMegy = caseMenuPresent();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("MenĂĽpont kivĂˇlasztĂˇsi hiba");
		}
	}
}
