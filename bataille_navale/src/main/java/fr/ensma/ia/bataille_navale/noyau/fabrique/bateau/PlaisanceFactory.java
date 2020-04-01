package fr.ensma.ia.bataille_navale.noyau.fabrique.bateau;

import java.util.Random;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ExceptionPasDeBateauIci;
import fr.ensma.ia.bataille_navale.Parametres;
import fr.ensma.ia.bataille_navale.ihm.IAsker;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.element.ElementBateau;
import fr.ensma.ia.bataille_navale.noyau.element.Plaisance;
import fr.ensma.ia.bataille_navale.noyau.element.Torpilleur;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class PlaisanceFactory extends BateauFactory {
	private IAsker asker;
	
	public PlaisanceFactory(IAsker asker)
	{
		this.asker = asker;
	}

	@Override
	public BateauAbs createBateau(IJoueur joueur) throws ExceptionBadInput {
		boolean ok = false;
		Case caseArriere = null;
		Case caseDirection = null;
		while (!ok)
		{
			caseArriere = joueur.getGrille().getRandomCase();
			try {
				caseArriere.getElementBateauSaufBombe();
				ok = false; //On aurait rien du trouver
			} catch (ExceptionPasDeBateauIci e) {
				//PArfait
				ok = true;
			}
		}
		
		ok = false;
		Random r = new Random();
		while (!ok) {
			EDirection dir = EDirection.values()[r.nextInt(4)];
			try {
				caseDirection = caseArriere.voisin(dir);
				ok = true;
			}catch(ExceptionBadInput e) {
				ok = false;
			}
		}
		
		
		
		asker.clean();
		
		checkElementBateau(caseArriere, caseDirection, 1);
		
		Plaisance out = new Plaisance(joueur,getDirection(caseArriere, caseDirection));
		
		//Cas special car nbCases != def
		caseArriere.addPlacable(new ElementBateau(2, out, caseArriere));
		try {
			caseArriere.getElementBateau().setHead(true);
		} catch (ExceptionPasDeBateauIci e) {
			// Bizarre
			e.printStackTrace();
		}
		
		joueur.ajouteBateau(out);
		
		out.triggerWholeUpdate();
		return out;
	}
}
