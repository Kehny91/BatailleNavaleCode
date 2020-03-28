package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class PorteAvion extends BateauAbs {

	public PorteAvion(IJoueur owner, EDirection cap) {
		super(8, true, cap, owner);
	}

}
