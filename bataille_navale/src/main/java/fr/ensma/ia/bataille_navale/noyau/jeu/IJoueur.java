package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.List;

import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur.IGestionEtat;;

public interface IJoueur extends IGestionEtat{
	void initialiserGrille();
	List<IAction> getActionDispo();
	void initialiserGrille(int largeur, int hauteur);
}