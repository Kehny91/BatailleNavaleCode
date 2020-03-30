package fr.ensma.ia.bataille_navale.ihm;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.outilsMultithread.MDD;

public interface IAsker {
	public Case demandeUneCase(String string, Grille grille) throws ExceptionBadInput, InterruptedException;
	public BateauAbs demandeUnBateau(String string, Grille grille) throws ExceptionBadInput, InterruptedException;
	public void clean(); //Retire les selections
}
