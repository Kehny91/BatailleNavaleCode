package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.PorteAvion;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public abstract class BateauFactory {
	public static BateauFactory createFactory(EBateau type, IAsker asker)
	{
		switch (type) {
			case Bombe:
				return new BombeFactory();
			case Contre_Torpilleur:
				return new TorpilleurFactory(asker);
			case Croiseur:
				return new CroiseurFactory(asker);
			case Plaisance:
				return new PlaisanceFactory(asker);
			case Porte_Avion:
				return new PorteAvionFactory(asker);
			case Sous_Marin:
				return new SousMarinFactory(asker);
			case Torpilleur:
				return new TorpilleurFactory(asker);
			default:
				return null;
		}
	}
	
	public abstract BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput;
	
	//=========== helpers ==============
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
	
	protected void checkElementBateau(Case caseDepart, Case caseCible, int size) throws ExceptionBadInput
	{
		EDirection direction = getDirection(caseDepart, caseCible);
		
		Case check = caseDepart;
		for (int i = 0; i < size ; i++){
			if (! check.isEmpty())
				throw new ExceptionBadInput();
			check = check.voisin(direction);
		}
	}
	
	protected void buildElementBateau(Case caseDepart, Case caseCible, BateauAbs bateau,int size) throws ExceptionBadInput
	{
		EDirection direction = getDirection(caseDepart, caseCible);
		
		//Les verifications ont été faites, on peut construire le bateau
		Case placeIci = caseDepart;
		for (int i = 0; i < size ; i++){
			//System.out.println("Je place en " + placeIci.getX() + " y " + placeIci.getY() + " caseN " + placeIci.hashCode());
			placeIci.addPlacable(new ElementBateau(bateau.getNbCase(), bateau, placeIci));
			placeIci = placeIci.voisin(direction);
		}
	}
}
