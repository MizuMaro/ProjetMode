package FreeMode;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import ObstacleFactory.Obstacle;
import ObstacleFactory.TypeObstacle;
import ObstacleFactory.Usine;

public class FreeModel extends Observable {
	
	Oiseau oiseau = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));
	public static boolean debug = false;
	boolean drag = false;
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

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
		addObstacle(0,0);
		setChanged();
		notifyObservers();
	}

	
	public void addObstacle(int x, int y){
		
		Usine usine = new Usine();
		
		obstacles.add(usine.formerObstacle(TypeObstacle.CARRE,new Point(x-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES)));
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
