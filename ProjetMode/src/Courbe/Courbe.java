package Courbe;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import ObstacleFactory.Obstacle;

public class Courbe {

	private boolean run = true;

	public Courbe(final double a, final double b, final double c, final Oiseau o, final Affichage affichage, final ArrayList<Obstacle> obstacles, final int i){

		final Timer timer = new Timer(); 
		TimerTask timerTask = new TimerTask(){	

			public void run()  { 

				if(run){
					run = false;
					if(Constantes.TRAJECTOIRE_UNIQUE){
						o.effacerTrajectoire();
						o.effacerTrajectoireTangeante();
					}

					affichage.repaint();

					long premier = System.currentTimeMillis();
					long deuxieme = premier;
					while(premier<deuxieme){
						premier=System.currentTimeMillis();
					}

					int y = (int) o.getC().y;
					int x = (int) o.getC().x;
					int x2 = x+50;
					int y2 =0;
					boolean touch = false;
					boolean sol = false;
					boolean limites = false;

					long timeLancement = System.currentTimeMillis();
					long timeFin = timeLancement + 15000;
					
					o.setTrajectoire(true);
					o.setDepart(new Point(o.getC().x, o.getC().y));
					o.setVole(true);

					while (x <= 800 * 7 && !touch && timeLancement<timeFin) {

						// collision avec le sol
						sol = (o.getC().y >= Constantes.HAUTEUR_SOL - Constantes.TAILLE_OISEAU);
						// sortie de l'ecran
						limites = (o.getC().x > Constantes.TAILLE_ECRAN[0] - Constantes.TAILLE_OISEAU || o.getC().x < 0);

						timeLancement = System.currentTimeMillis();
						
						// plus ou moins loin
						if (i==1) {
							x += 4;
							x2 = x+50;
						} else {
							x -= 4;
							x2 = x-30;
						}
						
						//pour tangente
						y2 = (int) ((int) (a*2*x2+b)*(x2-a)+(a * Math.pow(x2, 2) + b * x2 + c));
						
						// courbe ax2+bx+c
						y = (int) (a * Math.pow(x, 2) + b * x + c);
						if (Math.abs(y2-y) > 30){
							while(Math.abs(y2-y) > 30){
								if (y2-y>0){
									y2--;
								} else {
									y2++;
								}
							}
						}

						// nouvelle position de l'oiseau
						o.setC(x, y);

						long now = System.currentTimeMillis();
						long time = now + 10;

						while (now < time) {
							now = System.currentTimeMillis();
						}

						for (Obstacle ob : obstacles) {

							//on bouge les obstacles qui se deplacent
							if(ob.isMouvement() && ob.isActif()){
								ob.moveX();
								ob.moveY();
							}

							if(ob.isActif() && affichage.distance(o.getC().getX(), o.getC().getY(), 
									ob.getC().getX(), ob.getC().getY()) <= (Constantes.TAILLE_OISEAU/2 + Constantes.TAILLE_OBSTACLES/2) 
									// collision avec le sol ?
									|| sol 

									//sorti de l'ecran ?
									|| limites){

								// implementer la collision avec les obstacles carres

								affichage.setCollision(true);
								touch = true;
								if(sol==false && limites==false){
									ob.setActif(false);
									o.addToScore(1);
								}
								affichage.repaint();
								now = System.currentTimeMillis();
								time = now + 2000;
								sol=false;
								limites=false;

								while (now < time) {
									now = System.currentTimeMillis();
								}
							}
							if (touch == false)
								o.setC2(x2, y2);
						}

						affichage.setCollision(false);
						if(touch){
							o.setTrajectoire(false);
							o.setC(Constantes.COORDONNEES_ORIGINE.x, Constantes.COORDONNEES_ORIGINE.y);
							o.setC2(o.getC().x+50, o.getC().y);	
						}
						
						affichage.repaint();
					}
					o.setVole(false);
				}else{
					timer.cancel();
				}
			}

		};	
		
		//intervalle de temps entre chaque repaint
		timer.scheduleAtFixedRate(timerTask,0,1000);
	}
	
	
	public Courbe(final double coefDir, final double hauteur, final Oiseau o, final Affichage affichage, final ArrayList<Obstacle> obstacles, final int i){

		final Timer timer = new Timer(); 
		TimerTask timerTask = new TimerTask(){	

			public void run()  { 
				
				if(run){
					run = false;
					if(Constantes.TRAJECTOIRE_UNIQUE){
						o.effacerTrajectoire();
						o.effacerTrajectoireTangeante();
					}

					affichage.repaint();
					o.setVole(true);
					
					affichage.repaint();

					long premier = System.currentTimeMillis();
					long deuxieme = premier;
					while(premier<deuxieme){
						premier=System.currentTimeMillis();
					}

					int y = o.getC().y;
					int x = o.getC().x;
					int x2 = x+50;
					int y2 =0;
					boolean touch = false;
					boolean sol = false;
					boolean limites = false;

					long timeLancement = System.currentTimeMillis();
					long timeFin = timeLancement + 15000;
					
					o.setTrajectoire(true);
					o.setDepart(new Point(o.getC().x, o.getC().y));

					while (x <= 800 * 7 && !touch && timeLancement<timeFin) {

						// collision avec le sol
						sol = (o.getC().y >= Constantes.HAUTEUR_SOL - Constantes.TAILLE_OISEAU);
						// sortie de l'ecran
						limites = (o.getC().x > Constantes.TAILLE_ECRAN[0] - Constantes.TAILLE_OISEAU || o.getC().x < 0);

						timeLancement = System.currentTimeMillis();
						// plus ou moins loin
						if (i==1) {
							x += 4;
							x2 = x+50;
						} else {
							x -= 4;
							x2 = x-30;
						}
						//pour tangente
						y2 = (int) ((coefDir*x2)+hauteur);
						
						// courbe ax2+bx+c
						y =  (int) ((coefDir*x)+hauteur);
						if (Math.abs(y2-y) > 30){
							while(Math.abs(y2-y) > 30){
								if (y2-y>0){
									y2--;
								} else {
									y2++;
								}
							}
						}

						// nouvelle position de l'oiseau
						o.setC(x, y);


						// utiliser timer
						long now = System.currentTimeMillis();
						long time = now + 10;

						while (now < time) {
							now = System.currentTimeMillis();
						}

						for (Obstacle ob : obstacles) {

							//on bouge les obstacles qui se d�placent
							if(ob.isMouvement() && ob.isActif()){
								ob.moveX();
								ob.moveY();
							}

							if(ob.isActif() && affichage.distance(o.getC().getX(), o.getC().getY(), 
									ob.getC().getX(), ob.getC().getY()) <= (Constantes.TAILLE_OISEAU/2 + Constantes.TAILLE_OBSTACLES/2) 
									// collision avec le sol ?
									|| sol 

									//sorti de l'ecran ?
									|| limites){

								// implementer la collision avec les obstacles carres

								affichage.setCollision(true);
								touch = true;
								if(sol==false && limites==false){
									ob.setActif(false);
									o.addToScore(1);
								}
								affichage.repaint();
								now = System.currentTimeMillis();
								time = now + 2000;
								sol=false;
								limites=false;

								while (now < time) {
									now = System.currentTimeMillis();
								}
							}
							
							if (touch == false)
								o.setC2(x2, y2);
						}

						affichage.setCollision(false);
						if(touch){
							o.setTrajectoire(false);
							o.setC(Constantes.COORDONNEES_ORIGINE.x, Constantes.COORDONNEES_ORIGINE.y);
							o.setC2(o.getC().x+50, o.getC().y);	
						}
						
						affichage.repaint();
					}
					o.setVole(false);
				}else{
					timer.cancel();
				}
			}

		};
				
		//intervalle de temps entre chaque repaint
		timer.scheduleAtFixedRate(timerTask,0,2000);
	}
	
}
