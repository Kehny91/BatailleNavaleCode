package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public abstract class BateauAbs {
	protected int nbCase;
	protected boolean enVie;
	protected boolean peutTirer;
	protected IJoueur owner;
}
