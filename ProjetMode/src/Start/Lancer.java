package Start;

import Controller.Controller;
import Model.Model;
import Vue.Vue;

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
		Vue vue = new Vue(model, control);
		control.addVue(vue);
	}

	public static void main(String[] args) throws InterruptedException {
		new Lancer();
	}

}
