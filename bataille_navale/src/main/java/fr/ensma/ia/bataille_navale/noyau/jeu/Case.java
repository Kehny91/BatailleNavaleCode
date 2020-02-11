package fr.ensma.ia.bataille_navale.noyau.jeu;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.ia.bataille_navale.noyau.element.IPlacable;

public class Case {
	private int x,y;
	private List<IPlacable> placables;
	
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
	
	public Case(int x, int y)
	{
		this.x=x;
		this.y=y;
		this.placables = new ArrayList<IPlacable>();
	}
}