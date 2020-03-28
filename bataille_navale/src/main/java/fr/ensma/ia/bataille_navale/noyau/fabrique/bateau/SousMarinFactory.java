package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.SousMarin;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class SousMarinFactory extends BateauFactory {
private IAsker asker;
	
	public SousMarinFactory(IAsker asker)
	{
		this.asker = asker;
	}

	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		Case caseArriere = asker.demandeUneCase("Selectionner la case arrière du sous marin",joueur.getGrille());
		Case caseDirection = asker.demandeUneCase("Selectionner la direction du bateau",joueur.getGrille());
		
		checkElementBateau(caseArriere, caseDirection, 3);
		
		SousMarin out = new SousMarin(joueur, getDirection(caseArriere, caseDirection));
		
		buildElementBateau(caseArriere, caseDirection, out, 3);
		joueur.ajouteBateau(out);
		
		out.triggerWholeUpdate();
		return out;
	}
	
}
