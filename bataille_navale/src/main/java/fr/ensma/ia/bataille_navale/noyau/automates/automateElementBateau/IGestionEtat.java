package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

public interface IGestionEtat {
	IEtat getEtatDetruit();
	IEtat getEtatIntact();
	IEtat getEtatTouche();
	void setEtatCourant(IEtat e);
	IEtat getEtatCourant();
}
