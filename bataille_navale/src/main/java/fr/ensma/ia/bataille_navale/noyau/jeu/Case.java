package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.EResultat;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;
import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.Bombe;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.IPlacable;
import fr.ensma.ia.bataille_navale.noyau.element.Plaisance;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;

public class Case {
	private int x,y;
	private List<IPlacable> placables;
	private Grille myGrille;
	private EResultat ennemyHint = EResultat.None;
	
	public GenericObservable somethingChanged;
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void addPlacable(IPlacable pl)
	{
		placables.add(pl);
		somethingChanged.notifyObservateurs();
	}
	
	public void removePlacable(IPlacable pl)
	{
		if (placables.remove(pl))
			somethingChanged.notifyObservateurs();
	}
	
	public ElementBateau getElementBateauSaufBombe() throws ExceptionPasDeBateauIci
	{
		if (placables.size()==0)
			throw new ExceptionPasDeBateauIci();
		else
		{
			for (IPlacable pl : placables)
			{
				if (pl.getClass()==ElementBateau.class && ((ElementBateau)pl).getBateauAbs().getClass()!=Bombe.class)
				{
					return ((ElementBateau)pl);
				}
			}
			throw new ExceptionPasDeBateauIci();
		}	
	}
	
	public ElementBateau getElementBateau() throws ExceptionPasDeBateauIci
	{
		if (placables.size()==0)
			throw new ExceptionPasDeBateauIci();
		else
		{
			for (IPlacable pl : placables)
			{
				if (pl.getClass()==ElementBateau.class)
				{
					return ((ElementBateau)pl);
				}
			}
			throw new ExceptionPasDeBateauIci();
		}	
	}
	
	public EResultat getEnnemyHint() {
		return ennemyHint;
	}
	
	public void setEnnemyHint(EResultat res) {
		ennemyHint = res;
	}
	
	public Resultat onMeTireDessus(int puissance)
	{
		Resultat out = new Resultat();
		out.setPenalite(0);
		ElementBateau elB = null;
		try {
			elB = getElementBateau();
			elB.handleAttaque(puissance);
			if (!elB.getBateauAbs().isEnVie())
			{
				ennemyHint = EResultat.Coule;
				out.setTypeResultat(EResultat.Coule);
				elB.getBateauAbs().triggerWholeUpdate();
				if (elB.getBateauAbs().getClass()==Plaisance.class)
					out.setPenalite(3);
			}
			else if(elB.getEtatCourant()==elB.getEtatDetruit()) {
				ennemyHint = EResultat.Detruit;
				out.setTypeResultat(EResultat.Detruit);
			}
			else {
				ennemyHint = EResultat.Touche;
				out.setTypeResultat(EResultat.Touche);
			}
		} catch (ExceptionPasDeBateauIci e) {
			ennemyHint = EResultat.Plouf;
			out.setTypeResultat(EResultat.Plouf);
		}
		
		this.somethingChanged.notifyObservateurs();
		return out;
	}
	
	
	
	public Case(int x, int y, Grille myGrille)
	{
		this.x=x;
		this.y=y;
		this.placables = new ArrayList<IPlacable>();
		this.myGrille = myGrille;
		somethingChanged = new GenericObservable();
	}
	
	public Grille getGrille()
	{
		return myGrille;
	}
	
	public Case voisin(EDirection direction) throws ExceptionBadInput
	{
		switch (direction)
		{
			case Est:
				return myGrille.getCase(x+1, y);
			case Nord:
					return myGrille.getCase(x, y+1);
			case Ouest:
					return myGrille.getCase(x-1, y);
			case Sud:
					return myGrille.getCase(x, y-1);
			default:
					return null;
		}
	}
	
	public boolean isEmpty()
	{
		return placables.size() == 0;
	}
}