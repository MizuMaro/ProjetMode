package Courbe;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import Obstacles.Obstacle;
import Obstacles.ObstacleMouvant;

public class Courbe {

	private boolean run = true;

	public Courbe(final double a, final double b, final double c, final Oiseau o, final Affichage affichage, final ArrayList<Obstacle> obstacles, final double posX, final double posY){

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


					//o.setC(50, 450);
					//o.setC2(100, 430);


					//affichage.repaint();

					long premier = System.currentTimeMillis();
					long deuxieme = premier;
					while(premier<deuxieme){
						premier=System.currentTimeMillis();
					}

					int y = 0;
					int x = o.getC().x;
					int x2 = x+50;
					int y2 = 0;
					boolean touch = false;
					boolean sol = false;
					boolean limites = false;

					long timeLancement = System.currentTimeMillis();
					long timeFin = timeLancement + 15000;
					
					double m = (posY-350)/(posX-150);
					double e = 350-m*150;
					
					boolean pointe=false;
					boolean modifC=false;
					double cTest = c;

					while (x <= 800 * 7 && !touch && timeLancement<timeFin) {

						// collision avec le sol
						sol = (o.getC().y >= Constantes.HAUTEUR_SOL - Constantes.TAILLE_OISEAU);
						// sortie de l'ecran
						limites = (o.getC().x > Constantes.TAILLE_ECRAN[0] - Constantes.TAILLE_OISEAU || o.getC().x < 0);

						timeLancement = System.currentTimeMillis();
						// plus ou moins loin
						//x += 4;
						//pour tangente
						//x2 = x+50;
						
						if (x < 150) {
							x += 4;
							x2 = x+50;
							y2 = (int) (m*x2+e);
							y = (int) (m * x + e);
						} else {
							if (!modifC) {
								double neWc = 100;
								double calcul = ((o.getC().getX() / 1000000) * 6 + 0.00030)
										* Math.pow(o.getC().getX(), 2)
										+ (((o.getC().getY() - 350) / 7) - 1.05 - (1.02 * ((o
												.getC().getY() - 350) / 7)))
										* o.getC().getX() + neWc;
								while (Math.abs(calcul - o.getC().getY()) > 3
										&& calcul < 1000) {
									neWc = neWc + 2;
									calcul = ((o.getC().getX() / 1000000) * 6 + 0.00030)
											* Math.pow(o.getC().getX(), 2)
											+ (((o.getC().getY() - 350) / 7) - 1.05 - (1.02 * ((o
													.getC().getY() - 350) / 7)))
											* o.getC().getX() + neWc;
								}
								cTest = neWc;
								modifC = true;
							}
							
							
							x+=4;
							x2=x+50;
							y2 = (int) ((int) (a * 2 * x2 + b) * (x2 - a) + (a
									* Math.pow(x2, 2) + b * x2 + cTest));
							// courbe ax2+bx+c
							y = (int) (a * Math.pow(x, 2) + b * x + cTest);
							if (Math.abs(y2 - y) > 30) {
								while (Math.abs(y2 - y) > 30) {
									if (y2 - y > 0) {
										y2--;
									} else {
										y2++;
									}
								}
							}
						}
						

						// System.out.println(y);
						// nouvelle position de l'oiseau
						o.setC(x, y);
						//o.setC2(x2, y2);

						// utiliser timer
						long now = System.currentTimeMillis();
						long time = now + 10;

						while (now < time) {
							now = System.currentTimeMillis();
						}

						for (Obstacle ob : obstacles) {

							//on bouge les obstacles qui se d�placent
							if(ob instanceof ObstacleMouvant && ob.isActif()){
								((ObstacleMouvant)ob).moveX();
								((ObstacleMouvant)ob).moveY();
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
							o.setC(Constantes.COORDONNEES_ORIGINE.x, Constantes.COORDONNEES_ORIGINE.y);
							o.setC2(o.getC().x+50, o.getC().y);	
						}
						
						affichage.repaint();
					}
				}else{
					timer.cancel();
				}
			}

		};
		
		
		
		//intervalle de temps entre chaque repaint
		timer.scheduleAtFixedRate(timerTask,0,2000);
	}
}
