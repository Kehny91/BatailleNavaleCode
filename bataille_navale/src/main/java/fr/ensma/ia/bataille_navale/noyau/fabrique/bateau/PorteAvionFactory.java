package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;

public class PorteAvionFactory extends BateauFactory {
	private IAsker asker;
	
	public PorteAvionFactory(IAsker asker) {
		this.asker = asker;
	}

	@Override
	public BateauAbs createBateau() {
		Case caseArriere = asker.demandeUneCase();
		Case caseDirection = asker.demandeUneCase();
		
	}
	
	
}
