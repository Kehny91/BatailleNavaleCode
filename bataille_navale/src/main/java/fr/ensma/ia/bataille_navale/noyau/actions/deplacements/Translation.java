package fr.ensma.ia.bataille_navale.noyau.actions.deplacements;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;

public class Translation extends DeplacementAbs{
	private int nbDeCase;

	@Override
	public int doAction() throws ExceptionBadInput{
		EDirection sensDeplacement;
		if (nbDeCase>=0)
			sensDeplacement = bateauADeplacer.getCap();
		else
			sensDeplacement = EDirection.reverse(bateauADeplacer.getCap());
		
		Case caseAvantSelonDeplacement = null;
		for (ElementBateau el : bateauADeplacer.getElementsBateau()) {
			try {
				el.getCase().voisin(sensDeplacement).getElementBateauSaufBombe();
			} catch (ExceptionBadInput e) {
				//On est en dehors du plateau
				throw new ExceptionBadInput();
			} catch (ExceptionPasDeBateauIci e) {
				//Pas de bateau, donc c'est la case avant
				caseAvantSelonDeplacement = el.getCase();
			}
		}
		//On a trouvé la case avant, on fait le deplacement
		
		Case caseADeplacer = caseAvantSelonDeplacement;
		ElementBateau elementADeplacer = null;
		
		try {
			elementADeplacer = caseAvantSelonDeplacement.getElementBateauSaufBombe();
		} catch (ExceptionPasDeBateauIci e1) {
			// Inexplicable
			e1.printStackTrace();
		}
		
		for (int caseBateau=0; caseBateau<bateauADeplacer.getNbCase(); caseBateau++)
		{
			for (int i=0;i<nbDeCase;i++) {
				try {
					caseADeplacer.voisin(sensDeplacement).getElementBateauSaufBombe();
					//Il devrait y avoir personne, donc si ça ne throw pas, on retourne une erreur
					throw new ExceptionBadInput();
				} catch (ExceptionPasDeBateauIci e) {
					;//Parfait
				} catch (ExceptionBadInput e) {
					// On sort du plateau
					throw new ExceptionBadInput();
				}
				caseADeplacer.removePlacable(elementADeplacer);
				caseADeplacer.voisin(sensDeplacement).addPlacable(elementADeplacer);
				elementADeplacer.setCase(caseADeplacer.voisin(sensDeplacement));
			}
			
			try {
				caseADeplacer = caseADeplacer.voisin(EDirection.reverse(sensDeplacement));
			} catch (ExceptionBadInput e) {;}//Hors du plateau mais c'ets pas grave
		}
		return 1;
	}

}
