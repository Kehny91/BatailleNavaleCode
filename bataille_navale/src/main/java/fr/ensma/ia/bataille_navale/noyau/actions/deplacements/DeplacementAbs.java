package fr.ensma.ia.bataille_navale.noyau.actions.deplacements;

import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;

public abstract class DeplacementAbs implements IDeplacement {
	protected BateauAbs bateauADeplacer;

	public BateauAbs getBateauADeplacer() {
		return bateauADeplacer;
	}

	public void setBateauADeplacer(BateauAbs bateauADeplacer) {
		this.bateauADeplacer = bateauADeplacer;
	}
	
	
}
