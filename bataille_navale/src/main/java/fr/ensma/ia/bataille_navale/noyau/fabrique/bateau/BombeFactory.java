package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import com.sun.javadoc.Parameter;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.Parametres;
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
		ElementBateau elBombe = new ElementBateau(3, out, caseBombe);
		elBombe.setHidden(!Parametres.showBombe);
		caseBombe.addPlacable(elBombe);
		
		joueur.ajouteBateau(out);
		
		out.triggerWholeUpdate();
		return out;
	}

}
