package fr.ensma.ia.bataille_navale.noyau;

import fr.ensma.ia.bataille_navale.ExceptionBadInput;
import fr.ensma.ia.bataille_navale.ihm.TransitionScreen;
import fr.ensma.ia.bataille_navale.ihm.agents.global.PresenterGlobal;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.ActionFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.action.EAction;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Case;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.outilsMultithread.Synchro;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KernelThread extends Thread {
	private IJoueur j1,j2;
	private PresenterGlobal presGlobalJ1,presGlobalJ2;
	private Scene sceneJ1, sceneJ2;
	private Stage stage;
	private Scene j1Toj2Scene;
	private Scene j2Toj1Scene;
	
	public KernelThread(IJoueur j1, PresenterGlobal presGlobalJ1, IJoueur j2, PresenterGlobal presGlobalJ2, Scene sceneJ1, Scene sceneJ2, Scene j1Toj2Scene, Scene j2Toj1Scene, Stage primaryStage) {
		this.j1 = j1;
		this.j2 = j2;
		this.presGlobalJ1 = presGlobalJ1;
		this.presGlobalJ2 = presGlobalJ2;
		this.sceneJ1 =  sceneJ1;
		this.sceneJ2 =  sceneJ2;
		this.j1Toj2Scene = j1Toj2Scene;
		this.j2Toj1Scene = j2Toj1Scene;
		this.stage = primaryStage;
	}
	
	@Override
	public void run() {
        j1.initialiseBateaux(presGlobalJ1);
        swapToJ2();
        j2.initialiseBateaux(presGlobalJ2);
        swapToJ1();
        while (true) {
        	boolean ok = false;
        	while (!ok)
        	{
	        	try {
	        		System.out.println(ActionFactory.createFactory(EAction.AttaqueCroix, presGlobalJ1, j1.getGrille(), j2.getGrille()).createAction().doAction());
					ok = true;
				} catch (ExceptionBadInput e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					//TIMEOUT
				}
        	}
        	swapToJ2();
        	ok = false;
        	while (!ok)
        	{
	        	try {
					System.out.println(ActionFactory.createFactory(EAction.AttaqueCroix, presGlobalJ2, j2.getGrille(), j1.getGrille()).createAction().doAction());
					ok = true;
				} catch (ExceptionBadInput e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					//TIMEOUT
				}
        	}
        	swapToJ1();
        }
	}
	
	
	
	private void swapToJ1() {
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				stage.setScene(j2Toj1Scene);
			}
        	});
		
		try {
			((TransitionScreen)j2Toj1Scene.getRoot()).waitUnlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Platform.runLater(new Runnable(){
	
			@Override
			public void run() {
				stage.setScene(sceneJ1);
				presGlobalJ1.getPresGrilleMyBoats().updateAll();
				presGlobalJ1.getPresGrilleEnnemy().updateAll();
			}
	    	});
		
	}
	
	private void swapToJ2() {
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				stage.setScene(j1Toj2Scene);
			}
        	});
		
		Synchro s = new Synchro();
		try {
			((TransitionScreen)j1Toj2Scene.getRoot()).waitUnlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				stage.setScene(sceneJ2);
				presGlobalJ2.getPresGrilleMyBoats().updateAll();
				presGlobalJ2.getPresGrilleEnnemy().updateAll();
			}
        	});
	}
}
