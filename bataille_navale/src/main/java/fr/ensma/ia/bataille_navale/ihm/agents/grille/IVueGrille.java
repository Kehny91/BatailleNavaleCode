package fr.ensma.ia.bataille_navale.ihm.agents.grille;

import fr.ensma.ia.bataille_navale.ihm.agents.cellule.IVueCase;

public interface IVueGrille{
	public void ajouteVueCase(IVueCase vue, int x, int y);
	public int getGrilleWidth();
	public int getGrilleHeight();
}
