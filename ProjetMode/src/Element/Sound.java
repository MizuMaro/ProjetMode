package Element;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

public class Sound {


	/** Constructeur privé */
	private Sound(){}

	/** Instance unique pré-initialisée */
	private static Sound INSTANCE = new Sound();

	/** Point d'accès pour l'instance unique du singleton */
	public static Sound getInstance(){	
		return INSTANCE;
	}
	
	public void playBackground(){
		play("./sounds/background.wav",true);
	}
	
	public void playWeeee(){
		play("./sounds/yehaa.wav",false);
	}
	
	public void playVictory(){
		play("./sounds/victory.wav",false);
	}


	public void play(String fileName, final boolean loop){
		
		try {
			
			final Clip clip = AudioSystem.getClip();
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileName));
			clip.open(audioIn);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run(){ 
					if (loop) {
						// loop continuously
						clip.loop(Clip.LOOP_CONTINUOUSLY);
					} else {
						clip.start();
					}
				}
			});

		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

}