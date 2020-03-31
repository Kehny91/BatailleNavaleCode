package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class Execution extends AbsEtat {

	public Execution(IJoueur joueur) {
		super(joueur);
	}
	
	@Override
	public void actionExecutee() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendAction());
	}
	
	@Override
	public void actionImpossible() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendAction());
	}

}
