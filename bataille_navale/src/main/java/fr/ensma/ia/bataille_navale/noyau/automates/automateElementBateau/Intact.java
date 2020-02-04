package fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;

public class Intact extends AbsEtat {

	public Intact(ElementBateau eBateau) {
		super(eBateau);
	}
	
	@Override
	public void estAttaque(int puiss) throws ExceptionBadState {
		if (puiss>0)
		{
			if (eBateau.getNiveauDef()-puiss<=0){
				eBateau.setEtatCourant(eBateau.getEtatCoule());
			}
			else {
				eBateau.setEtatCourant(eBateau.getEtatTouche());
			}
			
		}
	}
}
