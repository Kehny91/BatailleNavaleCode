package fr.ensma.ia.bataille_navale.noyau.element;

import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.automates.automateElementBateau.*;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;


/*
 * Element Bateau peut permettre aux autres composants d'observer si son etatChange
 */
public class ElementBateau implements IGestionEtat, IPlacable{
	private int niveauDef0;
	private int niveauDef;
	private IEtat etatDetruit,etatIntact,etatTouche;
	private IEtat etatCourant;
	private IEtat lastEtat;
	private BateauAbs bateauAbs;
	private Case caseImOn;
	private boolean head;
	private boolean hidden;
	
	public GenericObservable etatChanged;
	
	
	
	//Se rajoute aux elements bateau du bateau en question
	public ElementBateau(int niveauDef0, BateauAbs bateauAbs, Case caseImOn) {
		this.niveauDef0 = niveauDef0;
		this.niveauDef = niveauDef0;
		this.bateauAbs = bateauAbs;
		this.caseImOn = caseImOn;
		
		this.etatDetruit = new Detruit(this);
		this.etatIntact = new Intact(this);
		this.etatTouche = new Touche(this);
		
		this.etatCourant = this.etatIntact;
		this.lastEtat = this.etatIntact;
		
		etatChanged = new GenericObservable();
		bateauAbs.ajouteElementBateau(this);
		head = false;
		hidden = false;
		//System.out.println("Nouvel element en "+ caseImOn.getX() +" "+caseImOn.getY());
	}
	
	public void setHead(boolean head) {
		this.head = head;
	}
	
	public boolean isHead() {
		return head;
	}
	
	
	public int getNiveauDef() {
		return niveauDef;
	}
	
	public int getNiveauDef0() {
		return niveauDef0;
	}

	public void setNiveauDef(int niveauDef) {
		this.niveauDef = niveauDef;
	}
	
	public BateauAbs getBateauAbs()
	{
		return bateauAbs;
	}

	@Override
	public IEtat getEtatDetruit() {
		return etatDetruit;
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
	public IEtat getEtatCourant() {
		return etatCourant;
	}


	@Override
	public void setCase(Case pos) {
		caseImOn = pos;
	}


	@Override
	public Case getCase() {
		return caseImOn;
	}
	
	public void handleAttaque(int puissance) {
		try {
			etatCourant.estAttaque(puissance);
			niveauDef = Math.max(0,niveauDef - puissance);
		} catch (ExceptionBadState e) {
			e.printStackTrace();
		}
	}

	public boolean isHidden() {
		return hidden;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}
