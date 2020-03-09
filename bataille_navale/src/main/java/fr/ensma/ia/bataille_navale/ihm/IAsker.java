package fr.ensma.ia.bataille_navale.ihm;

import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;

public interface IAsker {
	public Case demandeUneCase(String string, Grille grille);
	public BateauAbs demandeUnBateau();
}
