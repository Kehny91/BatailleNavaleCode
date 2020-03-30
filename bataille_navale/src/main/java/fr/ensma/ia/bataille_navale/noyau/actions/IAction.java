package fr.ensma.ia.bataille_navale.noyau.actions;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;

public interface IAction {
	int doAction() throws ExceptionBadInput; //Fait l'action, et renvoie le nombre de tour que cela va prendre
}
