package Element;

import java.awt.Point;

/**
 * La classe ObstacleMouvant, qui herite de la classe Obstacle, permet d'instancier
 * des obstacles qui peuvent se deplacer sur des trajectoires lineaires, sur les axes 
 * x et y.
 * @author Rémy
 *
 */

@SuppressWarnings("serial")
public class ObstacleMouvant extends Obstacle{
	
	/**
	 * @param limites_x Définit les limites du mouvement de l'obstacle sur l'axe x
	 * @param limites_y Définit les limites du mouvement de l'obstacle sur l'axe y
	 * limites_x[0] et limites_y[0] doivent être égales aux coordonées de départ de l'obstacle.
	 */
	private int[] limites_x = {0,0};
	private int[] limites_y = {0,0};
	private boolean descente = true;
	private boolean vers_droite = true;
	
	public ObstacleMouvant(Point c) {
		super(c);
	}

	/**
	 * La fonction agit sur la coordonnée y de l'obstacle : c'est elle qu'il
	 * faut appeler pour faire effectuer des translations horizontales à
	 * un obstacle.
	 */
	public void moveY(){
		
		if(this.limites_y[0] != this.limites_y[1]){			
		
			if(super.c.getY() <= this.getLimites_y()[0] ){
				descente = true;
			}else if(super.c.getY() >= this.getLimites_y()[1]){
				descente = false;
			}
			
			if(descente){
				super.c = new Point((int)super.c.getX(), (int)super.c.getY()+Constantes.VITESSE_OBSTACLES);
				//super.setY(super.getY()+1);
			}else{
				super.c = new Point((int)super.c.getX(), (int)super.c.getY()-Constantes.VITESSE_OBSTACLES);
				//super.setY(super.getY()-1);
			}
		
		}
	}
	
	/**
	 * La fonction agit sur la coordonnée x de l'obstacle : c'est elle qu'il
	 * faut appeler pour faire effectuer des translations horizontales à
	 * un obstacle.
	 */
	public void moveX(){
		
		if(limites_x[0] != limites_x[1]){
			
			if(super.c.getX() <= this.getLimites_x()[0]){
				vers_droite = true;
			}else if(super.c.getX() >= this.getLimites_x()[1]){
				vers_droite = false;
			}
			
			if(vers_droite){
				super.c = new Point((int)super.c.getX()+Constantes.VITESSE_OBSTACLES, (int)super.c.getY());
				//super.setX(super.getX()+1);
			}else{
				super.c = new Point((int)super.c.getX()-Constantes.VITESSE_OBSTACLES, (int)super.c.getY());
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
