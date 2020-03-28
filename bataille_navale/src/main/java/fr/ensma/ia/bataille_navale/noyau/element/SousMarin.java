package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class SousMarin extends BateauAbs {

	public SousMarin(IJoueur owner, EDirection cap) {
		super(3, true, cap, owner);
	}

}
