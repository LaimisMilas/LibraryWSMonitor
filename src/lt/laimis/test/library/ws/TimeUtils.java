package lt.laimis.test.library.ws;

import java.util.concurrent.TimeUnit;

public class TimeUtils {

	
	public static void timeDelay(int sec){
		
		try {
			
			TimeUnit.SECONDS.sleep(sec);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
