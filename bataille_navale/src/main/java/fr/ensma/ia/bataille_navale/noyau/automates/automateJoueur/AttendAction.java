package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class AttendAction extends AbsEtat {

	public AttendAction(IJoueur joueur) {
		super(joueur);
	}
	
	@Override
	public void actionChoisie() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendBateauSource());
	}
	
	@Override
	public void timeOut() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatEndormi());
	}
	
	@Override
	public void annuler() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendAction());
	}

}
