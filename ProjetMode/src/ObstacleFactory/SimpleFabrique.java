package ObstacleFactory;

import java.awt.Point;

public class SimpleFabrique {
	public Obstacle creerObstacle(TypeObstacle type,Point c){
		Obstacle o = null;/*
		switch(type){
			case ROND:o = new ObstacleRond(c);
			case CARRE:o= new ObstacleCarré(c);
			case RONDMOUVEMENT:o = new ObstacleRondMouvant(c);
			case CARREMOUVEMENT:o = new ObstacleCarréMouvant(c);
 		}
 		*/
		if(type == TypeObstacle.ROND){
			o= new ObstacleRond(c);
			return o;
		}else if(type == TypeObstacle.CARRE){
			o= new ObstacleCarré(c);
			return o;
		}else if(type == TypeObstacle.CARREMOUVEMENT){
			o= new ObstacleCarréMouvant(c);
			return o;
		}else if(type == TypeObstacle.RONDMOUVEMENT){
			o= new ObstacleRondMouvant(c);
			return o;
		}
		return o;
	}
}
