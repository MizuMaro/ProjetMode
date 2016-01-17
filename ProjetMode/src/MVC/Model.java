package MVC;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import ObstacleFactory.Obstacle;
import ObstacleFactory.TypeObstacle;
import ObstacleFactory.Usine;

/**
 * La classe Model est la partie Modele MVC du projet. Celle-ci est la base du contenu MVC 
 * il etends d'observable pour permetre a la vue d'avoir accès aux objets dans le model
 * 
 * @author Omar
 * 
 */
public class Model extends Observable {
	Oiseau oiseau = new Oiseau(new Point(
			Constantes.getInstance().COORDONNEES_ORIGINE));
	public static boolean debug = false;
	public static boolean physique = false;
	boolean drag = false;
	ArrayList<Obstacle> obstacles = new ArrayList<>();

	Affichage affichage;

	/**
	 * methode qui permet d'initialiser le boolean drag.
	 */
	public void setDrag(boolean b) {
		this.drag = b;
		setChanged();
		notifyObservers();
	}

	/**
	 * methode qui permet de recuperer le boolean drag.
	 */
	public boolean getDrag() {
		return this.drag;
	}

	/**
	 * methode qui permet d'initiliser le boolean vol qui permet de savoir si l'oiseau vol ou non.
	 */
	public void setVol(boolean b) {
		this.oiseau.setVole(b);
		setChanged();
		notifyObservers();
	}

	/**
	 * methode qui permet de recuperer le boolean vol.
	 */
	public boolean getVol() {
		return this.oiseau.getVole();
	}

	public void initAffichage() {
		affichage = new Affichage(oiseau, obstacles);
		setChanged();
		notifyObservers();
	}

	/**
	 * methode qui d'initialiser les obstacles.
	 */
	public void initObstacles() {
		Usine usine = new Usine();
		Obstacle ob1 = usine.formerObstacle(TypeObstacle.CARRE,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 100,
						Constantes.getInstance().TAILLE_ECRAN[1] - 170));
		Obstacle ob2 = usine.formerObstacle(TypeObstacle.CARRE,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 120,
						Constantes.getInstance().TAILLE_ECRAN[1] - 270));
		Obstacle ob3 = usine.formerObstacle(TypeObstacle.ROND,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 140,
						Constantes.getInstance().TAILLE_ECRAN[1] - 370));
		Obstacle ob4 = usine.formerObstacle(TypeObstacle.ROND,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 160,
						Constantes.getInstance().TAILLE_ECRAN[1] - 470));

		Obstacle ob5 = usine.formerObstacle(TypeObstacle.CARREMOUVEMENT,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 500,
						Constantes.getInstance().TAILLE_ECRAN[1] - 550));
		ob5.setLimites_y(new int[] {
				Constantes.getInstance().TAILLE_ECRAN[1] - 550,
				Constantes.getInstance().TAILLE_ECRAN[1] - 400 });

		Obstacle ob6 = usine.formerObstacle(TypeObstacle.CARREMOUVEMENT,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 100,
						Constantes.getInstance().TAILLE_ECRAN[1] - 560));
		ob6.setLimites_x(new int[] {
				Constantes.getInstance().TAILLE_ECRAN[0] - 200,
				Constantes.getInstance().TAILLE_ECRAN[0] - 100 });

		Obstacle ob7 = usine.formerObstacle(TypeObstacle.ROND,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 600,
						Constantes.getInstance().TAILLE_ECRAN[1] - 550));

		Obstacle ob8 = usine.formerObstacle(TypeObstacle.RONDMOUVEMENT,
				new Point(Constantes.getInstance().TAILLE_ECRAN[0] - 600,
						Constantes.getInstance().TAILLE_ECRAN[1] - 150));
		ob8.setLimites_x(new int[] {
				Constantes.getInstance().TAILLE_ECRAN[0] - 600,
				Constantes.getInstance().TAILLE_ECRAN[0] - 400 });

		obstacles.add(ob1);
		obstacles.add(ob2);
		obstacles.add(ob3);
		obstacles.add(ob4);
		obstacles.add(ob5);
		obstacles.add(ob6);
		obstacles.add(ob7);
		obstacles.add(ob8);

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

	public void addObstacle(int x, int y) {
		// TODO Auto-generated method stub

	}

	public void reset() {
		oiseau = new Oiseau(new Point(
				Constantes.getInstance().COORDONNEES_ORIGINE));
		oiseau.setC2(Constantes.getInstance().COORDONNEES_ORIGINE.x + 50,
				Constantes.getInstance().COORDONNEES_ORIGINE.y);
		debug = false;
		physique = false;
		drag = false;
		obstacles = new ArrayList<>();

		this.initAffichage();
		this.initObstacles();
	}

	public int getCptObstacles() {
		return this.getListObstacles().size();
	}

}
