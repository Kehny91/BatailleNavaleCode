package fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur;

public interface IGestionEtat {
	IEtat getEtatEndormi();
	IEtat getEtatPerdu();
	IEtat getEtatGagne();
	IEtat getEtatAttendAction();
	IEtat getEtatAttendBateauSource();
	IEtat getEtatAttendParametres();
	IEtat getEtatRealisationAction();
	void setEtatCourant(IEtat e);
}
