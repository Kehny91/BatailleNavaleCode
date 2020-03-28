package fr.ensma.ia.bataille_navale.noyau.element;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public abstract class BateauAbs {
	protected int nbCase;
	protected boolean enVie;
	protected boolean peutTirer;
	protected IJoueur owner;
	protected List<ElementBateau> elementsBateau;
	protected EDirection cap;
	
	public BateauAbs(int nbCase, boolean peutTirer, EDirection cap, IJoueur owner) {
		super();
		this.nbCase = nbCase;
		this.enVie = true;
		this.peutTirer = peutTirer;
		this.owner = owner;
		this.elementsBateau = new ArrayList<ElementBateau>();
		this.cap = cap;
	}
	
	public void ajouteElementBateau(ElementBateau e){
		if (this.elementsBateau.size() >= nbCase)
			System.out.println("Can't add more cases");
		else
			elementsBateau.add(e);
	}
	
	public void triggerWholeUpdate()
	{
		for (ElementBateau e : elementsBateau)
		{
			e.etatChanged.notifyObservateurs();
			e.getCase().somethingChanged.notifyObservateurs();
		}
	}

	public int getNbCase() {
		return nbCase;
	}

	public void setNbCase(int nbCase) {
		this.nbCase = nbCase;
	}

	public boolean isEnVie() {
		return enVie;
	}

	public void setEnVie(boolean enVie) {
		this.enVie = enVie;
	}

	public boolean isPeutTirer() {
		return peutTirer;
	}

	public void setPeutTirer(boolean peutTirer) {
		this.peutTirer = peutTirer;
	}

	public IJoueur getOwner() {
		return owner;
	}

	public void setOwner(IJoueur owner) {
		this.owner = owner;
	}

	public List<ElementBateau> getElementsBateau() {
		return elementsBateau;
	}

	public void setElementsBateau(List<ElementBateau> elementsBateau) {
		this.elementsBateau = elementsBateau;
	}

	public EDirection getCap() {
		return cap;
	}
	
	
	
}
