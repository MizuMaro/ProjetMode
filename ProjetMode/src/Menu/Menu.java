package Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Element.Constantes;
import Element.Images;
import MVC.Model;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	
	public boolean jouer_b = false;
	public boolean quitter_b = false;
	
	
	public Menu(final Model m, final JFrame fenetre){
		
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setVisible(true);
		
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
					fenetre.setContentPane(m.getAffichage());
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
		
		g.drawImage(Images.SKY, 0, 0, Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1], null);
		g.drawImage(Images.NAME, 25, 40, 600, 150, null);
		
		if(!jouer_b){
			g.drawImage(Images.JOUER, 700, 100, 200, 100, null);
		}else{
			g.drawImage(Images.JOUER1, 700, 100, 200, 100, null);
		}
		
		if(!quitter_b){
			g.drawImage(Images.QUITTER, 700, 200, 275, 100, null);
		}else{
			g.drawImage(Images.QUITTER1, 700, 200, 275, 100, null);
		}
		
		g.drawImage(Images.GIF, 70, 425, 150, 150, this);
		
	}
	


}
