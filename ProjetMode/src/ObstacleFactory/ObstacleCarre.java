package ObstacleFactory;

import java.awt.Point;
import Element.Constantes;


/**
 * La classe Obstaclecarré est un des obstacles que peut generer la factory 
 * Il etend d'obstacle.
 * @author Omar
 * 
 */
public class ObstacleCarre extends Obstacle {

	public ObstacleCarre(Point c) {
		this.taille = Constantes.getInstance().TAILLE_OBSTACLES;
		this.c = c;
		this.actif = true;
		this.setCarre(true);
		this.mouvement= false;
		this.nom = "carre";
	}

	@Override
	public void setLimites_x(int[] a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLimites_y(int[] a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getLimites_x() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLimites_y() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveX() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveY() {
		// TODO Auto-generated method stub
		
	}

}
