package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class AttendParametres extends AbsEtat {

	public AttendParametres(IJoueur joueur) {
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
	public void actionParametree() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatExecution());
	}
	
	@Override
	public void actionImpossible() throws ExceptionBadState {
		joueur.setEtatCourant(joueur.getEtatAttendAction());
	}
	
	

}
