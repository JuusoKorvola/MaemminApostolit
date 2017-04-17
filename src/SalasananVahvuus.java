import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Pääluokka SalasananVahvuus joka sisältää ohjelman funktioineen
 * @author juuso_000
 *
 */
public class SalasananVahvuus {   
	private static final Scanner lukija = new Scanner(System.in);    
	/**
	 * Main funktioniin sisälletty ajastin funktio joka lopettaa ohjelman 30 sekunnin kuluessa. Tämä voidaan myös ajaa cmd linestä parametreillä tai ilman. Ilman parametria salasanansyöttö terminoituu 30 sekunnin kuluessa.
	 * @param args String[] on siis taulukko tyyppiä, jossa annetaan indeksille arvoksi 30 sekuntia mikäli cmd linestä ei anneta jotakin toista parametria
	 */
	public static void main(String[] args) {  
		int ajastin = 30;
		if(args[0] != null){
			ajastin = Integer.parseInt(args[0]);
		}
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() { 
				System.out.println("Aika loppui");
				System.exit(0);
			}
		};
		System.out.println("Sinulla on " + ajastin + " sekuntia aikaa antaa salasana");
		timer.schedule(task, ajastin * 1000);
		/**
		 * Try catch joka ottaa kiinni filenotfoundexceptionin mikäli tiedostoa ei löydy tai se on väärässä paikassa tai se on esimerkiksi hakemisto.
		 */
		try {
			String salasana = lueSalasana();  
			while (salasananMuuttujat(salasana) == false){  
				salasana = lueSalasana();  
			}  
			tulostaSalasana(salasana); 
		}
		catch (FileNotFoundException f){
			System.out.println("Tiedostoa ei löytynyt.");
		}
	}  
	/**
	 * Tulostaa salasanan tiedostosta joka tallennettiin funktiossa lueSalasana, käytetään string -tyyppistä parametria "salasana" jotta annettu salasana saadaan käytettäväksi.
	 * @param String salasana saadaan funktiosta lueSalasana
	 * @throws FileNotFoundException mikäli tiedosto.txt ei ole olemassa.
	 */
	private static void tulostaSalasana(String salasana) throws FileNotFoundException {
		final Scanner tLukija = new Scanner(new File("salasana.txt"));
		System.out.println("Salasanasi on: " +tLukija.nextLine());
		tLukija.close();
		System.exit(0);


	}  
	/**
	 * Mukana taas parametri salasana. Lisätään listaan erilaisia merkkijonoja alkioihin joita käytetään salasananvahvuutta mittaavissa funktioissa.
	 * @param salasana string joka saadaan lueSalasana funktiosta
	 * @return False mikäli kaikki kriteerit eivät täyty
	 */
	public static boolean salasananMuuttujat(String salasana ) {
		ArrayList<String> sanat = new ArrayList<String>();
		sanat.add ("Salasana on alle 10 merkkiä pitkä");
		sanat.add ("Salasanassa pitää olla isoja ja pieniä kirjaimia");
		sanat.add ("Salasanassa pitää olla numero");
		sanat.add ("Salasanassa pitää olla ääkkösiä");
		sanat.add ("Salasanassa ei saa olla välilyöntiä");
		sanat.add ("Salasanassa ei saa olla huutomerkkiä");
		sanat.add ("Salasana ei saa olla yli 50 merkkiä pitkä");
		sanat.add ("Salasanasta puuttuu sana KAKKA");
		sanat.add ("Salasanassa on kiellettyjä merkkejä ';','©','û'");



		boolean testi = true;
		if(!salasananPituus(salasana, sanat)){testi = false;}
		if(!salasananNumero(salasana, sanat)){testi = false;}
		if(!salasananTarkistaMerkit(salasana, sanat)){testi = false;}
		if(!salasananHUUTO(salasana, sanat)){testi = false;}
		if(!salasananAekkoset(salasana, sanat)){testi = false;}
		if(!salasananData(salasana, sanat)){testi = false;}
		if(!salasananVaelilyonti(salasana, sanat)){testi = false;}
		if(!salasananIsotKirjaimet(salasana, sanat)){testi = false;}
		if(!salasananYli50merkkia(salasana, sanat)){testi = false;}
		return testi;  
	}  
	
	/**
	 * Mikäli salasana on yli 50 merkkiä pitkä, palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananYli50merkkia(String salasana, ArrayList<String> sanat) {
		if (salasana.length() > 50){  
			System.out.println(sanat.get(6));  
			return false; 
		}
		return true;
	}
	/**
	 * mikäli salasanassa ei ole isoja kirjaimia, palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananIsotKirjaimet(String salasana, ArrayList<String> sanat) {
		if(salasana.equals(salasana.toLowerCase())){
			System.out.println(sanat.get(1));
			return false;
		}

		return true;
	}
	/**
	 * Mikäli salasana sisältää välilyönnin, palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananVaelilyonti(String salasana, ArrayList<String> sanat) {
		if (salasana.contains(" ")){ 
			System.out.println(sanat.get(4)); 
			return false; 
		} 
		return true;
	}
	/**
	 * Mikäli salasana ei sisällä sanaa "data" palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananData(String salasana, ArrayList<String> sanat) {
		if (!salasana.contains("data")){ 
			System.out.println(sanat.get(7)); 
			return false;
		}  
		return true;
	}
	/**
	 * Mikäli salasanasta ei löydy ääkkösiä palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananAekkoset(String salasana, ArrayList<String> sanat) {
		if(!salasana.matches(".*[äöÄÖ]+.*")){
			System.out.println(sanat.get(3));
			return false;
		}
		return true;
	}
	/**
	 * Jos salasana sisältää huutomerkin palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananHUUTO(String salasana, ArrayList<String> sanat) {
		if (salasana.contains("!")){ 
			System.out.println(sanat.get(5)); 
			return false;  
		} 
		return true;
	}
	/**
	 * Jos salasana ei sisällä numeroita palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananNumero(String salasana, ArrayList<String> sanat) {
		if (!salasana.matches(".*\\d+.*")){  
			System.out.println(sanat.get(2));  
			return false; 
		}  
		return true;
	}
	/**
	 * Jos salasana on alle 10 merkkiä pitkä palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	private static boolean salasananPituus(String salasana, ArrayList<String> sanat) {
		if (salasana.length() < 10){
			System.out.println(sanat.get(0));  
			return false;  
		}
		return true;
	}
	/**
	 * Jos salasana sisältää kiellettyjä merkkehä kuten @, û tai ; niin palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta löytyvät printlinet jotka kertovat mitä funktiossa vaaditaan käyttäjältä
	 * @return true jos salasana läpäisee kriteerin
	 */
	public static boolean salasananTarkistaMerkit(String salasana, ArrayList<String> sanat){
		ArrayList<String> väärätmerkit = new ArrayList<String>();
		väärätmerkit.add("©");
		väärätmerkit.add("ü");
		väärätmerkit.add(";");
		for (String merkki : väärätmerkit) {
			if (salasana.contains(merkki)){
				System.out.println(sanat.get(8));
				return false;
			}
		}
		return true;
	}
	/**
	 * Funktion avulla kysytään käyttäjältä salasana lukija scannerin avulla. Salasana tallennetaan kirjoittajan avulla tiedosto.txt nimiseen tiedostoon.
	 * @return Palauttaa String tyyppisen muuttujan joka saadaan käyttäjän inputista
	 * @throws FileNotFoundException Mikäli tiedostoa ei löydy.
	 */
	private static String lueSalasana() throws FileNotFoundException {  
		PrintWriter kirjoittaja = new PrintWriter("Salasana.txt");
		System.out.println("Anna salasana, sen tulee sisältää ainakin 10 merkkiä, sanan 'data',  isoja ja pieniä kirjaimia, numeroita sekä ääkkösiä. Ei saa olla välilyöntiä, merkkejä û, @ tai ; eikä huutomerkkiä, salasana ei myöskään saa olla yli 50 merkkiä pitkä");  
		String salasana = lukija.nextLine();

		kirjoittaja.print(salasana);
		kirjoittaja.close();


		return salasana;  
	}   


} 