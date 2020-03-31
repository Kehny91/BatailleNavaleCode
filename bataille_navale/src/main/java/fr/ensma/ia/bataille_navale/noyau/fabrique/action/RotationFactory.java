package fr.ensma.ia.bataille_navale.noyau.fabrique.action;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.deplacements.Rotation;
import fr.ensma.ia.bataille_navale.noyau.actions.deplacements.Translation;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.observation.IObservateur;

public class RotationFactory extends ActionFactory{

	private Grille grille;
	
	public RotationFactory(IAsker asker, Grille grilleAttaquant) {
		this.asker = asker;
		this.grille = grilleAttaquant;
	}

	@Override
	public IAction createAction() throws ExceptionBadInput, InterruptedException {
		try {
			Rotation out = new Rotation();
			BateauAbs bateauADep = asker.demandeUnBateau("Selectionnez le bateau a deplacer", grille);
			if (!bateauADep.isEnVie())
				throw new ExceptionBadInput();
			out.setBateauADeplacer(bateauADep);
			boolean ok = false;
			Case pivot = null;
			while (!ok) {
				pivot = asker.demandeUneCase("Selectionnez une case pivot", grille);
				try {
				if (pivot.getElementBateauSaufBombe().getBateauAbs() == bateauADep)
					ok = true;
				}catch(ExceptionPasDeBateauIci e) {ok = false;}
			}
			out.setPivot(pivot);
			EDirection cap = bateauADep.getCap();
			IObservateur observeTrigo = new IObservateur() {
				
				@Override
				public void doOnNotification() {
					System.out.println("TRIGO");
					out.setSensTrigo(true);
					
				}
			};
			
			IObservateur observeHoraire = new IObservateur() {
				
				@Override
				public void doOnNotification() {
					System.out.println("HORAIRE");
					out.setSensTrigo(false);
					
				}
			};
			
			asker.getObservableDirection(EDirection.rotate(cap,true)).addObservateur(observeTrigo);
			asker.getObservableDirection(EDirection.rotate(cap,false)).addObservateur(observeHoraire);
			
			asker.afficheConsigne("Definissez la rotation");
			asker.afficheAide("Pour cela, appuier sur la fleche correspondante au cap voulu. Exemple <- pour que votre bateau prenne le cap Ouest. Validez avec la touche Entrée");
			try {
				System.out.println("En attente de validation");
				asker.attendValidation();
				System.out.println("validé");
			} catch (InterruptedException e) {
				asker.getObservableDirection(EDirection.rotate(cap,true)).removeObservateur(observeTrigo);
				asker.getObservableDirection(EDirection.rotate(cap,false)).addObservateur(observeHoraire);
				throw new InterruptedException();
			}
			asker.getObservableDirection(EDirection.rotate(cap,true)).removeObservateur(observeTrigo);
			asker.getObservableDirection(EDirection.rotate(cap,false)).addObservateur(observeHoraire);
			asker.clean();
			if (!bateauADep.peutPivoter(out.getPivot(), out.isSensTrigo()))
				throw new ExceptionBadInput();
			return out;
		} catch (InterruptedException e) {
			asker.clean();
			throw new InterruptedException();
		}
	}
	
	
	
}
