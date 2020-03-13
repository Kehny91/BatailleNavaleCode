package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.Croiseur;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class CroiseurFactory extends BateauFactory{

	private IAsker asker;
	
	public CroiseurFactory(IAsker asker)
	{
		this.asker = asker;
	}
	
	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		Case caseArriere = asker.demandeUneCase("Case Arrière",joueur.getGrille());
		Case caseDirection = asker.demandeUneCase("Direction",joueur.getGrille());
		
		checkElementBateau(caseArriere, caseDirection, 4);
		
		Croiseur out = new Croiseur(joueur);
		
		buildElementBateau(caseArriere, caseDirection, out, 4);
		joueur.ajouteBateau(out);
		
		return out;
	}
	
}
