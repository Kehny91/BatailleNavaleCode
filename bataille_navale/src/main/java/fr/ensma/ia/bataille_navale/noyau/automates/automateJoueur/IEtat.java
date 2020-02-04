package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;

public interface IEtat {
	public void bateauChoisi() throws ExceptionBadState;
	public void actionParametree() throws ExceptionBadState;
	public void actionChoisie() throws ExceptionBadState;
	public void timeOut() throws ExceptionBadState;
	public void victoire() throws ExceptionBadState;
	public void finDeTour() throws ExceptionBadState;
	public void annuler() throws ExceptionBadState;
	public void defaite() throws ExceptionBadState;
	public void aTonTour() throws ExceptionBadState;
	public void actionImpossible() throws ExceptionBadState;
}
