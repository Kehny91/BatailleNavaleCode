package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.Bombe;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.IPlacable;
import fr.ensma.ia.bataille_navale.observation.GenericObservable;

public class Case {
	private int x,y;
	private List<IPlacable> placables;
	private Grille myGrille;
	
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
	
	/*
	 * Ne renvoie pas les bombes
	 */
	public ElementBateau getElementBateau() throws ExceptionPasDeBateauIci
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
	
	public void onMeTireDessus()
	{
		//TODO
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