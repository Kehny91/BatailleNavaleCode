package fr.ensma.ia.bataille_navale.noyau.actions.autre;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class Flare implements IAction{
	private int radius;
	private Case cible;
	private IJoueur tireur;
	
	
	
	public void setRadius(int radius) {
		this.radius = radius;
	}



	public void setCible(Case cible) {
		this.cible = cible;
	}
	
	public void setTireur(IJoueur tireur) {
		this.tireur = tireur;
	}
	



	@Override
	public Resultat doAction() throws ExceptionBadInput {
		Grille grille = cible.getGrille();
		for (int x = cible.getX()-radius +1 ; x<cible.getX()+radius;x++) {
			for (int y = cible.getY()-radius + 1 ; y< cible.getY()+radius; y++) {
				try {
					grille.getCase(x, y).setNbDeTourVisible(4);
					grille.getCase(x, y).somethingChanged.notifyObservateurs();
				}
				catch (ExceptionBadInput e) {
					//Hors du terrain
				}
			}
		}
		Resultat r = new Resultat();
		r.setPenalite(5);
		tireur.useFlare();
		return r;
	}

}
