package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Element.Oiseau;
import ObstacleFactory.Obstacle;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	JButton start;
	public Menu(Oiseau a, ArrayList<Obstacle> listeObstacle) {
		this.start= new JButton("Start");
		this.setLayout(null);
		this.start.setSize(new Dimension(400,60));
		this.start.setLocation(200, 300);
		this.add(start);
		this.setBackground(Color.black);
	}

}
