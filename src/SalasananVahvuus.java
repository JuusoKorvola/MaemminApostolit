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
			System.out.println("Tiedostoa ei l�ytynyt.");
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
		sanat.add ("Salasana on alle 10 merkki� pitk�");
		sanat.add ("Salasanassa pit�� olla isoja ja pieni� kirjaimia");
		sanat.add ("Salasanassa pit�� olla numero");
		sanat.add ("Salasanassa pit�� olla ��kk�si�");
		sanat.add ("Salasanassa ei saa olla v�lily�nti�");
		sanat.add ("Salasanassa ei saa olla huutomerkki�");
		sanat.add ("Salasana ei saa olla yli 50 merkki� pitk�");
		sanat.add ("Salasanasta puuttuu sana KAKKA");
		sanat.add ("Salasanassa on kiellettyj� merkkej� ';','�','�'");

		//System.out.println("Anna salasana, sen tulee sis�lt�� ainakin: " + sanat + " Salasana ei saa si�lt�� v�lily�ntej�, huutomerkkej� eik� se saa olla yli 50 merkki� pitk�.");


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
		if(!salasana.matches(".*[����]+.*")){
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
	private static String lueSalasana() throws FileNotFoundException {  
		PrintWriter kirjoittaja = new PrintWriter("Salasana.txt");
		System.out.println("Anna salasana, sen tulee sis�lt�� ainakin 10 merkki�, isoja ja pieni� kirjaimia, numeroita sek� ��kk�si�. Ei saa olla v�lily�nti� eik� huutomerkki�, salasana ei my�sk��n saa olla yli 50 merkki� pitk�");  
		String salasana = lukija.nextLine();

		kirjoittaja.print(salasana);
		kirjoittaja.close();


		return salasana;  
	}   


} 