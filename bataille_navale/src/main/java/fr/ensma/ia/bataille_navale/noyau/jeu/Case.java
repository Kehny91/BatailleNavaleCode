package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.element.IPlacable;

public class Case {
	private int x,y;
	private List<IPlacable> placables;
	private Grille myGrille;
	
	public int getX(){
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public List<IPlacable> getPlacables()
	{
		return placables;
	}
	
	public Case(int x, int y, Grille myGrille)
	{
		this.x=x;
		this.y=y;
		this.placables = new ArrayList<IPlacable>();
		this.myGrille = myGrille;
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