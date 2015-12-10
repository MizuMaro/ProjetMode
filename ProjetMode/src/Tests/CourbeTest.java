package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Courbe.Courbe;
import Element.Ecran;
import IHM.Affichage;

public class CourbeTest {
//test inutile  ,  je voulais tester le timer
	@Test
	public void testCourbe() throws InterruptedException {
		Ecran e = new Ecran();
		Courbe c = new Courbe(0.00077, -1.05, 500, e.getOiseau(), new Affichage(e.getOiseau(),null,1), null);
		
	}

}
