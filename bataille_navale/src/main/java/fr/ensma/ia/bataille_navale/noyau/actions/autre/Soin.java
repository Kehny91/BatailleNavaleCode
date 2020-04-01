package fr.ensma.ia.bataille_navale.noyau.actions.autre;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;

public class Soin implements IAction{
	private BateauAbs bateauASoigner;

	public BateauAbs getBateauASoigner() {
		return bateauASoigner;
	}

	public void setBateauASoigner(BateauAbs bateauASoigner) {
		this.bateauASoigner = bateauASoigner;
	}

	@Override
	public Resultat doAction() throws ExceptionBadInput {
		bateauASoigner.soigner();
		bateauASoigner.triggerWholeUpdate();
		bateauASoigner.getOwner().useSoin();
		return new Resultat();
	}
	
}
