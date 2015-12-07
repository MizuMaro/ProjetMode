package IHM;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

	public TestTimer(){
		final Timer timer = new Timer(); 
		TimerTask timerTask = new TimerTask(){	
			public void run()  { 
				System.out.println("test");
			}
		};
		//intervalle de temps entre chaque repaint
		timer.scheduleAtFixedRate(timerTask,0,2000);
	}
}
