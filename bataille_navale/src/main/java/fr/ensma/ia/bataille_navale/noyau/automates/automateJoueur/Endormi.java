package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class Endormi extends AbsEtat {

	public Endormi(IJoueur joueur) {
		super(joueur);
	}
	
	@Override
	public void aTonTour() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendAction());
	}
	
	@Override
	public void defaite() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatPerdu());
	}

}
