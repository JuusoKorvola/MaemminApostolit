import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * P��luokka SalasananVahvuus joka sis�lt�� ohjelman funktioineen
 * @author juuso_000
 *
 */
public class SalasananVahvuus {   
	private static final Scanner lukija = new Scanner(System.in);    
	/**
	 * Main funktioniin sis�lletty ajastin funktio joka lopettaa ohjelman 30 sekunnin kuluessa. T�m� voidaan my�s ajaa cmd linest� parametreill� tai ilman. Ilman parametria salasanansy�tt� terminoituu 30 sekunnin kuluessa.
	 * @param args String[] on siis taulukko tyyppi�, jossa annetaan indeksille arvoksi 30 sekuntia mik�li cmd linest� ei anneta jotakin toista parametria
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
		 * Try catch joka ottaa kiinni filenotfoundexceptionin mik�li tiedostoa ei l�ydy tai se on v��r�ss� paikassa tai se on esimerkiksi hakemisto.
		 */
		try {
			String salasana = lueSalasana();  
			while (salasananMuuttujat(salasana) == false){  
				salasana = lueSalasana();  
			}  
			tulostaSalasana(salasana); 
		}
		catch (FileNotFoundException f){
			System.out.println("Tiedostoa ei l�ytynyt.");
		}
	}  
	/**
	 * Tulostaa salasanan tiedostosta joka tallennettiin funktiossa lueSalasana, k�ytet��n string -tyyppist� parametria "salasana" jotta annettu salasana saadaan k�ytett�v�ksi.
	 * @param String salasana saadaan funktiosta lueSalasana
	 * @throws FileNotFoundException mik�li tiedosto.txt ei ole olemassa.
	 */
	private static void tulostaSalasana(String salasana) throws FileNotFoundException {
		final Scanner tLukija = new Scanner(new File("salasana.txt"));
		System.out.println("Salasanasi on: " +tLukija.nextLine());
		tLukija.close();
		System.exit(0);


	}  
	/**
	 * Mukana taas parametri salasana. Lis�t��n listaan erilaisia merkkijonoja alkioihin joita k�ytet��n salasananvahvuutta mittaavissa funktioissa.
	 * @param salasana string joka saadaan lueSalasana funktiosta
	 * @return False mik�li kaikki kriteerit eiv�t t�yty
	 */
	public static boolean salasananMuuttujat(String salasana ) {
		ArrayList<String> sanat = new ArrayList<String>();
		sanat.add ("Salasana on alle 10 merkki� pitk�");
		sanat.add ("Salasanassa pit�� olla isoja ja pieni� kirjaimia");
		sanat.add ("Salasanassa pit�� olla numero");
		sanat.add ("Salasanassa pit�� olla ��kk�si�");
		sanat.add ("Salasanassa ei saa olla v�lily�nti�");
		sanat.add ("Salasanassa ei saa olla huutomerkki�");
		sanat.add ("Salasana ei saa olla yli 50 merkki� pitk�");
		sanat.add ("Salasanasta puuttuu sana KAKKA");
		sanat.add ("Salasanassa on kiellettyj� merkkej� ';','�','�'");



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
	 * Mik�li salasana on yli 50 merkki� pitk�, palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananYli50merkkia(String salasana, ArrayList<String> sanat) {
		if (salasana.length() > 50){  
			System.out.println(sanat.get(6));  
			return false; 
		}
		return true;
	}
	/**
	 * mik�li salasanassa ei ole isoja kirjaimia, palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananIsotKirjaimet(String salasana, ArrayList<String> sanat) {
		if(salasana.equals(salasana.toLowerCase())){
			System.out.println(sanat.get(1));
			return false;
		}

		return true;
	}
	/**
	 * Mik�li salasana sis�lt�� v�lily�nnin, palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananVaelilyonti(String salasana, ArrayList<String> sanat) {
		if (salasana.contains(" ")){ 
			System.out.println(sanat.get(4)); 
			return false; 
		} 
		return true;
	}
	/**
	 * Mik�li salasana ei sis�ll� sanaa "data" palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananData(String salasana, ArrayList<String> sanat) {
		if (!salasana.contains("data")){ 
			System.out.println(sanat.get(7)); 
			return false;
		}  
		return true;
	}
	/**
	 * Mik�li salasanasta ei l�ydy ��kk�si� palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananAekkoset(String salasana, ArrayList<String> sanat) {
		if(!salasana.matches(".*[����]+.*")){
			System.out.println(sanat.get(3));
			return false;
		}
		return true;
	}
	/**
	 * Jos salasana sis�lt�� huutomerkin palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananHUUTO(String salasana, ArrayList<String> sanat) {
		if (salasana.contains("!")){ 
			System.out.println(sanat.get(5)); 
			return false;  
		} 
		return true;
	}
	/**
	 * Jos salasana ei sis�ll� numeroita palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananNumero(String salasana, ArrayList<String> sanat) {
		if (!salasana.matches(".*\\d+.*")){  
			System.out.println(sanat.get(2));  
			return false; 
		}  
		return true;
	}
	/**
	 * Jos salasana on alle 10 merkki� pitk� palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	private static boolean salasananPituus(String salasana, ArrayList<String> sanat) {
		if (salasana.length() < 10){
			System.out.println(sanat.get(0));  
			return false;  
		}
		return true;
	}
	/**
	 * Jos salasana sis�lt�� kiellettyj� merkkeh� kuten @, � tai ; niin palauttaa funktio arvon false
	 * @param salasana String tyyppinen parametri saadaan lueSalasana funktiosta
	 * @param sanat Listasta l�ytyv�t printlinet jotka kertovat mit� funktiossa vaaditaan k�ytt�j�lt�
	 * @return true jos salasana l�p�isee kriteerin
	 */
	public static boolean salasananTarkistaMerkit(String salasana, ArrayList<String> sanat){
		ArrayList<String> v��r�tmerkit = new ArrayList<String>();
		v��r�tmerkit.add("�");
		v��r�tmerkit.add("�");
		v��r�tmerkit.add(";");
		for (String merkki : v��r�tmerkit) {
			if (salasana.contains(merkki)){
				System.out.println(sanat.get(8));
				return false;
			}
		}
		return true;
	}
	/**
	 * Funktion avulla kysyt��n k�ytt�j�lt� salasana lukija scannerin avulla. Salasana tallennetaan kirjoittajan avulla tiedosto.txt nimiseen tiedostoon.
	 * @return Palauttaa String tyyppisen muuttujan joka saadaan k�ytt�j�n inputista
	 * @throws FileNotFoundException Mik�li tiedostoa ei l�ydy.
	 */
	private static String lueSalasana() throws FileNotFoundException {  
		PrintWriter kirjoittaja = new PrintWriter("Salasana.txt");
		System.out.println("Anna salasana, sen tulee sis�lt�� ainakin 10 merkki�, sanan 'data',  isoja ja pieni� kirjaimia, numeroita sek� ��kk�si�. Ei saa olla v�lily�nti�, merkkej� �, @ tai ; eik� huutomerkki�, salasana ei my�sk��n saa olla yli 50 merkki� pitk�");  
		String salasana = lukija.nextLine();

		kirjoittaja.print(salasana);
		kirjoittaja.close();


		return salasana;  
	}   


} 