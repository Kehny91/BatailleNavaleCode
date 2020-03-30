package fr.ensma.ia.bataille_navale.noyau.fabrique.action;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.AttaqueNormale;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;

public class AttaqueSimpleFactory extends ActionFactory {
	
	AttaqueNormale out;
	Grille grilleAttaquant,grilleCible;
	
	public AttaqueSimpleFactory(IAsker asker, Grille grilleAttaquant, Grille grilleCible) {
		this.asker = asker;
		this.grilleAttaquant = grilleAttaquant;
		this.grilleCible = grilleCible;
		out = new AttaqueNormale();
	}

	@Override
	public IAction createAction() throws ExceptionBadInput, InterruptedException {
		BateauAbs tireur = asker.demandeUnBateau("Selectionner le bateau tireur", null); //TODO MY GRILLE
		if (!tireur.isPeutTirer() || tireur.getPuissance()==0) {
			asker.clean();
			throw new ExceptionBadInput();
		}
		out.setPuissance(tireur.getPuissance());
		Case cible = asker.demandeUneCase("Selectionner une case cible", null); //TODO HIS GRILLE
		out.setCible(cible);
		asker.clean();
		return out;
	}

}
