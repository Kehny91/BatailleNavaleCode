package fr.ensma.ia.bataille_navale.ihm;

import java.util.Scanner;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.noyau.element.BateauAbs;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.EDirection;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.observation.IObservable;

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
	public BateauAbs demandeUnBateau(String string, Grille grille) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clean() {
		System.out.println("cleaned");
	}

	@Override
	public EAction demandeAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IObservable getObservableDirection(EDirection dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IObservable getObservableEnter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attendValidation() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficheConsigne(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficheAide(String s) {
		// TODO Auto-generated method stub
		
	}


}
