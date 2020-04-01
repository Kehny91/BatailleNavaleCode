package fr.ensma.ia.bataille_navale.noyau.fabrique.action;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.autre.Flare;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.SousMarin;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;

public class FlareFactory extends ActionFactory{
	IAsker asker;
	Grille grilleCible,grilleAttaquant;

	public FlareFactory(IAsker asker,Grille grilleAttaquant, Grille grilleCible) {
		this.asker = asker;
		this.grilleCible = grilleCible;
		this.grilleAttaquant = grilleAttaquant;
	}

	@Override
	public IAction createAction() throws ExceptionBadInput, InterruptedException {

		Flare out = new Flare();
		boolean ok = false;
		BateauAbs bateau = null;
		while (!ok) {
			bateau = asker.demandeUnBateau("Selectionnez votre sousMarin", grilleAttaquant);
			if (bateau.getClass()==SousMarin.class) {
				ok = true;
				out.setTireur(bateau.getOwner());
			}
			else
				asker.clean();
		}
		
		out.setRadius(bateau.getResistance());
		out.setCible(asker.demandeUneCase("Où lancer le flare", grilleCible));
		asker.clean();
		return out;
		
	}

}
