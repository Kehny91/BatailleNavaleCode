package fr.ensma.ia.bataille_navale.ihm.agents.cellule;

import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;

public interface IVueCase {
	void clean();
	void blitUnknown();
	void blitBateau(int totalDef, int def, boolean connectNorth, boolean connectSouth, boolean connectWest,
			boolean connectEast);
	void blitTouche();
	void blitCoule();
	void blitHead(EDirection cap);
	void blitSelected();
	void blitPlouf();
	void blitDetruit();
}
