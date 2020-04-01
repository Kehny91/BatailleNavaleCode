package fr.ensma.ia.bataille_navale.noyau.fabrique.action;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.autre.Soin;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;

public class SoinFactory extends ActionFactory{
	IAsker asker;
	Grille grilleAttaquant;
	
	public SoinFactory(IAsker asker, Grille grilleAttaquant) {
		this.asker = asker;
		this.grilleAttaquant = grilleAttaquant;
	}

	@Override
	public IAction createAction() throws ExceptionBadInput, InterruptedException {
		Soin out = new Soin();
		boolean ok = false;
		BateauAbs bateau = null;
		while (!ok) {
			bateau = asker.demandeUnBateau("Quel bateau soigner ?", grilleAttaquant);
			if (bateau.isEnVie()) {
				ok = true;
			}
			asker.clean();
		}
		out.setBateauASoigner(bateau);
		return out;
	}

}
