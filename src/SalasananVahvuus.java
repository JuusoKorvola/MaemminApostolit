import java.util.Scanner;   




public class SalasananVahvuus {   
	private static final Scanner lukija = new Scanner(System.in);    
	public static void main(String[] args) {  
		String salasana = lueSalasana();  
		while (salasananMuuttujat(salasana) == false){  
			salasana = lueSalasana();  
		}  
		tulostaSalasana(salasana);  
	}  
	private static void tulostaSalasana(String salasana) {  
		System.out.println("Salasanasi on: " +salasana);  


	}  
	public static boolean salasananMuuttujat(String salasana ) {  
		boolean testi = true;

		if (salasana.length() < 10){  
			System.out.println("salasana on alle 10 merkkiä pitkä, anna uusi");  
			testi = false;  
		} 
		if (!salasana.matches(".*\\d+.*")){  
			System.out.println("salasanasta puuttuu numero");  
			testi = false; 
		}  
		if (!salasana.matches(".*[äöÄÖ]+.*")){  
			System.out.println("salasanasta puuttuu ääkkönen");  
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
			System.out.println("salasanassa ei saa olla välilyöntiä"); 
			testi = false; 
		} 
		if (salasana.contains("!")){ 
			System.out.println("salasanassa ei saa olla huutomerkkiä"); 
			testi = false;  
		} 
		if (salasana.length() > 50){  
			System.out.println("salasana on yli 50 merkkiä pitkä, anna uusi");  
			testi = false; 
		}  
		return testi;  
	}  
	private static String lueSalasana() {  
		System.out.println("Anna salasana, sen tulee sisältää ainakin 10 merkkiä, isoja ja pieniä kirjaimia, numeroita sekä ääkkösiä. Ei saa olla välilyöntiä eikä huutomerkkiä, salasana ei myöskään saa olla yli 50merkkiä pitkä");  
		String salasana = lukija.nextLine();  
		return salasana;  
	}   


} 