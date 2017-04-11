import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class SalasananVahvuus {   
	private static final Scanner lukija = new Scanner(System.in);    
	public static void main(String[] args) {  
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() { 
				System.out.println("Aika loppui");
				System.exit(0);
			}
		};
		System.out.println("Sinulla on 30 sekuntia aikaa antaa salasana");
		timer.schedule(task, 30 * 1000);
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
	private static void tulostaSalasana(String salasana) throws FileNotFoundException {
		final Scanner tLukija = new Scanner(new File("salasana.txt"));
		System.out.println("Salasanasi on: " +tLukija.nextLine());
		tLukija.close();
		System.exit(0);


	}  
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

		//System.out.println("Anna salasana, sen tulee sisältää ainakin: " + sanat + " Salasana ei saa siältää välilyöntejä, huutomerkkejä eikä se saa olla yli 50 merkkiä pitkä.");


		boolean testi = true;
		if(!salasananPituus(salasana, sanat)){testi = false;}
		if(!salasananNumero(salasana, sanat)){testi = false;}
		if(!salasananTarkistaMerkit(salasana, sanat)){testi = false;}
		if(!salasananHUUTO(salasana, sanat)){testi = false;}
		if(!salasananAekkoset(salasana, sanat)){testi = false;}
		if(!salasananKAKKA(salasana, sanat)){testi = false;}
		if(!salasananVaelilyonti(salasana, sanat)){testi = false;}
		if(!salasananIsotKirjaimet(salasana, sanat)){testi = false;}
		if(!salasananYli50merkkia(salasana, sanat)){testi = false;}
		return testi;  
	}  
	private static boolean salasananYli50merkkia(String salasana, ArrayList<String> sanat) {
		if (salasana.length() > 50){  
			System.out.println(sanat.get(6));  
			return false; 
		}
		return true;
	}
	private static boolean salasananIsotKirjaimet(String salasana, ArrayList<String> sanat) {
		if(salasana.equals(salasana.toLowerCase())){
			System.out.println(sanat.get(1));
			return false;
		}

		return true;
	}
	private static boolean salasananVaelilyonti(String salasana, ArrayList<String> sanat) {
		if (salasana.contains(" ")){ 
			System.out.println(sanat.get(4)); 
			return false; 
		} 
		return true;
	}
	private static boolean salasananKAKKA(String salasana, ArrayList<String> sanat) {
		if (!salasana.contains("KAKKA")){ 
			System.out.println(sanat.get(7)); 
			return false;
		}  
		return true;
	}
	private static boolean salasananAekkoset(String salasana, ArrayList<String> sanat) {
		if(!salasana.matches(".*[äöÄÖ]+.*")){
			System.out.println(sanat.get(3));
			return false;
		}
		return true;
	}
	private static boolean salasananHUUTO(String salasana, ArrayList<String> sanat) {
		if (salasana.contains("!")){ 
			System.out.println(sanat.get(5)); 
			return false;  
		} 
		return true;
	}
	private static boolean salasananNumero(String salasana, ArrayList<String> sanat) {
		if (!salasana.matches(".*\\d+.*")){  
			System.out.println(sanat.get(2));  
			return false; 
		}  
		return true;
	}
	private static boolean salasananPituus(String salasana, ArrayList<String> sanat) {
		if (salasana.length() < 10){
			System.out.println(sanat.get(0));  
			return false;  
		}
		return true;
	}
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
	private static String lueSalasana() throws FileNotFoundException {  
		PrintWriter kirjoittaja = new PrintWriter("Salasana.txt");
		System.out.println("Anna salasana, sen tulee sisältää ainakin 10 merkkiä, isoja ja pieniä kirjaimia, numeroita sekä ääkkösiä. Ei saa olla välilyöntiä eikä huutomerkkiä, salasana ei myöskään saa olla yli 50 merkkiä pitkä");  
		String salasana = lukija.nextLine();

		kirjoittaja.print(salasana);
		kirjoittaja.close();


		return salasana;  
	}   


} 