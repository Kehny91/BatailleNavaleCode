package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;

public abstract class AbsEtat implements IEtat{
	protected ElementBateau eBateau;
	
	protected AbsEtat(ElementBateau eBateau){
		this.eBateau = eBateau;
	}

	@Override
	public void estAttaque(int puiss) throws ExceptionBadState {
		throw new ExceptionBadState("can't attack in this state");
	}
}
