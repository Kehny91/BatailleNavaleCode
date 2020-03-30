package fr.ensma.ia.bataille_navale.noyau.fabrique.action;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;

public abstract class ActionFactory {
	protected IAsker asker;
	
	public static ActionFactory createFactory(EAction type, IAsker asker, Grille grilleAttaquant, Grille grilleCible) {
		switch (type) {
		case AttaqueCroix:
			return new AttaqueCroixFactory(asker, grilleAttaquant, grilleCible);
		case AttaqueSimple:
			return new AttaqueSimpleFactory(asker, grilleAttaquant, grilleCible);
		case Explosion:
			return null;
		case Flare:
			return null;
		case Rotation:
			return null;
		case Translation:
			return null;
		case Soins:
			return null;
		default:
			return null;
		}
	}
	
	public abstract IAction createAction() throws ExceptionBadInput, InterruptedException;
}
