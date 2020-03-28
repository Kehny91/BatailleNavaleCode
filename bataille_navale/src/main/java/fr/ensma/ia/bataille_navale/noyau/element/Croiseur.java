package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class Croiseur extends BateauAbs {

	public Croiseur(IJoueur owner, EDirection cap) {
		super(4, true, cap, owner);
	}

}
