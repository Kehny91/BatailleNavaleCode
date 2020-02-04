package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;

public class Coule extends AbsEtat {

	protected Coule(ElementBateau eBateau) {
		super(eBateau);
	}
	
	@Override
	public void estAttaque(int puiss) throws ExceptionBadState {
		/* I don't care, I'm already dead */
	}
}