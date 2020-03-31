package fr.ensma.ia.bataille_navale.noyau.actions;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;

public interface IAction {
	Resultat doAction() throws ExceptionBadInput; //Fait l'action, et renvoie le nombre de tour que cela va prendre
}
