package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.EResultat;
import fr.ensma.ia.bataille_navale.noyau.element.Bombe;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.IPlacable;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class ModeleCase {
	private Case cellule;
	private IJoueur looker;
	private boolean selected;
	private IJoueur owner;
	
	public ModeleCase(Case cellule, IJoueur looker, IJoueur owner)
	{
		this.cellule = cellule;
		this.looker = looker;
		this.owner = owner;
	}
	
	public IJoueur getOwner() {
		return owner;
	}
	
	public EResultat getEnnemyHint() {
		return cellule.getEnnemyHint();
	}
	
	public boolean hasAShip()
	{
		try {
			cellule.getElementBateauSaufBombe();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
		return true;
	}
	
	public boolean hasABombe() {
		try {
			cellule.getElementBombe();
			return true;
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
	}
	
	public ElementBateau getElementBombe() throws ExceptionPasDeBateauIci {
		return cellule.getElementBombe();
	}
	
	public boolean connect(EDirection dir)
	{
		ElementBateau e;
		try {
			e = cellule.getElementBateauSaufBombe();
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
			ElementBateau elementVoisin = voisine.getElementBateauSaufBombe();
			return elementVoisin.getBateauAbs()==e.getBateauAbs();
		} catch (ExceptionPasDeBateauIci ex) {
			return false;
		}
	}
	
	public boolean isVisible() //On affiche en priorité les bateaux. Puis les bombes si elles sont configurées comme visible dans la class Parametres
	{
		ElementBateau e = null;
		try {
			e = cellule.getElementBateauSaufBombe();
		} catch (ExceptionPasDeBateauIci e1) {
			try {
				e = cellule.getElementBombe();
			} catch (ExceptionPasDeBateauIci e2) {
				return false;
			}
		}
		if (e.isHidden())
			return false;
		
		if (e.getBateauAbs().getOwner()==looker)
			return true;
		else
			return cellule.getNbDeTourVisible()>0;
	}
	
	public boolean eclaireeParFlare()
	{
		return cellule.getNbDeTourVisible()>0;
	}
	
	public boolean bateauIsCoule()
	{
		try {
			return !cellule.getElementBateau().getBateauAbs().isEnVie();
		} catch (ExceptionPasDeBateauIci e) {
			return false;
		}
	}
	
	public int getNiveauDef() throws ExceptionPasDeBateauIci
	{
		return cellule.getElementBateauSaufBombe().getNiveauDef();
	}
	
	public int getNiveauDef0() throws ExceptionPasDeBateauIci
	{
		return cellule.getElementBateauSaufBombe().getNiveauDef0();
	}
	
	public boolean isHead() throws ExceptionPasDeBateauIci
	{
		return cellule.getElementBateauSaufBombe().isHead();
	}


	public EDirection getCap() throws ExceptionPasDeBateauIci {
		return cellule.getElementBateauSaufBombe().getBateauAbs().getCap();
	}


	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
