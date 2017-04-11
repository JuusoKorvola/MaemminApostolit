import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class SalasananVahvuus {   
	private static final Scanner lukija = new Scanner(System.in);    
	public static void main(String[] args) {  
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


	}  
	public static boolean salasananMuuttujat(String salasana ) {
		
		boolean testi = true;
		testi = salasananPituus(salasana);
		testi = salasananNumero(salasana);
		testi = tarkistaMerkit(salasana);
		
		
		
		if (!salasana.matches(".*\\d+.*")){  
			System.out.println("Salasanasta puuttuu numero");  
			testi = false; 
		}  
		if (!salasana.matches(".*[����]+.*")){  
			System.out.println("salasanasta puuttuu ��kk�nen");  
			testi = false; 
		} 
		if (salasana.equals(salasana.toLowerCase())){ 
			System.out.println("salasanasta puuttuu iso kirjain"); 
			testi = false; 
		} 
		if (!salasana.contains("KAKKA")){ 
			System.out.println("salasanasta puuttuu sana KAKKA"); 
			testi = false;  
		}
		if (salasana.contains(" ")){ 
			System.out.println("salasanassa ei saa olla v�lily�nti�"); 
			testi = false; 
		} 
		if (salasana.contains("!")){ 
			System.out.println("salasanassa ei saa olla huutomerkki�"); 
			testi = false;  
		} 
		if (salasana.length() > 50){  
			System.out.println("salasana on yli 50 merkki� pitk�, anna uusi");  
			testi = false; 
		}  
		return testi;  
	}  
	private static boolean salasananNumero(String salasana) {
		if (!salasana.matches(".*\\d+.*")){  
			System.out.println("Salasanasta puuttuu numero");  
			return false; 
		}  
		return true;
	}
	private static boolean salasananPituus(String salasana) {
		if (salasana.length() < 10){
			System.out.println("Salasana on alle 10 merkki� pitk�, anna uusi");  
			return false;  
		}
		return true;
	}
	public static boolean tarkistaMerkit(String salasana){
		ArrayList<String> v��r�tmerkit = new ArrayList<String>();
		v��r�tmerkit.add("p");
		v��r�tmerkit.add("d");
		v��r�tmerkit.add("x");
		for (String merkki : v��r�tmerkit) {
			if (salasana.contains(merkki)){
				System.out.println("Salasanassa kiellettyj� merkkej�");
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