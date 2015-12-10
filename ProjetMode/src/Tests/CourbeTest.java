package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.Controller;
import Courbe.Courbe;
import IHM.Affichage;
import Model.Model;
import Vue.Vue;

public class CourbeTest {
//test inutile  ,  je voulais tester le timer
	@Test
	public void testCourbe() throws InterruptedException {

		Model model = new Model();
		Controller control = new Controller(model);
		Vue vue = new Vue(model, control);
		Courbe c = new Courbe(0.00077, -1.05, 500, model.getOiseau(), new Affichage(model.getOiseau(),null,1), null);
		
	}

}
