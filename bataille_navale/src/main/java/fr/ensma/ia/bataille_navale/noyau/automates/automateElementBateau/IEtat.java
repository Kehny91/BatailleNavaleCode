package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;

public interface IEtat {
	void estAttaque(int puiss) throws ExceptionBadState ;
}
