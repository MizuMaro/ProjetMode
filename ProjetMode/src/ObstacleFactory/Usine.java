package ObstacleFactory;

import java.awt.Point;

/**
 * La classe Usine cr�er la fabrique qui permetra de former les objets.
 * @author Omar
 * 
 */
public class Usine {
	private SimpleFabrique sf;
	public Usine() {
		this.sf = new SimpleFabrique();
	}
	public Obstacle formerObstacle(TypeObstacle type,Point c){
		Obstacle o = this.sf.creerObstacle(type, c);
		return o;
	}

}
