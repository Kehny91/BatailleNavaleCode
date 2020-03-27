package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

public interface IVueCase {
	void clean();
	void blitBateau(int totalDef, int def, boolean connectNorth, boolean connectSouth, boolean connectWest,
			boolean connectEast);
	void blitTouche();
	void blitCoule();
	
	int getCaseWidth();
	int getCaseHeight();
}
