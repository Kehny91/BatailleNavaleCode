package fr.ensma.ia.bataille_navale.noyau.actions.attaques;

import java.util.Collection;
import java.util.Collections;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;

public class AttaqueCroix extends AttaqueAbs{

	@Override
	public Resultat doAction() {
		Resultat out = new Resultat();
		out.setPenalite(0);
		out.setTypeResultat(EResultat.Plouf);
		Resultat resultatUnitaire;
		resultatUnitaire = tirSimpleSurCase(cible, puissance);
		if (resultatUnitaire.getPenalite() > out.getPenalite())
			out.setPenalite(resultatUnitaire.getPenalite());
		if (resultatUnitaire.getTypeResultat()!=EResultat.Plouf)
			out.setTypeResultat(EResultat.Touche);//Moche mais j'ai plus le temps;
		
		for (int dir=0;dir<4;dir++) {
			try {
				cible.voisin(EDirection.values()[dir]);
				resultatUnitaire = tirSimpleSurCase(cible.voisin(EDirection.values()[dir]), puissance);
				if (resultatUnitaire.getPenalite() > out.getPenalite())
					out.setPenalite(resultatUnitaire.getPenalite());
				if (resultatUnitaire.getTypeResultat()!=EResultat.Plouf)
					out.setTypeResultat(EResultat.Touche);//Moche mais j'ai plus le temps;
				
			} catch (ExceptionBadInput e) {;}
		}
		out.setPenalite(out.getPenalite()+3);
		return out;
		
	}

}
