package ObstacleFactory;

import java.awt.Point;

/**
 * La classe SimpleFabrique créer un obstacle puis lui donne une forme demander en parametre.
 * @author Omar
 * 
 */
public class SimpleFabrique {
	public Obstacle creerObstacle(TypeObstacle type,Point c){
		Obstacle o = null;/*
		switch(type){
			case ROND:o = new ObstacleRond(c);
			case CARRE:o= new ObstacleCarrï¿½(c);
			case RONDMOUVEMENT:o = new ObstacleRondMouvant(c);
			case CARREMOUVEMENT:o = new ObstacleCarrï¿½Mouvant(c);
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
