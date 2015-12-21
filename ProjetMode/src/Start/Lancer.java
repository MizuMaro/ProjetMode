package Start;


import FreeMode.FreeController;
import FreeMode.FreeModel;
import FreeMode.FreeVue;

/**
 * La classe Lancer est le point d'entree du programme, celle-ci contenant la
 * methode main.
 * 
 * @author Omar
 *
 */
public class Lancer {
	
	public Lancer() {
		/*
		Model model = new Model();
		Controller control = new Controller(model);
		Vue vue = new Vue(model, control);
		control.addVue(vue);
		*/
		
		FreeModel model = new FreeModel();
		FreeController control = new FreeController(model);
		FreeVue vue = new FreeVue(model, control);	
		control.addVue(vue);
		
	}

	public static void main(String[] args) throws InterruptedException {
		new Lancer();
	}

}
