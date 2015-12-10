package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import Element.Constantes;
import Element.Oiseau;

public class OiseauTest {

	@Test
	public void testOiseauCourant() {
		Oiseau a = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));
		assertEquals(a.getC().x,150);
		assertEquals(a.getC().y,350);
		assertEquals(a.getTaille(),Constantes.TAILLE_OISEAU);
	}
	@Test
	public void testSetOiseau(){
		Oiseau a = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));
		a.setTaille(42);
		a.setC(200, 200);
		assertEquals(a.getTaille(),42);
		assertEquals(a.getC().x,200);
		assertEquals(a.getC().y,200);
	}
	
	
}
