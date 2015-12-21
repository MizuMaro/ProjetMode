package Menu;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

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
		
		fenetre.add(this);
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e) {
				fenetre.repaint();
				
				if(jouer_b){
					fenetre.setContentPane(m.getAffichage());
				}else if(quitter_b){
					System.exit(0);
				}
				
				jouer_b = false;
				quitter_b = false;
				fenetre.repaint();
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(e.getX() > 700 && e.getX() < 900 && e.getY() > 100 && e.getY() < 200){
					jouer_b = true;
					fenetre.repaint();
				}else if(e.getX() > 700 && e.getX() < 975 && e.getY() > 275-70 && e.getY() < 375-70){
					quitter_b = true;
					fenetre.repaint();
				}else{
					jouer_b = false;
					quitter_b = false;
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
