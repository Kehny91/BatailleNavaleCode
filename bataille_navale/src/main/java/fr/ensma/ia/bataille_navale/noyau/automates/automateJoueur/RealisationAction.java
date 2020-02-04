package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class RealisationAction extends AbsEtat {

	public RealisationAction(IJoueur joueur) {
		super(joueur);
	}
	
	@Override
	public void victoire() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatGagne());
	}
	
	@Override
	public void finDeTour() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatEndormi());
	}
	
	@Override
	public void actionImpossible() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendAction());
	}

}
