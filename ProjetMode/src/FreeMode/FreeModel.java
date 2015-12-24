package FreeMode;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import Element.Constantes;
import Element.Oiseau;
import ObstacleFactory.Obstacle;
import ObstacleFactory.TypeObstacle;
import ObstacleFactory.Usine;

public class FreeModel extends Observable {

	private Oiseau oiseau = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));
	private boolean drag = false;
	private boolean ajout = false;
	private Point encours = new Point();
	
	private Usine usine = new Usine();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private HashMap<Point, Point> trajectoires = new HashMap<Point,Point>();

	private int cptObstacles = -1;
	private FreeAffichage affichage;

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
		affichage = new FreeAffichage(this);

		setChanged();
		notifyObservers();
	}

	public void initObstacles() {
		// initialisation du modele avec un obstacle
		modObstacle(0,0);
		setChanged();
		notifyObservers();
	}


	public void modObstacle(int x, int y){
		
		// suppression d'un obstacle
		for(Obstacle o : this.obstacles){
			
			// condition a verifier
			if(x > o.getC().x && x < o.getC().x + Constantes.TAILLE_OBSTACLES &&
					y > o.getC().y + Constantes.TAILLE_OBSTACLES/2 && y < o.getC().y + (Constantes.TAILLE_OBSTACLES/2)*3){
				obstacles.remove(o);
				this.remCptObstacles();
				return;
			}
		}

		// ajout d'un obstacle
		
		if(FreeVue.carre){		
			obstacles.add(usine.formerObstacle(TypeObstacle.CARRE,new Point(x-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES)));
			addCptObstacles();
		}else if(FreeVue.rond){		
			obstacles.add(usine.formerObstacle(TypeObstacle.ROND,new Point(x-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES)));
			addCptObstacles();

		}else if(FreeVue.carre_bouge && !ajout){
			ajout = true;
			encours = new Point(x-8,y-30);			

		}else if(FreeVue.carre_bouge && ajout){

			// test si les 2 points sont les memes
			if(encours.x+8 == x && encours.y+30 == y){
				obstacles.add(usine.formerObstacle(TypeObstacle.CARRE,new Point(x-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES)));
				addCptObstacles();
				
			}else{
				trajectoires.put(encours,new Point(x-8,y-30));
				
				Obstacle ob = usine.formerObstacle(TypeObstacle.CARREMOUVEMENT, new Point(x-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES));
				
				// X1 < X2 && Y1 < Y2 = cool
				if(encours.x > x && encours.y > y){
					ob.setLimites_x(new int[]{x-Constantes.TAILLE_OBSTACLES/2, encours.x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{y-Constantes.TAILLE_OBSTACLES/2, encours.y-Constantes.TAILLE_OBSTACLES/2});
					
					// X1 > X2 && Y1 < Y2
				}else if(encours.x > x && encours.y < y){
					ob.setLimites_x(new int[]{x-Constantes.TAILLE_OBSTACLES/2, encours.x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{encours.y-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES/2});
					
					// X1 < X2 && Y1 > Y2 
				}else if(encours.x < x && encours.y > y){
					ob.setLimites_x(new int[]{encours.x-Constantes.TAILLE_OBSTACLES/2,x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{y-Constantes.TAILLE_OBSTACLES/2,encours.y-Constantes.TAILLE_OBSTACLES/2});
					
				}else{
					ob.setLimites_x(new int[]{encours.x-Constantes.TAILLE_OBSTACLES/2, x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{encours.y-Constantes.TAILLE_OBSTACLES/2, y-Constantes.TAILLE_OBSTACLES/2});
					
				}
				obstacles.add(ob);
				addCptObstacles();
			}
			ajout = false;


		}else if(FreeVue.rond_bouge && !ajout){		
			ajout = true;
			encours = new Point(x-8,y-30);			

		}else if(FreeVue.rond_bouge && ajout){

			// test si les 2 points sont les memes
			if(encours.x+8 == x && encours.y+30 == y){
				obstacles.add(usine.formerObstacle(TypeObstacle.ROND,new Point(x-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES)));
				addCptObstacles();
				
			}else{
				trajectoires.put(encours,new Point(x-8,y-30));
				
				Obstacle ob = usine.formerObstacle(TypeObstacle.RONDMOUVEMENT, new Point(x-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES));
				
				// X1 < X2 && Y1 < Y2 = cool
				if(encours.x > x && encours.y > y){
					ob.setLimites_x(new int[]{x-Constantes.TAILLE_OBSTACLES/2, encours.x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{y-Constantes.TAILLE_OBSTACLES/2, encours.y-Constantes.TAILLE_OBSTACLES/2});
					
					// X1 > X2 && Y1 < Y2
				}else if(encours.x > x && encours.y < y){
					ob.setLimites_x(new int[]{x-Constantes.TAILLE_OBSTACLES/2, encours.x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{encours.y-Constantes.TAILLE_OBSTACLES/2,y-Constantes.TAILLE_OBSTACLES/2});
					
					// X1 < X2 && Y1 > Y2 
				}else if(encours.x < x && encours.y > y){
					ob.setLimites_x(new int[]{encours.x-Constantes.TAILLE_OBSTACLES/2,x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{y-Constantes.TAILLE_OBSTACLES/2,encours.y-Constantes.TAILLE_OBSTACLES/2});
					
				}else{
					ob.setLimites_x(new int[]{encours.x-Constantes.TAILLE_OBSTACLES/2, x-Constantes.TAILLE_OBSTACLES/2});
					ob.setLimites_y(new int[]{encours.y-Constantes.TAILLE_OBSTACLES/2, y-Constantes.TAILLE_OBSTACLES/2});
					
				}
				obstacles.add(ob);
				addCptObstacles();
			}
			ajout = false;

		}
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

	public FreeAffichage getAffichage() {
		return affichage;
	}

	public void setAffichage(FreeAffichage affichage) {
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
	
	public HashMap<Point, Point> getTrajectoires() {
		return trajectoires;
	}
	
	public int getCptObstacles() {
		return cptObstacles;
	}
	
	public void addCptObstacles(){
		this.cptObstacles++;
		setChanged();
		notifyObservers();
	}
	
	public void remCptObstacles(){
		this.cptObstacles--;
		setChanged();
		notifyObservers();
	}

}
