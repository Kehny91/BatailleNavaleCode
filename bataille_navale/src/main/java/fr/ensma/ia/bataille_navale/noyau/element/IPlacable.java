package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.jeu.Case;

public interface IPlacable {
	public void setCase(Case pos);
	public Case getCase();
}
