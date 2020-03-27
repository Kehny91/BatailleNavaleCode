package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.ContreTorpilleur;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class ContreTorpilleurFactory extends BateauFactory {
private IAsker asker;
	
	public ContreTorpilleurFactory(IAsker asker)
	{
		this.asker = asker;
	}

	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		Case caseArriere = asker.demandeUneCase("Case Arrière",joueur.getGrille());
		Case caseDirection = asker.demandeUneCase("Direction",joueur.getGrille());
		
		checkElementBateau(caseArriere, caseDirection, 3);
		
		ContreTorpilleur out = new ContreTorpilleur(joueur);
		
		buildElementBateau(caseArriere, caseDirection, out, 3);
		joueur.ajouteBateau(out);
		
		out.triggerWholeUpdate();
		return out;
	}
}
