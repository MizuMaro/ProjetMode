package ObstacleFactory;

import java.awt.Point;

public class SimpleFabrique {
	public Obstacle creerObstacle(TypeObstacle type,Point c){
		Obstacle o = null;/*
		switch(type){
			case ROND:o = new ObstacleRond(c);
			case CARRE:o= new ObstacleCarr�(c);
			case RONDMOUVEMENT:o = new ObstacleRondMouvant(c);
			case CARREMOUVEMENT:o = new ObstacleCarr�Mouvant(c);
 		}
 		*/
		if(type == TypeObstacle.ROND){
			o= new ObstacleRond(c);
			return o;
		}else if(type == TypeObstacle.CARRE){
			o= new ObstacleCarre(c);
			return o;
		}else if(type == TypeObstacle.CARREMOUVEMENT){
			o= new ObstacleCarreMouvant(c);
			return o;
		}else if(type == TypeObstacle.RONDMOUVEMENT){
			o= new ObstacleRondMouvant(c);
			return o;
		}
		return o;
	}
}
