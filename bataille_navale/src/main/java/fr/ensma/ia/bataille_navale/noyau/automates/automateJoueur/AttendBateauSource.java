package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class AttendBateauSource extends AbsEtat {

	public AttendBateauSource(IJoueur joueur) {
		super(joueur);
	}
	
	@Override
	public void timeOut() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatEndormi());
	}
	
	@Override
	public void annuler() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendAction());
	}
	
	@Override
	public void bateauChoisi() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendParametres());
	}

}
