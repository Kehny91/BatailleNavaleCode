package fr.ensma.ia.bataille_navale.ihm;

import java.util.Scanner;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;

public class ConsoleAsker implements IAsker {

	@Override
	public Case demandeUneCase(String string, Grille grille) throws ExceptionBadInput {
		System.out.println("Donne moi une case (expl A5) pour: " + string);
		Scanner input = new Scanner(System.in);
		String in = input.next();
		in = in.toUpperCase();
		int x = (int) in.charAt(0) - 65;
		int y = Integer.parseInt(in.substring(1)) - 1;
		return grille.getCase(x,y);
	}

	@Override
	public BateauAbs demandeUnBateau() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clean() {
		System.out.println("cleaned");
	}

}
