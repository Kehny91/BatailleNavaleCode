package fr.ensma.ia.bataille_navale.noyau.actions.attaques;

import java.util.List;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;

public class Explosion extends AttaqueAbs{


	@Override
	public int doAction() {
		Case hitHere = cible;
		
		tirSimpleSurCase(hitHere, 3);
		
		
		//Les 4 cote direct
		for (EDirection dir : EDirection.values()) {
			try {
				hitHere = cible.voisin(dir);
				tirSimpleSurCase(hitHere, 2);
			} catch (ExceptionBadInput e) {}
		}
		
		//Les 4 angles
		try {
			hitHere = cible.voisin(EDirection.Nord).voisin(EDirection.Ouest);
			tirSimpleSurCase(hitHere, 2);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Nord).voisin(EDirection.Est);
			tirSimpleSurCase(hitHere, 2);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Sud).voisin(EDirection.Ouest);
			tirSimpleSurCase(hitHere, 2);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Sud).voisin(EDirection.Est);
			tirSimpleSurCase(hitHere, 2);
		} catch (ExceptionBadInput e) {}
		
		
		//Exterieur Nord
		try {
			hitHere = cible.voisin(EDirection.Nord).voisin(EDirection.Nord);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Nord).voisin(EDirection.Nord).voisin(EDirection.Est);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Nord).voisin(EDirection.Nord).voisin(EDirection.Est).voisin(EDirection.Est);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Nord).voisin(EDirection.Nord).voisin(EDirection.Ouest);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Nord).voisin(EDirection.Nord).voisin(EDirection.Ouest).voisin(EDirection.Ouest);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		//Exterieur Sud
		try {
			hitHere = cible.voisin(EDirection.Sud).voisin(EDirection.Sud);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Sud).voisin(EDirection.Sud).voisin(EDirection.Est);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Sud).voisin(EDirection.Sud).voisin(EDirection.Est).voisin(EDirection.Est);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Sud).voisin(EDirection.Sud).voisin(EDirection.Ouest);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Sud).voisin(EDirection.Sud).voisin(EDirection.Ouest).voisin(EDirection.Ouest);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		//Exterieur Est
		
		try {
			hitHere = cible.voisin(EDirection.Est).voisin(EDirection.Est);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Est).voisin(EDirection.Est).voisin(EDirection.Nord);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Est).voisin(EDirection.Est).voisin(EDirection.Sud);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		//Exterieur Ouest

		try {
			hitHere = cible.voisin(EDirection.Ouest).voisin(EDirection.Ouest);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Ouest).voisin(EDirection.Ouest).voisin(EDirection.Nord);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		try {
			hitHere = cible.voisin(EDirection.Ouest).voisin(EDirection.Ouest).voisin(EDirection.Sud);
			tirSimpleSurCase(hitHere, 1);
		} catch (ExceptionBadInput e) {}
		
		
		
		
		return 0;
	}
	
}
