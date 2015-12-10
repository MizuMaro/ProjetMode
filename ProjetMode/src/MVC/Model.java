
package MVC;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import Obstacles.Obstacle;
import Obstacles.ObstacleMouvant;

public class Model extends Observable {
	Oiseau oiseau = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));
	boolean vol = false;

	ArrayList<Obstacle> obstacles = new ArrayList<>();

	private int compteurTouch = 1;

	Affichage affichage;

	public void vol(boolean b) {
		this.vol = b;
		setChanged();
		notifyObservers();
	}
	public boolean getVol(){
		return vol;
	}
	public void initAffichage() {
		affichage = new Affichage(oiseau, obstacles, compteurTouch);
		setChanged();
		notifyObservers();
	}

	public void initObstacles() {

		Obstacle ob1 = new Obstacle(new Point(Constantes.TAILLE_ECRAN[0] - 100, Constantes.TAILLE_ECRAN[1] - 130));
		Obstacle ob2 = new Obstacle(new Point(Constantes.TAILLE_ECRAN[0] - 120, Constantes.TAILLE_ECRAN[1] - 270));
		Obstacle ob3 = new Obstacle(new Point(Constantes.TAILLE_ECRAN[0] - 140, Constantes.TAILLE_ECRAN[1] - 370));
		Obstacle ob4 = new Obstacle(new Point(Constantes.TAILLE_ECRAN[0] - 160, Constantes.TAILLE_ECRAN[1] - 470));
		Obstacle ob5 = new Obstacle(new Point(Constantes.TAILLE_ECRAN[0] - 180, Constantes.TAILLE_ECRAN[1] - 600));
		Obstacle ob6 = new Obstacle(new Point(Constantes.TAILLE_ECRAN[0] - 600, Constantes.TAILLE_ECRAN[1] - 180));
		Obstacle ob7 = new Obstacle(new Point(Constantes.TAILLE_ECRAN[0] - 200, Constantes.TAILLE_ECRAN[1] - 150));

		ObstacleMouvant ob8 = new ObstacleMouvant(
				new Point(Constantes.TAILLE_ECRAN[0] - 400, Constantes.TAILLE_ECRAN[1] - 550));
		ob8.setLimites_x(new int[] { Constantes.TAILLE_ECRAN[0] - 400, Constantes.TAILLE_ECRAN[0] - 400 });
		ob8.setLimites_y(new int[] { Constantes.TAILLE_ECRAN[1] - 550, Constantes.TAILLE_ECRAN[1] - 400 });

		ObstacleMouvant ob9 = new ObstacleMouvant(
				new Point(Constantes.TAILLE_ECRAN[0] - 1000, Constantes.TAILLE_ECRAN[1] / 3));
		ob9.setLimites_x(new int[] { Constantes.TAILLE_ECRAN[0] - 1000, Constantes.TAILLE_ECRAN[0] - 800 });
		ob9.setLimites_y(new int[] { Constantes.TAILLE_ECRAN[1] / 3, Constantes.TAILLE_ECRAN[1] / 3 });
		ob9.setCarre(true);

		obstacles.add(ob1);
		obstacles.add(ob2);
		obstacles.add(ob3);
		obstacles.add(ob4);
		obstacles.add(ob5);
		obstacles.add(ob6);
		obstacles.add(ob7);
		obstacles.add(ob8);
		obstacles.add(ob9);

		setChanged();
		notifyObservers();
	}

	public Oiseau getOiseau() {
		return oiseau;
	}

	public void setOiseau(Oiseau oiseau) {
		this.oiseau = oiseau;
		setChanged();
		notifyObservers();
	}

	public ArrayList<Obstacle> getListObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
		setChanged();
		notifyObservers();
	}

	public int getCompteurTouch() {
		return compteurTouch;
	}

	public void setCompteurTouch(int compteurTouch) {
		this.compteurTouch = compteurTouch;
		setChanged();
		notifyObservers();
	}

	public Affichage getAffichage() {
		return affichage;
	}

	public void setAffichage(Affichage affichage) {
		this.affichage = affichage;
		setChanged();
		notifyObservers();
	}

	public Point getPositionOiseau() {
		return this.oiseau.getC();
	}

	public Point getPositionOiseauC2() {
		return this.oiseau.getC2();
	}

	public void setPositionOiseau(int x, int y) {
		this.oiseau.setC(x, y);
		setChanged();
		notifyObservers();

	}

	public void setPositionOiseauC2(int x, int y) {
		this.oiseau.setC2(x, y);
		setChanged();
		notifyObservers();
	}

}
