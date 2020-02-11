package fr.ensma.ia.bataille_navale.ihm;

import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;

public interface IAsker {
	public Case demandeUneCase();
	public BateauAbs demandeUnBateau();
}
