package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Element.Constantes;
import Obstacles.Obstacle;
import Obstacles.ObstacleMouvant;

public class ObstacleTest {

	@Test
	public void testObstacleNonMouvant() {
		Obstacle o = new Obstacle(new Point(400,600));
		//teste si la taille de l'obstacle correspond à la constante
		assertEquals(o.getTaille(),Constantes.TAILLE_OBSTACLES);
		o.setActif(true);
		//test sur Actif
		assertEquals(o.isActif(),true);
		// teste si le setTaille modifie bien la taille
		o.setTaille(10);
		assertEquals(o.getTaille(),10);
		// test si l'obstacle est carré
		o.setCarre(true);
		assertEquals(o.isCarre(),true);
	}

	@Test 
	public void testObstacleMouvant (){
		ObstacleMouvant om = new ObstacleMouvant(new Point(400,600));
		om.moveX();
		om.moveY();
		om.setLimites_x(new int []{500,600});
		om.getLimites_x();
		om.setLimites_y(new int []{700,900});
		om.getLimites_y();
	}
}
