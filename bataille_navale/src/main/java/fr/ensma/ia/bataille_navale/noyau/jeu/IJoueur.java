package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.List;

import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.automates.automateJoueur.IGestionEtat;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;;

public interface IJoueur extends IGestionEtat{
	List<EAction> getActionDispo();
	void ajouteBateau(BateauAbs bateau);
	Grille getGrille();
	List<BateauAbs> getBateaux();
	void initialiseBateaux(IAsker asker);
	int getNbTourAttente();
	void setNbTourAttente(int nbTourAttente);
	void finDeTour(boolean iHaveHitSomething);
	void useFlare();
	void useSoin();
	int getNbBateauEnVie();
	String getName();
}