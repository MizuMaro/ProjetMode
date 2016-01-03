package ObstacleFactory;

import java.awt.Point;

import Element.Constantes;

public class ObstacleRond extends Obstacle {

	public ObstacleRond(Point c) {
		this.taille = Constantes.getInstance().TAILLE_OBSTACLES;
		this.c = c;
		this.actif = true;
		this.carre = false;
		this.mouvement = false;
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
