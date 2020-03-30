package fr.ensma.ia.bataille_navale.noyau.actions.attaques;

import java.util.Collection;
import java.util.Collections;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;

public class AttaqueCroix extends AttaqueAbs{

	@Override
	public int doAction() {
		int pena = 0;
		int test;
		test = tirSimpleSurCase(cible, puissance).getPenalite();
		if (test > pena)
			pena = test;
		for (int dir=0;dir<4;dir++) {
			try {
				cible.voisin(EDirection.values()[dir]);
				test = tirSimpleSurCase(cible.voisin(EDirection.values()[dir]), puissance).getPenalite();
				if (test>pena)
					pena = test;
			} catch (ExceptionBadInput e) {;}
		}
		return 3 + pena;
		
	}

}
