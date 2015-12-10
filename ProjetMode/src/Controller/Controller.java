package Controller;

import java.util.ArrayList;

import Model.Model;
import Obstacles.Obstacle;
import Vue.Vue;

public class Controller {
	Model m ;
	Vue v= null;
	public Controller(Model m) {
		this.m=m;
	}
	public void setPositionOiseau(int x , int y){
		m.setPositionOiseau(x,y);
		}
	public void setPositionOiseauC2(int x , int y){
		m.setPositionOiseauC2(x,y);
	}

	public void compteurTouchInc(){
		m.setCompteurTouch(m.getCompteurTouch()+1);
	}
	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.m.setObstacles(obstacles);
	}
	public void repaint(){
		this.m.getAffichage().repaint();
	}
	
	public void addVue(Vue v){
		this.v = v;
	}
	public void initObstacles(){
		this.m.initObstacles();
	}
	public void initAffichage(){
		this.m.initAffichage();
	}
	public void vol(){
		this.m.vol(true);
	}
	public void volFini(){
		this.m.vol(false);
		
	}
	
}
