package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau.*;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;


/*
 * Element Bateau peut permettre aux autres composants d'observer si son etatChange
 */
public class ElementBateau implements IGestionEtat, IPlacable{
	private int niveauDef;
	private IEtat etatCoule,etatIntact,etatTouche;
	private IEtat etatCourant;
	private IEtat lastEtat;
	private BateauAbs bateauAbs;
	private Case caseImOn;
	
	public GenericObservable etatChanged;
	
	//Se rajoute aux elements bateau du bateau en question
	public ElementBateau(int niveauDef, BateauAbs bateauAbs, Case caseImOn) {
		this.niveauDef = niveauDef;
		this.bateauAbs = bateauAbs;
		this.caseImOn = caseImOn;
		
		this.etatCoule = new Coule(this);
		this.etatIntact = new Intact(this);
		this.etatTouche = new Touche(this);
		
		this.etatCourant = this.etatIntact;
		this.lastEtat = this.etatIntact;
		
		etatChanged = new GenericObservable();
		bateauAbs.ajouteElementBateau(this);
		//System.out.println("Nouvel element en "+ caseImOn.getX() +" "+caseImOn.getY());
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


	@Override
	public void setCase(Case pos) {
		caseImOn = pos;
	}


	@Override
	public Case getCase() {
		return caseImOn;
	}
}
