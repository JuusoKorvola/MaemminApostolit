import java.util.ArrayList;
public class SalasananUnitTest {

	public static void main(String[] args) {
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("kakka");
		lista.add("kakAka滗�1�2�12");
		lista.add("kakAkaKAKKA 漩漩12 11");
		lista.add("kakAkaAKKA11asa");
		lista.add("kakkaKAKKA漩漩111!");
		lista.add("asdskdslakdsakdl鰏ak鰏akd88lsakd鰏kad鋋d鰇s鋎aslkd漩ask鲣ask鯽ds鋎kdlkskaldsakaksd");
		lista.add("kakkaKAKKA漩漩漩漩漩�1111");
		for (String salasana : lista) {
			System.out.println(SalasananVahvuus.salasananMuuttujat(salasana));

		}

	}

}
