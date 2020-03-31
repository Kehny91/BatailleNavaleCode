package fr.ensma.ia.bataille_navale.noyau.fabrique.action;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.deplacements.Translation;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.observation.IObservateur;

public class TranslationFactory extends ActionFactory{
	private Grille grille;
	
	public TranslationFactory(IAsker asker, Grille grilleAttaquant) {
		this.asker = asker;
		this.grille = grilleAttaquant;
	}

	@Override
	public IAction createAction() throws ExceptionBadInput, InterruptedException {
		try {
			Translation out = new Translation();
			BateauAbs bateauADep = asker.demandeUnBateau("Selectionner le bateau a deplacer", grille);
			if (!bateauADep.isEnVie())
				throw new ExceptionBadInput();
			out.setBateauADeplacer(bateauADep);
			out.setNbDeCase(0);
			EDirection cap = bateauADep.getCap();
			IObservateur observeCap = new IObservateur() {
				
				@Override
				public void doOnNotification() {
					System.out.println("AVANCE");
					out.setNbDeCase(out.getNbDeCase()+1);
					
				}
			};
			
			IObservateur observeReverseCap = new IObservateur() {
				
				@Override
				public void doOnNotification() {
					System.out.println("RECULE");
					out.setNbDeCase(out.getNbDeCase()-1);
					
				}
			};
			
			asker.getObservableDirection(cap).addObservateur(observeCap);
			asker.getObservableDirection(EDirection.reverse(cap)).addObservateur(observeReverseCap);
			
			asker.afficheConsigne("Definissez la translation");
			asker.afficheAide("Pour cela, appuier sur les fleches directionnelles de votre clavier. Exemple <- <- <- <- pour faire reculer 4 fois un bateau ayant le cap à l'Est. Validez avec la touche Entrée");
			
			try {
				asker.attendValidation();
			} catch (InterruptedException e) {
				asker.getObservableDirection(cap).removeObservateur(observeCap);
				asker.getObservableDirection(EDirection.reverse(cap)).addObservateur(observeReverseCap);
				asker.clean();
				throw new InterruptedException();
			}
			asker.getObservableDirection(cap).removeObservateur(observeCap);
			asker.getObservableDirection(EDirection.reverse(cap)).addObservateur(observeReverseCap);
			asker.clean();
			out.setNbDeCase(Math.max(-1*bateauADep.getElementsBateau().get(0).getNiveauDef0(),Math.min(bateauADep.getElementsBateau().get(0).getNiveauDef0(), out.getNbDeCase())));
			return out;
		} catch (InterruptedException e) {
			asker.clean();
			throw new InterruptedException();
		}
	}

}
