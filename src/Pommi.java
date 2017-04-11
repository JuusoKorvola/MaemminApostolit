import java.util.Timer;
import java.util.TimerTask;

public class Pommi {

	public static void main(String[] args) {
		System.out.println("Pommi r‰j‰ht‰‰ 5 sec p‰‰st‰!");
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
	        @Override
	        public void run() { 
	        	System.out.println("EXPLODE!!");
				System.exit(0);
	        }
	    };
		timer.schedule(task, 5 * 1000);
	}

}
