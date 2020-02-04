package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

public interface IGestionEtat {
	IEtat getEtatCoule();
	IEtat getEtatIntact();
	IEtat getEtatTouche();
	void setEtatCourant(IEtat e);
}
