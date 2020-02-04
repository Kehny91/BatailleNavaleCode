package fr.ensma.ia.bataille_navale.observation;

public interface IObservable {
	void notifyObservateurs();
	void addObservateur(IObservateur obs);
	void removeObservateur(IObservateur obs);
}
