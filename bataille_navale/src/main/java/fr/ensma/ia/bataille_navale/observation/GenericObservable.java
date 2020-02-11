package fr.ensma.ia.bataille_navale.observation;

import java.util.ArrayList;


/*
 * Parce que j'en avais marre de réécrire ces meme lignes 300 fois
 */
public class GenericObservable implements IObservable{
	private ArrayList<IObservateur> observateurs;
	
	public GenericObservable() {
		observateurs = new ArrayList<IObservateur>();
	}

	@Override
	public void notifyObservateurs() {
		for (IObservateur obs : observateurs){
			obs.doOnNotification();
		}
	}

	@Override
	public void addObservateur(IObservateur obs) {
		observateurs.add(obs);
	}

	@Override
	public void removeObservateur(IObservateur obs) {
		observateurs.remove(obs);
	}

}
