package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class AbsEtat implements IEtat{
	IJoueur joueur;

	public AbsEtat(IJoueur joueur) {
		this.joueur = joueur;
	}

	@Override
	public void bateauChoisi() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas choisir de bateau dans cet état");
	}

	@Override
	public void actionParametree() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas parametrer l'action dans cet etat");
	}

	@Override
	public void actionChoisie() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas choisir d'action dans cet etat");
	}

	@Override
	public void timeOut() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas timeout dans cet etat");
	}

	@Override
	public void victoire() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas gagner dans cet etat");
	}

	@Override
	public void finDeTour() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas finir le tour dans cet etat");
	}

	@Override
	public void annuler() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas annuler dans cet etat");
	}

	@Override
	public void defaite() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas perdre dans cet etat");
	}

	@Override
	public void aTonTour() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas etre reveillé dans cet etat");
	}

	@Override
	public void actionImpossible() throws ExceptionBadState {
		throw new ExceptionBadState("Je ne peux pas faire cet action dans cet etat");
	}

	

}
