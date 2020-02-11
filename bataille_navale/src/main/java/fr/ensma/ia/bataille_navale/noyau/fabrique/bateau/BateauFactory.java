package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;

public abstract class BateauFactory {
	public static BateauFactory createFactory(EBateau type, IAsker asker)
	{
		switch (type) {
			case Bombe:
				return null;
			case Contre_Torpilleur:
				return null;
			case Croiseur:
				return null;
			case Plaisance:
				return null;
			case Porte_Avion:
				return null;
			case Sous_Marin:
				return null;
			case Torpilleur:
				return null;
			default:
				return null;
		}
	}
	
	public abstract BateauAbs createBateau();
}
