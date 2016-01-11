package Menu;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Element.Constantes;
import Element.Images;
import Element.Sound;
import MVC.Model;
import MVC_free.FreeController;
import MVC_free.FreeModel;
import MVC_free.FreeVue;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	
	public boolean jouer_b = false;
	public boolean quitter_b = false;
	public boolean free_b = false;
	
	
	public Menu(final Model m, final FreeModel model, FreeController control, final JFrame fenetre){
		
		this.setLayout(null);
		
		fenetre.add(this);
		FreeVue vue = new FreeVue(model, control, fenetre);	
		control.addVue(vue);
		Sound.getInstance().playBackground();
		
		String sep = File.separator;
		JLabel redBird = new JLabel();
		redBird.setIcon(new ImageIcon("img"+sep+"menu"+sep+"red.gif"));
		//redBird.setBounds(70, 425, 150, 150);
		redBird.setBounds(70, 350, 300, 300);
		this.add(redBird);
		
		
		fenetre.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'q'){
					fenetre.setContentPane(Menu.this);
					m.reset();
					model.reset();
				}
			}
			
		});
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e) {
				fenetre.repaint();
				
				if(jouer_b){
					fenetre.setContentPane(m.getAffichage());
					
				}else if(free_b){				
					fenetre.setContentPane(model.getAffichage());
					
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
				if(e.getX() > 700 && e.getX() < 900 && e.getY() > 50 && e.getY() < 150){
					jouer_b = true;
					quitter_b = false;
					free_b = false;
				}else if(e.getX() > 700 && e.getX() < 975 && e.getY() > 250 && e.getY() < 350){
					quitter_b = true;
					jouer_b = false;
					free_b = false;
				}else if(e.getX() > 700 && e.getX() < 1000 && e.getY() > 150 && e.getY() < 250){
					free_b = true;
					quitter_b = false;
					jouer_b = false;
				}else{
					jouer_b = false;
					quitter_b = false;
					free_b = false;
				}
				
				fenetre.repaint();

			}
			
		});
		
	}
	
	public void paintComponent(Graphics g){
		
		g.drawImage(Images.getInstance().SKY, 0, 0, Constantes.getInstance().TAILLE_ECRAN[0], Constantes.getInstance().TAILLE_ECRAN[1], null);
		g.drawImage(Images.getInstance().NAME, 25, 40, 600, 150, null);
		
		if(!jouer_b){
			g.drawImage(Images.getInstance().JOUER, 700, 50, 200, 100, null);
		}else{
			g.drawImage(Images.getInstance().JOUER1, 700, 50, 200, 100, null);
		}
		
		if(!free_b){
			g.drawImage(Images.getInstance().FREE, 700, 150, 300, 100, null);
		}else{
			g.drawImage(Images.getInstance().FREE1, 700, 150, 300, 100, null);
		}
		
		if(!quitter_b){
			g.drawImage(Images.getInstance().QUITTER, 700, 250, 275, 100, null);
		}else{
			g.drawImage(Images.getInstance().QUITTER1, 700, 250, 275, 100, null);
		}
		
		
		g.drawImage(Images.getInstance().GIF, 70, 425, 150, 150, this);
		
	}
	


}
