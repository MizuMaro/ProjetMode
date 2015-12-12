package ObstacleFactory;

import java.awt.Point;

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
