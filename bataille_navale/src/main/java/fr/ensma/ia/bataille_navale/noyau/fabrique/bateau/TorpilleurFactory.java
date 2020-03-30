package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.Torpilleur;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class TorpilleurFactory extends BateauFactory {
	private IAsker asker;
	
	public TorpilleurFactory(IAsker asker)
	{
		this.asker = asker;
	}

	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		Case caseArriere = null;
		try {
			caseArriere = asker.demandeUneCase("Selectionner la case arrière du torpilleur",joueur.getGrille());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Case caseDirection = null;
		try {
			caseDirection = asker.demandeUneCase("Selectionner la direction du bateau",joueur.getGrille());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		asker.clean();
		
		checkElementBateau(caseArriere, caseDirection, 2);
		
		Torpilleur out = new Torpilleur(joueur, getDirection(caseArriere, caseDirection));
		
		buildElementBateau(caseArriere, caseDirection, out, 2);
		joueur.ajouteBateau(out);
		
		out.triggerWholeUpdate();
		return out;
	}

}
