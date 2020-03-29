package fr.ensma.ia.bataille_navale.noyau.actions.attaques;

import fr.ensma.ia.bataille_navale.noyau.jeu.Case;

public abstract class AttaqueAbs implements IAttaque {
	protected Case cible;
	protected int puissance;
	
	//<== HELPER ==>
	protected Resultat tirSimpleSurCase(Case cellule, int puissance) {
		return cellule.onMeTireDessus(puissance);
	}

	public void setCible(Case cible) {
		this.cible = cible;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}	
}
