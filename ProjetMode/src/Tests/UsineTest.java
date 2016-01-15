
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import ObstacleFactory.Obstacle;
import ObstacleFactory.TypeObstacle;
import ObstacleFactory.Usine;

public class UsineTest {

	// On teste le bon fonctionnement de l'usine à Obstacles qui génère les bons types.
	
	@Test
	public void testUsine() {
		Usine usine = new Usine ();
		Obstacle o=null;
		Obstacle ob=null;
		Obstacle obs=null;
		Obstacle obst=null;
		
		o=usine.formerObstacle(TypeObstacle.ROND, new Point (500,500));
		assertFalse(o.isCarre());
		assertFalse(o.isMouvement());
		
		ob=usine.formerObstacle(TypeObstacle.CARRE, new Point (400,400));
		assertTrue(ob.isCarre());
		assertFalse(ob.isMouvement());
		
		obs=usine.formerObstacle(TypeObstacle.RONDMOUVEMENT, new Point (300,300));
		assertFalse(obs.isCarre());
		assertTrue(obs.isMouvement());
		
		obst=usine.formerObstacle(TypeObstacle.CARREMOUVEMENT, new Point (200,200));
		assertTrue(obst.isCarre());
		assertTrue(obst.isMouvement());
	}

}
