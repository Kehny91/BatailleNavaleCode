package fr.ensma.ia.bataille_navale.noyau.jeu;

import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;

public class JoueurHumain extends JoueurAbstrait {

	public JoueurHumain() {
		super();
	}
	
	@Override
	public void initialiserGrille(int largeur, int hauteur) {
		// TODO Auto-generated method stub
		super.initialiserGrille(largeur, hauteur);
		//Demander a l'utilisateur de rentrer les bateaux...
	}

}
