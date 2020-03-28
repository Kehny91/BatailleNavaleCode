package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class Plaisance extends BateauAbs {

	public Plaisance(IJoueur owner, EDirection cap) {
		super(1, false, cap, owner);
	}

}
