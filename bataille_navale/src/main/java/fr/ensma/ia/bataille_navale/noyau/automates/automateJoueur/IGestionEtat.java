package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

public interface IGestionEtat {
	IEtat getEtatEndormi();
	IEtat getEtatPerdu();
	IEtat getEtatGagne();
	IEtat getEtatAttendAction();
	IEtat getEtatAttendParametres();
	IEtat getEtatExecution();
	IEtat getEtatCourant();
	void setEtatCourant(IEtat e);
}
