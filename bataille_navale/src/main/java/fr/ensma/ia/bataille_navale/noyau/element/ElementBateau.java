package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau.*;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;


/*
 * Element Bateau peut permettre aux autres composants d'observer si son etatChange
 */
public class ElementBateau implements IGestionEtat{
	private int niveauDef;
	private IEtat etatCoule,etatIntact,etatTouche;
	private IEtat etatCourant;
	private IEtat lastEtat;
	private BateauAbs bateauAbs;
	
	public GenericObservable etatChanged;
	
	public ElementBateau(int niveauDef, BateauAbs bateauAbs) {
		this.niveauDef = niveauDef;
		this.bateauAbs = bateauAbs;
		this.etatCoule = new Coule(this);
		this.etatIntact = new Intact(this);
		this.etatTouche = new Touche(this);
		
		this.etatCourant = this.etatIntact;
		this.lastEtat = this.etatIntact;
		
		etatChanged = new GenericObservable();
	}
	
	
	public int getNiveauDef() {
		return niveauDef;
	}

	public void setNiveauDef(int niveauDef) {
		this.niveauDef = niveauDef;
	}

	@Override
	public IEtat getEtatCoule() {
		return etatCoule;
	}

	@Override
	public IEtat getEtatIntact() {
		return etatIntact;
	}

	@Override
	public IEtat getEtatTouche() {
		return etatTouche;
	}

	@Override
	public void setEtatCourant(IEtat e) {
		etatCourant = e;
		if (etatCourant != lastEtat)
			etatChanged.notifyObservateurs();
		lastEtat = etatCourant;
	}
}
