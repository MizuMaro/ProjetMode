package Tests;

import org.junit.Test;

import FreeMode.FreeController;
import FreeMode.FreeModel;
import MVC.Controller;
import MVC.Model;
import MVC.Vue;

public class CourbeTest {
//test inutile  ,  je voulais tester le timer
	@Test
	public void testCourbe() throws InterruptedException {

		Model model = new Model();
		Controller control = new Controller(model);
		FreeModel model1 = new FreeModel();
		FreeController control1 = new FreeController(model1);
		new Vue(model, control, model1, control1);
		
	}

}
