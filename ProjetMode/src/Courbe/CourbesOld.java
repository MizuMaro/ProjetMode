package Courbe;

import java.util.ArrayList;

import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import ObstacleFactory.Obstacle;

/**
 * Ancienne classe qui servait a lancer les oiseaux.
 * Celle-ci n'est plus utilisee car elle n'utilisait pas de Timer.
 * @author Rémy
 *
 */
public class CourbesOld {

	public void courbe(double a, double b, double c, Oiseau o, Affichage affichage, ArrayList<Obstacle> obstacles) {
		
		if(Constantes.getInstance().TRAJECTOIRE_UNIQUE){
			o.effacerTrajectoire();
			o.effacerTrajectoireTangeante();
		}
			
		affichage.repaint();
		
		o.setC(50, 450);
		o.setC2(100, 430);
		
		affichage.repaint();
		
		long premier = System.currentTimeMillis();
		long deuxieme = premier+1000;
		while(premier<deuxieme){
			premier=System.currentTimeMillis();
		}
		
		int y;
		int x = 50;
		int x2 = 50+50;
		int y2 =0;
		boolean touch = false;
		boolean sol = false;
		boolean limites = false;
		
		long timeLancement = System.currentTimeMillis();
		long timeFin = timeLancement + 15000;
		
		while (x <= 800 * 7 && !touch && timeLancement<timeFin) {
			
			// collision avec le sol
			sol = (o.getC().y >= Constantes.getInstance().HAUTEUR_SOL - Constantes.getInstance().TAILLE_OISEAU);
			// sortie de l'ecran
			limites = (o.getC().x > Constantes.getInstance().TAILLE_ECRAN[0] - Constantes.getInstance().TAILLE_OISEAU || o.getC().x < 0);
			
			timeLancement = System.currentTimeMillis();
			// plus ou moins loin
			x += 4;
			//pour tangente
			x2 = x+50;
			y2 = (int) ((int) (a*2*x2+b)*(x2-a)+(a * Math.pow(x2, 2) + b * x2 + c));
			// courbe ax2+bx+c
			y = (int) (a * Math.pow(x, 2) + b * x + c);
			
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
				
				//on bouge les obstacles qui se déplacent
				if(ob.isMouvement() && ob.isActif()){
					ob.moveX();
					ob.moveY();
				}
				
				if(ob.isActif() && o.distance(o.getC().getX(), o.getC().getY(), 
								   ob.getC().getX(), ob.getC().getY()) <= (Constantes.getInstance().TAILLE_OISEAU/2 + Constantes.getInstance().TAILLE_OBSTACLES/2) 
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
			affichage.repaint();
		}
	}
	
	
	public void courbeCubique(double a, double b, double c, Oiseau o, Affichage affichage, ArrayList<Obstacle> obstacles) {
		
		if(Constantes.getInstance().TRAJECTOIRE_UNIQUE){
			o.effacerTrajectoire();
			o.effacerTrajectoireTangeante();
		}
			
		affichage.repaint();
		o.setC(50, 450);
		o.setC2(100, 430);
		affichage.repaint();
		
		long premier = System.currentTimeMillis();
		long deuxieme = premier+1000;
		
		while(premier<deuxieme){
			premier=System.currentTimeMillis();
		}
		
		int y;
		int x = 50;
		int x2 = 50+50;
		int y2 =0;
		boolean touch = false;
		boolean sol = false;
		boolean limites = false;
		
		long timeLancement = System.currentTimeMillis();
		long timeFin = timeLancement + 15000;
		
		while (x <= 800 * 7 && !touch && timeLancement<timeFin) {
			
			//collision avec le sol
			sol = (o.getC().getY() >= Constantes.getInstance().HAUTEUR_SOL - Constantes.getInstance().TAILLE_OISEAU);
			// sortie de l'ecran
			limites = (o.getC().x > Constantes.getInstance().TAILLE_ECRAN[0] - Constantes.getInstance().TAILLE_OISEAU || o.getC().x < 0);
			
			timeLancement = System.currentTimeMillis();
			// plus ou moins loin
			x += 4;
			//pour tangente
			x2 = x+50;
			y2 = (int) ((int) (a*3*Math.pow(x2,2)+b)*(x2-a)+(a * Math.pow(x2, 3) + b * x2 + c));
			// courbe ax2+bx+c
			y = (int) (a * Math.pow(x, 3) + b * x + c);
			
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

			// test si un obstacle est touche
			for (Obstacle ob : obstacles) {
				
				//on bouge les obstacles qui se déplacent
				if(ob.isMouvement() && ob.isActif()){
					ob.moveX();
					ob.moveY();
				}
				
				if(ob.isActif() && o.distance(o.getC().getX(), o.getC().getY(), 
						   ob.getC().getX(), ob.getC().getY()) <= (Constantes.getInstance().TAILLE_OISEAU/2 + Constantes.getInstance().TAILLE_OBSTACLES/2) 
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
			affichage.repaint();
		}
	}
	
}
