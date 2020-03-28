package fr.ensma.ia.bataille_navale;

import fr.ensma.ia.bataille_navale.ihm.ConsoleAsker;
import fr.ensma.ia.bataille_navale.ihm.TransitionScreen;
import fr.ensma.ia.bataille_navale.ihm.agents.global.PresenterGlobal;
import fr.ensma.ia.bataille_navale.ihm.agents.global.VueGlobal;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.PresenterGrille;
import fr.ensma.ia.bataille_navale.ihm.agents.grille.VueGrille;
import fr.ensma.ia.bataille_navale.noyau.KernelThread;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.BateauFactory;
import fr.ensma.ia.bataille_navale.noyau.fabrique.bateau.EBateau;
import fr.ensma.ia.bataille_navale.noyau.jeu.Grille;
import fr.ensma.ia.bataille_navale.noyau.jeu.IJoueur;
import fr.ensma.ia.bataille_navale.noyau.jeu.JoueurHumain;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
	IJoueur moi,lui;
	PresenterGlobal myPresGlobal;
	PresenterGlobal hisPresGlobal;
	KernelThread kernelThread;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        launch(args);
    }
    
    
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		moi = new JoueurHumain();
		lui = new JoueurHumain();
		
        myPresGlobal = new PresenterGlobal(moi, lui);
        myPresGlobal.setVue(new VueGlobal(myPresGlobal));
        
        hisPresGlobal = new PresenterGlobal(lui, moi);
        hisPresGlobal.setVue(new VueGlobal(hisPresGlobal));
        
        
        Scene myScene = new Scene((Parent) myPresGlobal.getVue(), Parametres.widthPix, Parametres.heightPix);
        Scene hisScene = new Scene((Parent) hisPresGlobal.getVue(), Parametres.widthPix, Parametres.heightPix);
        Scene j1Toj2Scene = new Scene(new TransitionScreen("C'est au tour de J2") , Parametres.widthPix, Parametres.heightPix);
        Scene j2Toj1Scene = new Scene(new TransitionScreen("C'est au tour de J1") , Parametres.widthPix, Parametres.heightPix);
        kernelThread = new KernelThread(moi, myPresGlobal, lui, hisPresGlobal, myScene, hisScene,j1Toj2Scene, j2Toj1Scene, primaryStage);
        primaryStage.setScene(myScene);
		primaryStage.show();
		myPresGlobal.getPresGrilleMyBoats().updateAll();
        myPresGlobal.getPresGrilleEnnemy().updateAll();
        kernelThread.start();
	}
}