package FreeMode;

import java.util.ArrayList;

import ObstacleFactory.Obstacle;

public class FreeController {
	FreeModel m ;
	FreeVue v ;
	
	public FreeController(FreeModel m) {
		this.m=m;
	}
	public void setDrag(boolean b){
		m.setDrag(b);
	}
	
	public void setTrajectoire(boolean b){
		m.getOiseau().setTrajectoire(b);
	}
	
	public void setPositionOiseau(int x , int y){
		m.setPositionOiseau(x,y);
		}
	public void setPositionOiseauC2(int x , int y){
		m.setPositionOiseauC2(x,y);
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.m.setObstacles(obstacles);
	}
	public void repaint(){
		this.m.getAffichage().repaint();
	}
	
	public void addVue(FreeVue v){
		this.v = v;
	}
	
	public void initAffichage(){
		this.m.initAffichage();
	}
	public void SetVol(boolean b){
		this.m.setVol(b);
	}
	public void initObstacles() {
		this.m.initObstacles();
		
	}
	
}