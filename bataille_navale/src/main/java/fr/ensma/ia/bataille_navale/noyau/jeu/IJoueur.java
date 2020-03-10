package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.List;

import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur.IGestionEtat;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;;

public interface IJoueur extends IGestionEtat{
	List<IAction> getActionDispo();
	void initialiserGrille(int largeur, int hauteur);
	void ajouteBateau(BateauAbs bateau);
	Grille getGrille();
	List<BateauAbs> getBateaux();
}