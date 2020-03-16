package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.Bombe;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.Torpilleur;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class BombeFactory extends BateauFactory {

	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		
		Bombe out = new Bombe(joueur);
		Case caseBombe = joueur.getGrille().getRandomCase();
		
		//Cas special car nbCases != def
		caseBombe.getPlacables().add(new ElementBateau(3, out, caseBombe));
		
		joueur.ajouteBateau(out);
		
		return out;
	}

}
