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
	
	
	public boolean hasAShip()
	{
		try {
			cellule.getElementBateau();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
		return true;
	}
	
	public boolean connect(EDirection dir)
	{
		ElementBateau e;
		try {
			e = cellule.getElementBateau();
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
		
		try {
			ElementBateau elementVoisin = voisine.getElementBateau();
			return elementVoisin.getBateauAbs()==e.getBateauAbs();
		} catch (ExceptionPasDeBateauIci ex) {
			return false;
		}
	}
	
	public boolean isVisible()
	{
		ElementBateau e;
		try {
			e = cellule.getElementBateau();
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
			return cellule.getElementBateau().getEtatCourant() == cellule.getElementBateau().getEtatTouche();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
	}
	
	public boolean isCoule()
	{
		try {
			return cellule.getElementBateau().getEtatCourant() == cellule.getElementBateau().getEtatCoule();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
	}
	
	public int getNiveauDef() throws ExceptionPasDeBateauIci
	{
		return cellule.getElementBateau().getNiveauDef();
	}
	
	public int getNiveauDef0() throws ExceptionPasDeBateauIci
	{
		return cellule.getElementBateau().getNiveauDef0();
	}
	
	public boolean isHead() throws ExceptionPasDeBateauIci
	{
		return cellule.getElementBateau().isHead();
	}
	
	public void finDeTour()
	{
		if (nbTourVisible>0)
			nbTourVisible--;
	}


	public EDirection getCap() throws ExceptionPasDeBateauIci {
		return cellule.getElementBateau().getBateauAbs().getCap();
	}
}
