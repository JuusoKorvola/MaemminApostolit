import java.util.ArrayList;
public class SalasananUnitTest {

	public static void main(String[] args) {
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("kakka");
		lista.add("kakAkaääö1ä2ö12");
		lista.add("kakAkaKAKKA äöäö12 11");
		lista.add("kakAkaAKKA11asa");
		lista.add("kakkaKAKKAäöäö111!");
		lista.add("asdskdslakdsakdlösakösakd88lsakdöskadäadöksädaslkdäöasköäasköadsädkdlkskaldsakaksd");
		lista.add("kakkaKAKKAäöäöäöäöäöä1111");
		for (String salasana : lista) {
			System.out.println(SalasananVahvuus.salasananMuuttujat(salasana));

		}

	}

}
