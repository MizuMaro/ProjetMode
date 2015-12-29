package Start;


import MVC_free.FreeController;
import MVC_free.FreeModel;
import MVC.Controller;
import MVC.Model;
import MVC.Vue;

/**
 * La classe Lancer est le point d'entree du programme, celle-ci contenant la
 * methode main.
 * 
 * @author Omar
 *
 */
public class Lancer {
	
	public Lancer() {
		
		Model model = new Model();
		Controller control = new Controller(model);
		FreeModel model1 = new FreeModel();
		FreeController control1 = new FreeController(model1);
		
		Vue vue = new Vue(model, control, model1, control1);
		control.addVue(vue);
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Lancer();
	}

}
