package fr.ensma.ia.bataille_navale.noyau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.TransitionScreen;
import fr.ensma.ia.bataille_navale.ihm.agents.global.PresenterGlobal;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.noyau.actions.IAction;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.EResultat;
import fr.ensma.ia.bataille_navale.noyau.actions.attaques.Resultat;
import fr.ensma.ia.bataille_navale.noyau.automates.ExceptionBadState;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.ActionFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.noyau.jeu.JoueurAbstrait;
import fr.ensma.ia.bataille_navale.outilsMultithread.Synchro;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KernelThread extends Thread {
	private IJoueur[] joueurs;
	private PresenterGlobal[] presGlobals;
	private Scene sceneJ1, sceneJ2;
	private Stage stage;
	private Scene j1Toj2Scene;
	private Scene j2Toj1Scene;
	private KernelThreadKillerThread watchdog;
	
	public KernelThread(IJoueur j1, PresenterGlobal presGlobalJ1, IJoueur j2, PresenterGlobal presGlobalJ2, Scene sceneJ1, Scene sceneJ2, Scene j1Toj2Scene, Scene j2Toj1Scene, Stage primaryStage) {
		this.joueurs = new IJoueur[2];
		this.joueurs[0] = j1;
		this.joueurs[1] = j2;
		
		this.presGlobals = new PresenterGlobal[2];
		this.presGlobals[0] = presGlobalJ1;
		this.presGlobals[1] = presGlobalJ2;
	
		this.sceneJ1 =  sceneJ1;
		this.sceneJ2 =  sceneJ2;
		this.j1Toj2Scene = j1Toj2Scene;
		this.j2Toj1Scene = j2Toj1Scene;
		this.stage = primaryStage;
		watchdog = new KernelThreadKillerThread(this, presGlobalJ1.getPresAction().boutonRetourObs);
	}
	
	@Override
	public void run() {
		for (int i=0;i<2;i++) {
			joueurs[i].initialiseBateaux(presGlobals[i]);
			swapFrom(joueurs[i]);
		}
		
		((JoueurAbstrait)joueurs[0]).setObservateurFailAdverse(((JoueurAbstrait)joueurs[1]).failObs);
		((JoueurAbstrait)joueurs[0]).setObservateurSuccessAdverse(((JoueurAbstrait)joueurs[1]).successObs);
		((JoueurAbstrait)joueurs[1]).setObservateurFailAdverse(((JoueurAbstrait)joueurs[0]).failObs);
		((JoueurAbstrait)joueurs[1]).setObservateurSuccessAdverse(((JoueurAbstrait)joueurs[0]).successObs);
		
        watchdog.start();
        
        //J1 dans l'etat Attend action
        while (true) {
        	for (int j = 0; j<2; j++) {
	        	watchdog.ping();
	        	boolean iHaveHitSomething = false;
	        	boolean ok = false;
	        	try {joueurs[j].getEtatCourant().aTonTour();} catch (ExceptionBadState e1) {e1.printStackTrace();}
	        	while (!ok)
	        	{
	        		presGlobals[j].getPresAction().setAvailable(joueurs[j].getActionDispo());
	        		
		        	try {
		        		EAction action = presGlobals[j].demandeAction();
		        		System.out.println(action.toString());
		        		try {joueurs[j].getEtatCourant().actionChoisie();} catch (ExceptionBadState e1) {e1.printStackTrace();}
		        		if (action == EAction.FinDeTour) {
		        			try {
		        				joueurs[j].getEtatCourant().actionParametree();
		        				joueurs[j].getEtatCourant().actionExecutee();
		        				joueurs[j].getEtatCourant().finDeTour();
							} catch (ExceptionBadState e) {
								e.printStackTrace();
							}
		        			ok = true;
		        		}
		        		else
		        		{
		        			IAction actionToDo = ActionFactory.createFactory(action, presGlobals[j], joueurs[j].getGrille(), lautre(joueurs[j]).getGrille()).createAction();
		        			try {joueurs[j].getEtatCourant().actionParametree();} catch (ExceptionBadState e) {e.printStackTrace();}
		        			Resultat res = actionToDo.doAction();
		        			joueurs[j].setNbTourAttente(res.getPenalite());
		        			if (res.getTypeResultat()!=EResultat.Plouf) {
		        				iHaveHitSomething = true;
		        			}
		        			
		        			try {joueurs[j].getEtatCourant().actionExecutee();} catch (ExceptionBadState e) {e.printStackTrace();}
		        		}
					} catch (ExceptionBadInput e) {
						e.printStackTrace();
						try {joueurs[j].getEtatCourant().actionImpossible();} catch (ExceptionBadState e1) {e1.printStackTrace();}
					} catch (InterruptedException e) {
						if (watchdog.itsTheEnd())
						{
							//TIMEOUT
							ok = true;
							try {joueurs[j].getEtatCourant().timeOut();} catch (ExceptionBadState e1) {e1.printStackTrace();}
						}else {
							//RETOUR
							ok = false;
							try {joueurs[j].getEtatCourant().annuler();} catch (ExceptionBadState e1) {e1.printStackTrace();}
						}
						
					}
	        	}
	        	
	        	joueurs[j].finDeTour(iHaveHitSomething); // Decremente les compteurs
	        	watchdog.ping();
	        	swapFrom(joueurs[j]);
        	}
        }
	}
	
	
	private IJoueur lautre(IJoueur joueur) {
		if (joueur == joueurs[0])
			return joueurs[1];
		else
			return joueurs[0];
	}
        
	private IJoueur swapFrom(IJoueur joueur) {
		if (joueur == joueurs[0]) {
			try {
				swapToJ2();
			} catch (InterruptedException e) {
				//TIMEOUT
			}
			return joueurs[1];
		} else {
			try {
				swapToJ1();
			} catch (InterruptedException e) {
				// TIMEOUT
			}
			return joueurs[0];
		}
	}
	
	
	private void swapToJ1() throws InterruptedException {
		boolean timeout = false;
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				stage.setScene(j2Toj1Scene);
			}
        	});
		
		try {
			((TransitionScreen)j2Toj1Scene.getRoot()).waitUnlock();
		} catch (InterruptedException e) {
			timeout = true;
		}

		
		Platform.runLater(new Runnable(){
	
			@Override
			public void run() {
				stage.setScene(sceneJ1);
				presGlobals[0].getPresGrilleMyBoats().updateAll();
				presGlobals[0].getPresGrilleEnnemy().updateAll();
			}
	    	});
		if (timeout)
			throw new InterruptedException();
		
	}
	
	private void swapToJ2() throws InterruptedException {
		boolean timeout = false;
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				stage.setScene(j1Toj2Scene);
			}
        	});
		try {
		((TransitionScreen)j1Toj2Scene.getRoot()).waitUnlock();
		} catch (InterruptedException e) {
			timeout = true;
		}
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				stage.setScene(sceneJ2);
				presGlobals[1].getPresGrilleMyBoats().updateAll();
				presGlobals[1].getPresGrilleEnnemy().updateAll();
			}
        	});
		
		if (timeout)
			throw new InterruptedException();
	}
}
