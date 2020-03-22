package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.element.Bombe;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.IPlacable;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class ModeleCase {
	private Case cellule;
	private IJoueur looker;
	private int nbTourVisible;
	
	public ModeleCase(Case cellule, IJoueur looker)
	{
		this.cellule = cellule;
		this.looker = looker;
		nbTourVisible = 0;
	}
	
	public ElementBateau getElementBateau() throws ExceptionPasDeBateauIci
	{
		if (cellule.getPlacables().size()==0)
			throw new ExceptionPasDeBateauIci();
		else {
			for (IPlacable p : cellule.getPlacables())
			{
				if (p.getClass() == ElementBateau.class && ((ElementBateau)p).getBateauAbs().getClass()!=Bombe.class)
				{
					return ((ElementBateau)p);
				}
			}
		}
		throw new ExceptionPasDeBateauIci();
	}
	
	public boolean hasAShip()
	{
		try {
			getElementBateau();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
		return true;
	}
	
	public boolean connect(EDirection dir)
	{
		ElementBateau e;
		try {
			e = getElementBateau();
		} catch (ExceptionPasDeBateauIci ex) {
			return false;
		}
		Case voisine;
		try {
			voisine = e.getCase().voisin(dir);
		}
		catch (ExceptionBadInput ex){ //Pas de cellule dans cette direction
			return false;
		}
		
		if (voisine.getPlacables().size()==0)
			return false;
		else {
			for (IPlacable p : voisine.getPlacables())
			{
				if (p.getClass() == ElementBateau.class && ((ElementBateau)p).getBateauAbs()==e.getBateauAbs())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isVisible()
	{
		ElementBateau e;
		try {
			e = getElementBateau();
		} catch (ExceptionPasDeBateauIci e1) {
			return false;
		}
		if (e.getBateauAbs().getOwner()==looker)
			return true;
		else
			return nbTourVisible>0;
	}
	
	public boolean hasBeenShot()
	{
		try {
			return getElementBateau().getEtatCourant() == getElementBateau().getEtatTouche();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
	}
	
	public boolean isCoule()
	{
		try {
			return getElementBateau().getEtatCourant() == getElementBateau().getEtatCoule();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
	}
	
	public void finDeTour()
	{
		if (nbTourVisible>0)
			nbTourVisible--;
	}
}
