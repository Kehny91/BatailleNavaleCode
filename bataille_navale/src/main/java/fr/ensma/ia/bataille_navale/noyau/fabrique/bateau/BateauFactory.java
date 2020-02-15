package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

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
	
	
	protected EDirection getDirection(Case caseDepart, Case caseCible) throws ExceptionBadInput
	{
		if (caseCible.getX() == caseDepart.getX()) //Nord ou sud ?
		{
			if (caseCible.getY()>caseDepart.getY())
			{
				return EDirection.Nord;
			}
			else if (caseCible.getY()<caseDepart.getY())
			{
				return EDirection.Sud;
			}
			else
				throw new ExceptionBadInput();
		}
		else if (caseCible.getY() == caseDepart.getY()) //Ouest ou Est ?
		{
			if (caseCible.getX()>caseDepart.getX())
			{
				return EDirection.Est;
			}
			else if (caseCible.getX()<caseDepart.getX())
			{
				return EDirection.Ouest;
			}
			else
				throw new ExceptionBadInput();
		}
		else
			throw new ExceptionBadInput();
	}

	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		// TODO Auto-generated method stub
		return null;
	}
}
