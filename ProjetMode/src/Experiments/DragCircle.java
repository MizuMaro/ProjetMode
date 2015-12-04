package Experiments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// https://openclassrooms.com/courses/apprenez-a-programmer-en-java/le-drag-n-drop


public class DragCircle extends JFrame {
	

    public static int size = 400;
    public static int r = 10;
    private int x;
    private int y;
    private int cX;
    private int cY;
    private int dX;
    private int dY;

    private MouseHandler mh;

    boolean isCircleClicked = false;

    public static void main(String[] args) {
    	DragCircle c1 = new DragCircle();
    	c1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public DragCircle() {

    	super("Drag circle");

    	cX = r + 100;
    	cY = r + 100;

    	mh = new MouseHandler();
    	addMouseListener(mh);
    	addMouseMotionListener(mh);

    	setSize(size, size);
    	setVisible(true);

    }

    public void paintComponent(Graphics g) {
    	super.paint(g);
    	g.setColor(Color.RED);
    	g.fillOval(cX, cY, r * 2, r * 2);

    }

    private class MouseHandler extends MouseAdapter implements MouseMotionListener {
    	public void mousePressed(MouseEvent me)

    	{

    		if ((cX - me.getX()) * (cX - me.getX()) + (cY - me.getY())
    				* (cY - me.getY()) < r * r) {
    			isCircleClicked = true;
    		}
    	}

    	public void mouseDragged(MouseEvent me) {
    		if (isCircleClicked) {

    			x = me.getX() - dX;
    			y = me.getY() - dY;
    			cX = x + r;
    			cY = y + r;
    			repaint();
    		}
    	}

    	public void mouseReleased(MouseEvent e) {
    		isCircleClicked = false;
    	}

    }

}