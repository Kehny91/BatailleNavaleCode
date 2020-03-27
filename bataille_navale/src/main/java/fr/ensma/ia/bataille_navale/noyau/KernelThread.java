package fr.ensma.ia.bataille_navale.noyau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;

public class KernelThread extends Thread {
	private IJoueur j1,j2;
	private PresenterGrille presGrilleJ1,presGrilleJ2;
	
	public KernelThread() {}
	
	
	public KernelThread(IJoueur j1, PresenterGrille presGrilleJ1,  IJoueur j2, PresenterGrille presGrilleJ2)
	{
		this.j1 = j1;
		this.j2 = j2;
		this.presGrilleJ1 = presGrilleJ1;
		this.presGrilleJ2 = presGrilleJ2;
	}
	
	@Override
	public void run() {
        j1.initialiseBateaux(presGrilleJ1);
	}
}
