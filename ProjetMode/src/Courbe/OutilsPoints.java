package Courbe;

import java.awt.Point;



public class OutilsPoints {
	
	private Point p1;
	private Point p2;
	
	public OutilsPoints(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}

	public double getDistance(Point p1, Point p2) {
		return Math.round(Math.sqrt((p2.getY() - p1.getY())*(p2.getY() - p1.getY()) + (p2.getX() - p1.getX())*(p2.getX() - p1.getX())));
	}
	
	public double getAngle(){
		Point p3 = new Point((int)p2.getX(), (int)p2.getY());
		return Math.cos(getDistance(p2,p1)/getDistance(p2,p3));		
	}
}
