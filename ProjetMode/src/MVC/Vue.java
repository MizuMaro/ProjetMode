package MVC;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Element.Constantes;
import Element.Images;
import MVC_free.FreeController;
import MVC_free.FreeModel;
import Menu.Menu;

public class Vue implements Observer {
	private JFrame fenetre;
	protected Model m;
	protected Controller c;

/**
 * La classe Vue est la partie Vue MVC du projet. Celle-ci permet
 * de presenter les evenement sur l'oiseau, les objets etc... qui ont été effectué via le controller 
 * sur le model. Tous les modification effectué sur les objets sont affiché ici
 * @author Omar
 * 
 */
	public Vue(final Model m, final Controller c, final FreeModel model,
			final FreeController controller) {
		// MVC
		this.m = m;
		this.c = c;
		m.addObserver(this);

		this.fenetre = new JFrame(Constantes.getInstance().TITRE);
		fenetre.setIconImage(Images.getInstance().OBSTACLE);
		fenetre.setSize(Constantes.getInstance().TAILLE_ECRAN[0],
				Constantes.getInstance().TAILLE_ECRAN[1]);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);

		JPanel menu = new Menu(m, model, controller, fenetre);

		// initialisation de la position du bec
		c.setPositionOiseauC2(m.getPositionOiseau().x + 50,
				m.getPositionOiseau().y);
		// initialisation des obstacles
		c.initObstacles();
		// initialisation du jPanel Affichage
		c.initAffichage();
		fenetre.setContentPane(menu);

		// fonctions du jalon 1
		// courbesTest();

	}

	@Override
	public void update(Observable o, Object arg) {

	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}

}
