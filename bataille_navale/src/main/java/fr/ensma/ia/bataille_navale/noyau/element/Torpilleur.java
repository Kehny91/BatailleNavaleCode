package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class Torpilleur extends BateauAbs {

	public Torpilleur(IJoueur owner, EDirection cap) {
		super(2, true, cap, owner);
	}

}
