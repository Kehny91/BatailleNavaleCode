package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.List;

import fr.ensma.ia.bataille_navale.noyau.actions.IAction;

public interface IJoueur {
	void initialiserGrille();
	List<IAction> getActionDispo();
}
