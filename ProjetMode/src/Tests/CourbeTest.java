package Tests;

import org.junit.Test;

import MVC.Controller;
import MVC.Model;
import MVC.Vue;

public class CourbeTest {
//test inutile  ,  je voulais tester le timer
	@Test
	public void testCourbe() throws InterruptedException {

		Model model = new Model();
		Controller control = new Controller(model);
		new Vue(model, control);
		
	}

}
