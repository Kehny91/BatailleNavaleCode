package fr.ensma.ia.bataille_navale.noyau.element;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.EResultat;
import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public abstract class BateauAbs {
	protected int nbCase;
	protected boolean peutTirer;
	protected IJoueur owner;
	protected List<ElementBateau> elementsBateau;
	protected EDirection cap;
	
	public BateauAbs(int nbCase, boolean peutTirer, EDirection cap, IJoueur owner) {
		super();
		this.nbCase = nbCase;
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
			if (!isEnVie())
				e.getCase().setEnnemyHint(EResultat.Coule);
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
		for (ElementBateau el : elementsBateau) {
			if (el.getEtatCourant()!=el.getEtatDetruit())
				return true;
		}
		return false;
	}
	
	public void soigner() {
		for (ElementBateau el : elementsBateau) {
			if (el.getEtatCourant()!=el.getEtatDetruit()) {
				try {
					el.getEtatCourant().estAttaque(-1);
					el.setNiveauDef(el.getNiveauDef()+1);
				} catch (ExceptionBadState e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Hypothese, la resistance est le nb de case encore en vie
	 */
	public int getResistance() {
		int out = 0;
		for (ElementBateau el : elementsBateau) {
			if (el.getEtatCourant()!=el.getEtatDetruit())
				out ++;
		}
		return out;
	}
	

	public boolean isPeutTirer() {
		return peutTirer && getPuissance()>0;
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

	public int getPuissance() {
		int out = 0;
		for (ElementBateau el : elementsBateau) {
			if (el.getEtatCourant()==el.getEtatIntact())
				out++;
		}
		return out;
	}
	
	public boolean peutAvancer() {
		for (ElementBateau el : elementsBateau) {
			Case next = null;
			try {
				next = el.getCase().voisin(cap);
			} catch (ExceptionBadInput e) {
				//On est sorti du tableau
				System.out.println("Sortie");
				return false;
			}
			try {
				if (next.getElementBateauSaufBombe().getBateauAbs() != this) {
					System.out.println("Collision");
					return false; //On est tombé sur un autre bateau
				}
			} catch (ExceptionPasDeBateauIci e) {
				;//Parfait
			}
		}
		return true;
	}
	
	public boolean peutReculer() {
		for (ElementBateau el : elementsBateau) {
			Case next = null;
			try {
				next = el.getCase().voisin(EDirection.reverse(cap));
			} catch (ExceptionBadInput e) {
				//On est sorti du tableau
				System.out.println("Sortie");
				return false;
			}
			try {
				if (next.getElementBateauSaufBombe().getBateauAbs() != this) {
					System.out.println("Collision");
					return false; //On est tombé sur un autre bateau
				}
			} catch (ExceptionPasDeBateauIci e) {
				;//Parfait
			}
		}
		return true;
	}
	
	public boolean peutPivoter(Case pivot, boolean sensTrigo) {
		for (ElementBateau el : elementsBateau) {
			Case next = null;
			try {
				next = el.getCase().rotate(pivot, sensTrigo);
			} catch (ExceptionBadInput e) {
				//On est sorti du tableau
				System.out.println("Sortie");
				return false;
			}
			try {
				if (next.getElementBateauSaufBombe().getBateauAbs() != this) {
					System.out.println("Collision");
					return false; //On est tombé sur un autre bateau
				}
			} catch (ExceptionPasDeBateauIci e) {
				;//Parfait
			}
		}
		return true;
	}

	public void setCap(EDirection cap) {
		this.cap  = cap;
	}
	
	
	
}
