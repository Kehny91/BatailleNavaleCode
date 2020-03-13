package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class ContreTorpilleur extends BateauAbs {
	public ContreTorpilleur(IJoueur owner) {
		super(3, true, owner);
	}
}
