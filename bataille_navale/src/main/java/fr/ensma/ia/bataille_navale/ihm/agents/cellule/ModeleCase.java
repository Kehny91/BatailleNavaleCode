package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class ModeleCase {
	private boolean hasAShip;
	private int iniDef, def;
	private boolean hasBeenShotByOwner;
	private int visibleByEnnemyForNTours;
	private IJoueur owner;
	
	public boolean getHasAShip() {
		return hasAShip;
	}
	public void setHasAShip(boolean hasAShip) {
		this.hasAShip = hasAShip;
	}
	public int getIniDef() {
		return iniDef;
	}
	public void setIniDef(int iniDef) {
		this.iniDef = iniDef;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public boolean getHasBeenShotByOwner() {
		return hasBeenShotByOwner;
	}
	public void setHasBeenShotByOwner(boolean hasBeenShotByOwner) {
		this.hasBeenShotByOwner = hasBeenShotByOwner;
	}
	public int getVisibleByEnnemyForNTours() {
		return visibleByEnnemyForNTours;
	}
	public void setVisibleByEnnemyForNTours(int visibleByEnnemyForNTours) {
		this.visibleByEnnemyForNTours = visibleByEnnemyForNTours;
	}
	public IJoueur getOwner() {
		return owner;
	}
	public void setOwner(IJoueur owner) {
		this.owner = owner;
	}
	
	
}
