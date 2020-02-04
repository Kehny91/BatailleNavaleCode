package fr.ensma.ia.bataille_navale.noyau.element;

import java.util.List;

import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public abstract class BateauAbs {
	protected int nbCase;
	protected boolean enVie;
	protected boolean peutTirer;
	protected IJoueur owner;
	protected List<ElementBateau> elementsBateau;
}
