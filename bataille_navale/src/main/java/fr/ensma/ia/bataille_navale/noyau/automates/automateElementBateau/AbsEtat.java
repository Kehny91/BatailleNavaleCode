package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;

public abstract class AbsEtat implements IEtat{
	void estAttaque(int puiss) throws ExceptionBadState{
		throw new ExceptionBadState("can't attack in this state");
	}
}
