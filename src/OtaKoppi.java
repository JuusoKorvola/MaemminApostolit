
public class OtaKoppi {

	public static void main(String[] args) {
		String pallo = "kuuma";
		System.out.println("Pallo on: " + pallo);		
		try {			
			heit‰pallo(pallo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void heit‰pallo(String pallo) throws Exception{
		if(pallo.equals("kuuma")){
			System.out.println("OH NO! Pallo on " + pallo);
			throw new Exception("Pid‰ pallosi, se on kuuma");
		}
		System.out.println("Pallo j‰‰htyi matkalla, taidan pit‰‰ t‰m‰n");	
	}

}
