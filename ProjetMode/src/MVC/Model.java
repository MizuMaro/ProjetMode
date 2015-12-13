
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

public class Model extends Observable {
	Oiseau oiseau = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));
	public static boolean debug = true;
	boolean drag = false;
	ArrayList<Obstacle> obstacles = new ArrayList<>();

	Affichage affichage;

	public void setDrag(boolean b){
		this.drag = b;
		setChanged();
		notifyObservers();
	}
	public boolean getDrag(){
		return this.drag;
	}
	public void setVol(boolean b) {
		this.oiseau.setVole(b);
		setChanged();
		notifyObservers();
	}
	public boolean getVol(){
		return this.oiseau.getVole();
	}
	public void initAffichage() {
		affichage = new Affichage(oiseau, obstacles);
		setChanged();
		notifyObservers();
	}

	public void initObstacles() {
		Usine usine = new Usine();
		Obstacle ob1 = usine.formerObstacle(TypeObstacle.CARRE,
				new Point(Constantes.TAILLE_ECRAN[0] - 100, Constantes.TAILLE_ECRAN[1] - 170));
		Obstacle ob2 = usine.formerObstacle(TypeObstacle.CARRE,
				new Point(Constantes.TAILLE_ECRAN[0] - 120, Constantes.TAILLE_ECRAN[1] - 270));
		Obstacle ob3 = usine.formerObstacle(TypeObstacle.ROND,
				new Point(Constantes.TAILLE_ECRAN[0] - 140, Constantes.TAILLE_ECRAN[1] - 370));
		Obstacle ob4 = usine.formerObstacle(TypeObstacle.ROND,
				new Point(Constantes.TAILLE_ECRAN[0] - 160, Constantes.TAILLE_ECRAN[1] - 470));
		
		Obstacle ob5 = usine.formerObstacle(TypeObstacle.CARREMOUVEMENT,
				new Point(Constantes.TAILLE_ECRAN[0] - 500, Constantes.TAILLE_ECRAN[1] - 550));
		ob5.setLimites_y(new int[] { Constantes.TAILLE_ECRAN[1] - 550, Constantes.TAILLE_ECRAN[1] - 400 });

		Obstacle ob6 = usine.formerObstacle(TypeObstacle.CARREMOUVEMENT,
				new Point(Constantes.TAILLE_ECRAN[0] - 100, Constantes.TAILLE_ECRAN[1] - 560));
		ob6.setLimites_x(new int[] { Constantes.TAILLE_ECRAN[0] - 200, Constantes.TAILLE_ECRAN[0] - 100 });
		
		Obstacle ob7 = usine.formerObstacle(TypeObstacle.ROND,
				new Point(Constantes.TAILLE_ECRAN[0] - 600, Constantes.TAILLE_ECRAN[1] - 550));

		Obstacle ob8 =usine.formerObstacle(TypeObstacle.RONDMOUVEMENT,
				new Point(Constantes.TAILLE_ECRAN[0] - 600, Constantes.TAILLE_ECRAN[1] - 150));
		ob8.setLimites_x(new int[] { Constantes.TAILLE_ECRAN[0] - 600, Constantes.TAILLE_ECRAN[0] - 400 });
		
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

}
