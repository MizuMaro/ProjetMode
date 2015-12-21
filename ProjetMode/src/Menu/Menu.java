package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Element.Constantes;
import Start.Lancer;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	
	private JFrame fenetre;
	private Image name;
	private Image sky;
	private Image jouer;
	private Image quitter;
	private Image jouer1;
	private Image quitter1;
	private Image gif;
	
	public boolean jouer_b = false;
	public boolean quitter_b = false;
	
	
	public Menu(){
		
		try {
			initMenu();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
			
/*
	    JLabel label = new JLabel(gif);
	    label.setPreferredSize(new Dimension(300,300));

	    JFrame f = new JFrame("Animation");
	    f.getContentPane().add(label);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
*/		
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setVisible(true);
		
		this.fenetre = new JFrame(Constantes.TITRE);
		fenetre.setSize(Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1]);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		
		//this.add(label);
		fenetre.add(this);
		
		fenetre.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				fenetre.repaint();
				
				if(quitter_b){
					System.exit(0);
				}
				
				if(jouer_b){
					fenetre.setVisible(false);
					new Lancer();
				}
				
				jouer_b = false;
				quitter_b = false;
				fenetre.repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(e.getX() > 700 && e.getX() < 900 && e.getY() > 100+15 && e.getY() < 200+15){
					jouer_b = true;
					fenetre.repaint();
				}else if(e.getX() > 700 && e.getX() < 975 && e.getY() > 275-45 && e.getY() < 375-45){
					quitter_b = true;
					fenetre.repaint();
				}
				
			}
			
			
		});
		
	}
	
	public void paintComponent(Graphics g){
		
		g.drawImage(sky, 0, 0, Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1], null);
		g.drawImage(name, 25, 40, 600, 150, null);
		
		if(!jouer_b){
			g.drawImage(jouer, 700, 100, 200, 100, null);
		}else{
			g.drawImage(jouer1, 700, 100, 200, 100, null);
		}
		
		if(!quitter_b){
			g.drawImage(quitter, 700, 200, 275, 100, null);
		}else{
			g.drawImage(quitter1, 700, 200, 275, 100, null);
		}
		
		g.drawImage(gif, 70, 425, 150, 150, this);
		
	}
	
	public void initMenu() throws IOException{
		sky = ImageIO.read(new File("img/menu/sky.jpg"));
		name = ImageIO.read(new File("img/menu/name.png"));
		jouer = ImageIO.read(new File("img/menu/Jouer.png"));
		quitter = ImageIO.read(new File("img/menu/Quitter.png"));
		jouer1 = ImageIO.read(new File("img/menu/Jouer1.png"));
		quitter1 = ImageIO.read(new File("img/menu/Quitter1.png"));
		gif = ImageIO.read(new File("img/menu/red.gif"));
	}

}
