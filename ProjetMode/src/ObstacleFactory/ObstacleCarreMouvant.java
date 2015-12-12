package ObstacleFactory;

import java.awt.Point;

import Element.Constantes;

public class ObstacleCarreMouvant extends Obstacle {

	/**
	 * @param limites_x D�finit les limites du mouvement de l'obstacle sur l'axe x
	 * @param limites_y D�finit les limites du mouvement de l'obstacle sur l'axe y
	 * limites_x[0] et limites_y[0] doivent �tre �gales aux coordon�es de d�part de l'obstacle.
	 */
	private int[] limites_x = {0,0};
	private int[] limites_y = {0,0};
	private boolean descente = true;
	private boolean vers_droite = true;
	
	public ObstacleCarreMouvant(Point c) {
		this.taille = Constantes.TAILLE_OBSTACLES;
		this.c = c;
		this.actif = true;
		this.carre= true;
		this.mouvement = true;
		this.nom = "test";
	}

	/**
	 * La fonction agit sur la coordonn�e y de l'obstacle : c'est elle qu'il
	 * faut appeler pour faire effectuer des translations horizontales �
	 * un obstacle.
	 */
	public void moveY(){
		
		if(this.limites_y[0] != this.limites_y[1]){			
		
			if(super.getC().getY() <= this.getLimites_y()[0] ){
				descente = true;
			}else if(super.getC().getY() >= this.getLimites_y()[1]){
				descente = false;
			}
			
			if(descente){
				super.setC(super.getC().x, super.getC().y+Constantes.VITESSE_OBSTACLES);
				//super.setY(super.getY()+1);
			}else{
				super.setC(super.getC().x, super.getC().y-Constantes.VITESSE_OBSTACLES);
				//super.setY(super.getY()-1);
			}
		
		}
	}
	
	/**
	 * La fonction agit sur la coordonn�e x de l'obstacle : c'est elle qu'il
	 * faut appeler pour faire effectuer des translations horizontales �
	 * un obstacle.
	 */
	public void moveX(){
		
		if(limites_x[0] != limites_x[1]){
			
			if(super.getC().getX() <= this.getLimites_x()[0]){
				vers_droite = true;
			}else if(super.getC().getX() >= this.getLimites_x()[1]){
				vers_droite = false;
			}
			
			if(vers_droite){
				super.setC(super.getC().x+Constantes.VITESSE_OBSTACLES, super.getC().y);
				//super.setX(super.getX()+1);
			}else{
				super.setC(super.getC().x-Constantes.VITESSE_OBSTACLES, super.getC().y);
				//super.setX(super.getX()-1);
			}
		}
	}
	
	
	public int[] getLimites_x() {
		return limites_x;
	}

	public void setLimites_x(int[] limites_x) {
		this.limites_x = limites_x;
	}

	public int[] getLimites_y() {
		return limites_y;
	}

	public void setLimites_y(int[] limites_y) {
		this.limites_y = limites_y;
	}


}
