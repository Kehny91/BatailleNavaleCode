package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau.IEtat;
import fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau.IGestionEtat;

public class ElementBateau implements IGestionEtat{
	private int niveauDef;

	public int getNiveauDef() {
		return niveauDef;
	}

	public void setNiveauDef(int niveauDef) {
		this.niveauDef = niveauDef;
	}

	@Override
	public IEtat getEtatCoule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEtat getEtatIntact() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEtat getEtatTouche() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEtatCourant(IEtat e) {
		// TODO Auto-generated method stub
		
	}
}
