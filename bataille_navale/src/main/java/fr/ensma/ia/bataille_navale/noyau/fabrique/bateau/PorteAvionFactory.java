package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.PorteAvion;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class PorteAvionFactory extends BateauFactory {
	private IAsker asker;
	
	public PorteAvionFactory(IAsker asker) {
		this.asker = asker;
	}

	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		Case caseArriere = asker.demandeUneCase("Selectionner la case arrière du porte avion",joueur.getGrille());
		Case caseDirection = asker.demandeUneCase("Selectionner la Direction du bateau",joueur.getGrille());
		asker.clean();
		
		EDirection direction = getDirection(caseArriere, caseDirection);
		
		checkElementBateau(caseArriere, caseDirection, 5);
		
		//Verification additionnelle a cause de la forme du porte avion
		switch (direction)
		{
			case Est:
				if (!(caseArriere.voisin(EDirection.Nord).isEmpty() && 
						caseArriere.voisin(EDirection.Nord).voisin(EDirection.Est).isEmpty() && 
						caseArriere.voisin(EDirection.Nord).voisin(EDirection.Est).voisin(EDirection.Est).isEmpty()))
					throw new ExceptionBadInput();
				break;
			case Nord:
				if (!(caseArriere.voisin(EDirection.Ouest).isEmpty() && 
						caseArriere.voisin(EDirection.Ouest).voisin(EDirection.Nord).isEmpty() && 
						caseArriere.voisin(EDirection.Ouest).voisin(EDirection.Nord).voisin(EDirection.Nord).isEmpty()))
					throw new ExceptionBadInput();
				break;
			case Ouest:
				if (!(caseArriere.voisin(EDirection.Sud).isEmpty() && 
						caseArriere.voisin(EDirection.Sud).voisin(EDirection.Ouest).isEmpty() && 
						caseArriere.voisin(EDirection.Sud).voisin(EDirection.Ouest).voisin(EDirection.Ouest).isEmpty()))
					throw new ExceptionBadInput();
				break;
			case Sud:
				if (!(caseArriere.voisin(EDirection.Est).isEmpty() && 
						caseArriere.voisin(EDirection.Est).voisin(EDirection.Sud).isEmpty() && 
						caseArriere.voisin(EDirection.Est).voisin(EDirection.Sud).voisin(EDirection.Sud).isEmpty()))
					throw new ExceptionBadInput();
				break;
			default:
				throw new ExceptionBadInput();
		}
		
		//Les verifications ont été faites, on peut construire le bateau
		PorteAvion out = new PorteAvion(joueur, direction);
		
		buildElementBateau(caseArriere, caseDirection, out, 5);
		
		Case placeIci = caseArriere;
		
		//Ajouts additionnels a cause de la forme du porte avion
		switch (direction)
		{
			case Est:
				placeIci = caseArriere.voisin(EDirection.Nord);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Nord).voisin(EDirection.Est);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Nord).voisin(EDirection.Est).voisin(EDirection.Est);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				break;
			case Nord:
				placeIci = caseArriere.voisin(EDirection.Ouest);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Ouest).voisin(EDirection.Nord);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Ouest).voisin(EDirection.Nord).voisin(EDirection.Nord);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				break;
			case Ouest:
				placeIci = caseArriere.voisin(EDirection.Sud);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Sud).voisin(EDirection.Ouest);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Sud).voisin(EDirection.Ouest).voisin(EDirection.Ouest);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				break;
			case Sud:
				placeIci = caseArriere.voisin(EDirection.Est);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Est).voisin(EDirection.Sud);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				placeIci = caseArriere.voisin(EDirection.Est).voisin(EDirection.Sud).voisin(EDirection.Sud);
				placeIci.addPlacable(new ElementBateau(out.getNbCase(), out, placeIci));
				break;
			default:
				break;
		}
		
		joueur.ajouteBateau(out);
		
		out.triggerWholeUpdate();
		return out;
	}
	
	
}
