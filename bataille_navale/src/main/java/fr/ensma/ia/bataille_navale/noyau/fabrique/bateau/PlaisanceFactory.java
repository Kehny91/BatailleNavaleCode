package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.Torpilleur;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class PlaisanceFactory extends BateauFactory {
	private IAsker asker;
	
	public PlaisanceFactory(IAsker asker)
	{
		this.asker = asker;
	}

	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		Case caseArriere = asker.demandeUneCase("Selectionner la case arrière du bateau de plaisance",joueur.getGrille());
		Case caseDirection = asker.demandeUneCase("Selectionner la direction du bateau",joueur.getGrille());
		asker.clean();
		
		checkElementBateau(caseArriere, caseDirection, 1);
		
		Torpilleur out = new Torpilleur(joueur,getDirection(caseArriere, caseDirection));
		
		//Cas special car nbCases != def
		caseArriere.addPlacable(new ElementBateau(2, out, caseArriere));
		try {
			caseArriere.getElementBateau().setHead(true);
		} catch (ExceptionPasDeBateauIci e) {
			// Bizarre
			e.printStackTrace();
		}
		
		joueur.ajouteBateau(out);
		
		out.triggerWholeUpdate();
		return out;
	}
}
