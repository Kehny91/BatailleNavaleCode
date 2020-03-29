package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;

public class Touche extends AbsEtat {

	public Touche(ElementBateau eBateau) {
		super(eBateau);
	}
	
	@Override
	public void estAttaque(int puiss) throws ExceptionBadState {
		if (eBateau.getNiveauDef()-puiss<=0){
			eBateau.setEtatCourant(eBateau.getEtatDetruit());
		}
	}
}
